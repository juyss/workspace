var mysql = require('mysql');

/**
 * 创建连接对象
 *
 * @param config 配置
 */
var client = function(config) {
    var me = this;

    // 配置
    this.config = config;

    /**
     * 创建连接
     *
     * @param filePath
     *            文件路径
     * @returns
     */
    this.connect = function() {
        return mysql.createConnection(me.config);
    };

    /**
     * 查询
     *
     * @param sql
     * @param callbackFn
     */
    this.query = function(sql, callbackFn) {
        var conn = me.connect();
        conn.connect();
        conn.query(sql, function(err, result, fields) {
            if (err) {
                throw err;
            }
            callbackFn(result, fields);
        });
        conn.end();
    };
};

module.exports = client;