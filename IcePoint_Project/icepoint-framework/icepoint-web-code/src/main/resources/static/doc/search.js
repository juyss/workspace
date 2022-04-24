let api = [];
api.push({
    alias: 'SysBizFieldController',
    order: '1',
    link: '(sysbizfield)表控制层',
    desc: '(SysBizField)表控制层',
    list: []
})
api[0].list.push({
    order: '1',
    desc: '分页查询所有数据',
});
api[0].list.push({
    order: '2',
    desc: '通过主键查询单条数据',
});
api[0].list.push({
    order: '3',
    desc: '新增数据',
});
api[0].list.push({
    order: '4',
    desc: '修改数据',
});
api[0].list.push({
    order: '5',
    desc: '删除数据',
});
api.push({
    alias: 'SysFunctionController',
    order: '2',
    link: '(sysfunction)函数控制层',
    desc: '(SysFunction)函数控制层',
    list: []
})
api[1].list.push({
    order: '1',
    desc: '分页查询所有数据',
});
api[1].list.push({
    order: '2',
    desc: '通过主键查询单条数据',
});
api[1].list.push({
    order: '3',
    desc: '新增数据',
});
api[1].list.push({
    order: '4',
    desc: '修改数据',
});
api[1].list.push({
    order: '5',
    desc: '删除数据',
});
api[1].list.push({
    order: '6',
    desc: '添加函数，来自工程',
});
api[1].list.push({
    order: '7',
    desc: '添加函数，来自用户自定义',
});
api[1].list.push({
    order: '8',
    desc: '查询函数',
});
api[1].list.push({
    order: '9',
    desc: '修改函数',
});
api[1].list.push({
    order: '10',
    desc: '删除函数',
});
api.push({
    alias: 'SysFunServiceController',
    order: '3',
    link: '(sysfunservice)表控制层',
    desc: '(SysFunService)表控制层',
    list: []
})
api[2].list.push({
    order: '1',
    desc: '分页查询所有数据',
});
api[2].list.push({
    order: '2',
    desc: '通过主键查询单条数据',
});
api[2].list.push({
    order: '3',
    desc: '新增数据',
});
api[2].list.push({
    order: '4',
    desc: '修改数据',
});
api[2].list.push({
    order: '5',
    desc: '删除数据',
});
api[2].list.push({
    order: '6',
    desc: '查询用户的函数列表',
});
api[2].list.push({
    order: '7',
    desc: '保存函数流程图',
});
api[2].list.push({
    order: '8',
    desc: '读取函数流程图',
});
api.push({
    alias: 'SysGroupController',
    order: '4',
    link: '分组表(sysgroup)表控制层',
    desc: '分组表(SysGroup)表控制层',
    list: []
})
api[3].list.push({
    order: '1',
    desc: '分页查询所有数据',
});
api[3].list.push({
    order: '2',
    desc: '获取树形结构',
});
api[3].list.push({
    order: '3',
    desc: '通过主键查询单条数据',
});
api[3].list.push({
    order: '4',
    desc: '新增数据',
});
api[3].list.push({
    order: '5',
    desc: '修改数据',
});
api[3].list.push({
    order: '6',
    desc: '删除数据',
});
api[3].list.push({
    order: '7',
    desc: '移动节点，命令（置顶 :top  置底 :end 移动 :move）',
});
api[3].list.push({
    order: '8',
    desc: '',
});
api.push({
    alias: 'CreateCodeController',
    order: '5',
    link: '生成代码请求',
    desc: '生成代码请求',
    list: []
})
api[4].list.push({
    order: '1',
    desc: '生成代码',
});
api.push({
    alias: 'IntegrationGraphController',
    order: '6',
    link: 'the_rest_controller_to_provide_the_management_api_over_{@link_integrationgraphserver}.',
    desc: 'The REST Controller to provide the management API over {@link IntegrationGraphServer}.',
    list: []
})
api[5].list.push({
    order: '1',
    desc: '',
});
api[5].list.push({
    order: '2',
    desc: '',
});
api.push({
    alias: 'dict',
    order: '7',
    link: 'dict_list',
    desc: '数据字典',
    list: []
})
document.onkeydown = keyDownSearch;
function keyDownSearch(e) {
    const theEvent = e;
    const code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code == 13) {
        const search = document.getElementById('search');
        const searchValue = search.value;
        let searchArr = [];
        for (let i = 0; i < api.length; i++) {
            let apiData = api[i];
            const desc = apiData.desc;
            if (desc.indexOf(searchValue) > -1) {
                searchArr.push({
                    order: apiData.order,
                    desc: apiData.desc,
                    link: apiData.link,
                    list: apiData.list
                });
            } else {
                let methodList = apiData.list || [];
                let methodListTemp = [];
                for (let j = 0; j < methodList.length; j++) {
                    const methodData = methodList[j];
                    const methodDesc = methodData.desc;
                    if (methodDesc.indexOf(searchValue) > -1) {
                        methodListTemp.push(methodData);
                        break;
                    }
                }
                if (methodListTemp.length > 0) {
                    const data = {
                        order: apiData.order,
                        desc: apiData.desc,
                        link: apiData.link,
                        list: methodListTemp
                    };
                    searchArr.push(data);
                }
            }
        }
        let html;
        if (searchValue == '') {
            const liClass = "";
            const display = "display: none";
            html = buildAccordion(api,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        } else {
            const liClass = "open";
            const display = "display: block";
            html = buildAccordion(searchArr,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        }
        const Accordion = function (el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            const links = this.el.find('.dd');
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        };
        Accordion.prototype.dropdown = function (e) {
            const $el = e.data.el;
            $this = $(this), $next = $this.next();
            $next.slideToggle();
            $this.parent().toggleClass('open');
            if (!e.data.multiple) {
                $el.find('.submenu').not($next).slideUp("20").parent().removeClass('open');
            }
        };
        new Accordion($('#accordion'), false);
    }
}

function buildAccordion(apiData, liClass, display) {
    let html = "";
    let doc;
    if (apiData.length > 0) {
        for (let j = 0; j < apiData.length; j++) {
            html += '<li class="'+liClass+'">';
            html += '<a class="dd" href="#_' + apiData[j].link + '">' + apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
            html += '<ul class="sectlevel2" style="'+display+'">';
            doc = apiData[j].list;
            for (let m = 0; m < doc.length; m++) {
                html += '<li><a href="#_' + apiData[j].order + '_' + doc[m].order + '_' + doc[m].desc + '">' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + doc[m].desc + '</a> </li>';
            }
            html += '</ul>';
            html += '</li>';
        }
    }
    return html;
}