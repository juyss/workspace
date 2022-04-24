const moment = require('moment');
const path = require('path');
const Client = require('./util/client');
const file = require('./util/file');
const template = require('./util/template');

const modelConfig = require('./config/model');
const client = new Client(modelConfig.jdbc);
const modelSrcPath = path.join('../src/main/java/',...modelConfig.module.split('.'),'/model');
const mapperSrcPath = path.join('../src/main/java',...modelConfig.module.split('.'),'/mapper');
const exampleSrcPath = path.join('../src/main/java',...modelConfig.module.split('.'),'/example');

// 遍历配置
let sql = 'show table status';
if (!modelConfig.allTables) {
    sql += ` where name in ('${modelConfig.tables.join("', '")}')`;
}

client.query(sql, async function(tables) {

    await file.existsElseCreate(modelSrcPath)
    await file.existsElseCreate(mapperSrcPath)
    await file.existsElseCreate(exampleSrcPath)

    tables.forEach(info => {
        let table = info.Name, nameCN = table, keyAuto = 'N', num = 0;

        try {
            nameCN = info.Comment;
        } catch (e) {
        }

        // 查询表结构
        client.query('show full fields from ' + table, function(result) {
            var fields = [], imports = {};

            // 遍历字段
            result.forEach(function(row) {
                // 是否为主键
                var isPrimary = row.Key === 'PRI' ? 'Y' : 'N';
                // 是否自增长
                var isAuto = row.Extra === 'auto_increment' ? 'Y' : 'N';
                // 字段别名
                var alias = row.Field;
                // 字段名称
                var name = alias.substring(0, 1).toLowerCase() + alias.substring(1).replace(/_(\w)/g, function(all, letter) {
                    return letter.toUpperCase();
                });
                // 字段方法名
                var fnName = name.substring(0, 1).toUpperCase() + name.substring(1);

                var index = row.Type.indexOf('(');
                index = index > 0 ? index : row.Type.length;
                // 数据库原类型
                var dbType = row.Type;
                // 数据库类型，不带长度
                var jdbcType = row.Type.substring(0, index).toLowerCase();
                // java类型
                var javaType = modelConfig.fieldMap[jdbcType];
                // 是否可空
                var isNull = row.Null === 'YES' ? 'Y' : 'N';
                // 字段备注
                var comment = row.Comment;

                if (javaType.indexOf('.lang.') === -1) {
                    imports[javaType] = 1;
                }

                javaType = javaType.split('.');
                javaType = javaType[javaType.length - 1];

                var ignoreArr = ['creator','id','createDate','modifier','modifyDate','delFlag','applicationCode','tenantCode','isNewRecord','ext']
                // 字段模型
                var fieldModel = {
                    isPrimary: isPrimary,
                    isAuto: isAuto,
                    alias: alias,
                    name: name,
                    fnName: fnName,
                    dbType: dbType,
                    jdbcType: jdbcType,
                    javaType: javaType,
                    isNull: isNull,
                    comment: comment,
                    ignore:  ignoreArr.includes(name) ? 'Y' : 'N'
                };

                fields.push(fieldModel);

                if (keyAuto === 'N') {
                    keyAuto = isAuto;
                }
                if (fieldModel.ignore === 'Y') {
                    num++;
                }
            });

            // 表名
            var name = table.toLowerCase();
            // 变量名
            var variableName = name.replace(/_(\w)/g, function(all, letter) {
                return letter.toUpperCase();
            });
            // 实体类名
            var className = variableName.substring(0, 1).toUpperCase() + variableName.substring(1);

            // 表模型
            var tableModel = {
                module: modelConfig.module,
                author: modelConfig.author,
                date: moment().format('YYYY/MM/DD'),
                name: name,
                variableName: variableName,
                className: className,
                nameCN: nameCN,
                fields: fields,
                imports: imports,
                keyAuto: keyAuto,
                useBase: num >= 3 ? 'Y' : 'N'
            };

            if (modelConfig.create.model) {
                file.write(`${modelSrcPath}/${className}.java`, template.renderFile('src/template/model.tmpl', tableModel));
            }
            if (modelConfig.create.mapper) {
                file.write(`${mapperSrcPath}/${className}BaseMapper.java`, template.renderFile('src/template/mapper.tmpl', tableModel));
            }
            if (modelConfig.create.example) {
                file.write(`${exampleSrcPath}/${className}Example.java`, template.renderFile('src/template/example.tmpl', tableModel));
            }
        });
    });
});
