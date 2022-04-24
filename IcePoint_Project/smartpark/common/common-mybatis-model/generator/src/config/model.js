

let local = {
    host: '39.100.11.213',
    port: 3306,
    database: 'exam',
    user: 'root',
    password: '123456'
};

module.exports = {
    jdbc: local,
    // jdbc: local,
    module: 'com.github.tangyi',
    fieldMap: {
        varchar: 'java.lang.String',
        int: 'java.lang.Integer',
        tinyint: 'java.lang.Integer',
        bigint: 'java.lang.Long',
        decimal: 'java.math.BigDecimal',
        datetime: 'java.util.Date',
        timestamp: 'java.util.Date',
        float: 'java.lang.Double',
        text: 'java.lang.String',
        smallint: 'java.lang.Integer',
        date: 'java.util.Date',
        double: 'java.lang.Double',
        char: 'java.lang.String',
        json: 'java.lang.String',
        longtext: 'java.lang.String',
        mediumtext: 'java.lang.String'
    },
    create: {
        model: true,
        mapper: true,
        example: true
    },
    author: 'xh',
    allTables: false,
    tables: [
        'sys_user_dept'//'exam_course','exam_study_task'//'sms_record'//'exam_study_task'//'contacts'//'page_click_log'//'main_role'//'main_user' //'sys_config'//'exam_study_task'//,'exam_study_task_user_info','exam_study_task_course_relation','exam_study_task_course_user_info'//'exam_course'//'train_user_relation','train_course '
    ]
};
