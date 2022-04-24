const asciidoctor = require('asciidoctor')();
const fs = require('fs');
const charset = 'utf-8';

const adocRexExp = /.+\.adoc$/;
const baseDir = '../public/document';
const newDir = `./generated`;

const options = {
    mkdirs: true,
    safe: 'server',
};

function mask(str, begin, end, char) {
    let fstStr = str.substring(0, begin);
    let lstStr = str.substring(end, str.length);
    return fstStr + char + lstStr;
}

function toLowerCamel(str) {
    return str.replace(/([^-])-+([^-])/g, ($0, $1, $2) => {
        return $1 + $2.toUpperCase();
    });
}

function toHtml(str) {
    return str.replace(/&(lt|gt|nbsp|amp|quot);/ig, function (all, t) {
        switch (t) {
            case 'lt':
                return '<';
            case 'gt':
                return '>';
            case 'nbsp':
                return ' ';
            case 'amp':
                return '&';
            case "quot":
                return '"';
            default:
                throw new Error('不支持的Html特殊符号: ' + t);
        }
    });
}

function replaceStyle(html) {
    let styleRex = /style=".+"/g;
    let style = html.matchAll(styleRex).next().value;
    while (style) {

        const excludedIndex = 7;
        let start = style.index;
        let styleContent = style[0];
        let end = start + styleContent.length;
        let styleProperties = html.substring(excludedIndex + start, end).split(';');

        let jsonString = '';
        for (let propertyAndValue of styleProperties) {
            let split = propertyAndValue.split(":");

            if (split.length < 2) {
                continue;
            }

            let property = toLowerCamel(split[0].trim());
            let value = split[1].trim();
            jsonString += `"${property}": "${value}"`
        }
        jsonString = `{{${jsonString}}}`;

        html = mask(html, start + excludedIndex - 1, end, jsonString);

        style = html.matchAll(styleRex).next().value;
    }

    return html;
}

function replaceCol(html) {

    let rex = /<col .+>/g;
    html = html.replace(rex, str => {
        return str.substring(0, str.length - 1) + '/>';
    })

    return html;
}

/**
 * 删除后缀名以及.
 *
 * @param fileName 文件名
 * @returns {*|string} 返回没有后缀名的文件名
 */
function removeExt(fileName) {

    let docIndex = fileName.lastIndexOf('.');
    return docIndex <= 0
        ? fileName
        : fileName.substr(0, docIndex);
}

function removeCatalog(html) {

    const sign = '<div id="toc" className="toc">';
    let startIndex = html.indexOf(sign);
    if (startIndex < 0) {
        return html;
    }

    let searchIndex = startIndex + 78;
    let endIndex = html.indexOf('</div>', searchIndex);

    if (endIndex < 0) {
        throw new Error('目录html解析失败');
    }

    endIndex += 6;
    return mask(html, startIndex, endIndex, '');
}

function replacePreElementContent(html) {
    return html.replace(/<pre[\s\S]*?><code>[\s\S]+?<\/pre>/g, str => {
        let replacement = str.replace(/<[\s\S]+?>/g, s => {
            return s.replace('>', '>{`').replace('<', '`}<');
        })

        replacement = toHtml(replacement);
        return replacement.substring(2, replacement.length - 2)
            .replace(/&#123;/g, '{')
            .replace(/&#125;/g, '}');
    });
}

let documents = fs.readdirSync(baseDir).filter(f => f.match(adocRexExp));
// let documentNames = documents.map(doc => removeExt(doc));
//
// // 删除不存在的文件
// fs.readdirSync(newDir)
//     .filter(file => {
//         let name = removeExt(file);
//         return documentNames.indexOf(name) >= 0;
//     })
//     .forEach(file => fs.rmdirSync(`${newDir}/${file}`));

// 生成tsx文件
documents
    .forEach(doc => {

        let file = fs.readFileSync(`${baseDir}/${doc}`, charset);
        let adoc = asciidoctor.loadFile(`${baseDir}/${doc}`, options);
        let html = asciidoctor.convert(file, options);

        let title = adoc.getTitle();
        html = html.replace(/class=/g, 'className=');
        html = html.replace(/{/g, '&#123;');
        html = html.replace(/}/g, '&#125;');

        html = replaceStyle(html);
        html = replaceCol(html);
        html = removeCatalog(html);
        html = replacePreElementContent(html);

        let fileName = removeExt(doc);

        let content = `import Document from "../page/Document";
        
        export default function ${fileName}() {
            return <Document name="${title}">
                ${html}
            </Document>;
        }`;

        fs.writeFileSync(`${newDir}/${fileName}.tsx`, content);
    });
