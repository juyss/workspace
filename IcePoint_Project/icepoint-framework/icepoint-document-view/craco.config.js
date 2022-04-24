const CracoLessPlugin = require('craco-less');

module.exports = {
    plugins: [
        {
            plugin: CracoLessPlugin,
            options: {
                lessLoaderOptions: {
                    lessOptions: {
                        modifyVars: {
                            '@font-size-base': '16px',
                            '@background-color-base': '#ffffff'
                        },
                        javascriptEnabled: true,
                    },
                },
            },
        },
    ],
};