import PageTemplate from "./PageTemplate";
import React from "react";
import List from "./List";
import java from '../generated/java';
import {BrowserRouter, Route} from "react-router-dom";

import routes from "../routes";
import AsciidoctorUtils from "../util/AsciidoctorUtils";
import Catalog from "../component/Catalog";

import xml2js from 'xml2js';
import frameworkWebCore from "../generated/frameworkWebCore";
import framework from "../generated/framework";
import frameworkCore from "../generated/frameworkCore";
import frameworkData from "../generated/frameworkData";
import frameworkWebSystem from "../generated/frameworkWebSystem";
import workOrder from "../generated/workOrder";

export default class Home extends React.Component<{}, any> {


    async getCatalog(name: string): Promise<Catalog | undefined> {
        return AsciidoctorUtils.read(name)
            .then(data => {

                let catalogHtml = AsciidoctorUtils.getCatalogHtml(data);

                return xml2js.parseStringPromise(catalogHtml as string)
                    .then(res => {

                        let ul = res.div.ul;
                        let items = AsciidoctorUtils.getItems(ul);

                        let catalog = new Catalog();
                        catalog.items = items;

                        return catalog;
                    });
            });
    }

    render() {
        return (
            <BrowserRouter>
                <Route
                    path="/"
                    exact
                    render={() =>
                        <PageTemplate title='冰点文档系统'>
                            <List
                                data={[
                                    {
                                        title: routes.java.title,
                                        description: 'Java语言开发规范手册',
                                        href: routes.java.path
                                    },
                                    {
                                        title: routes.framework.title,
                                        description: '后端框架整体架构简介',
                                        href: routes.framework.path
                                    },
                                    {
                                        title: routes.core.title,
                                        description: '后端框架核心功能模块，主要是通用的辅助API以及对JDK进一步封装的功能',
                                        href: routes.core.path
                                    },
                                    {
                                        title: routes.data.title,
                                        description: '数据层核心功能模块',
                                        href: routes.data.path
                                    },
                                    {
                                        title: routes.webCore.title,
                                        description: 'MVC的核心功能模块，包括wen辅助API、前端返回值规范定义、前端信息返回定义、数据字典等',
                                        href: routes.webCore.path
                                    },
                                    {
                                        title: routes.webSystem.title,
                                        description: '以项目的维度构建的web模块，包括项目、模块、函数、数据实体等的元数据定义，以及通用接口等',
                                        href: routes.webSystem.path
                                    },
                                    {
                                        title: routes.workOrder.title,
                                        description: '工单系统需求说明文档',
                                        href: routes.workOrder.path
                                    },
                                ]}
                            />
                        </PageTemplate>
                    }
                >
                </Route>
                <Route
                    path={routes.java.path}
                    exact
                    render={() =>
                        <PageTemplate title={routes.java.title} catalog={this.getCatalog('java')}>
                            {java()}
                        </PageTemplate>
                    }
                />
                <Route
                    path={routes.framework.path}
                    exact
                    render={() =>
                        <PageTemplate title={routes.framework.title} catalog={this.getCatalog('framework')}>
                            {framework()}
                        </PageTemplate>
                    }
                />
                <Route
                    path={routes.core.path}
                    exact
                    render={() =>
                        <PageTemplate title={routes.core.title} catalog={this.getCatalog('frameworkCore')}>
                            {frameworkCore()}
                        </PageTemplate>
                    }
                />
                <Route
                    path={routes.data.path}
                    exact
                    render={() =>
                        <PageTemplate title={routes.data.title} catalog={this.getCatalog('frameworkData')}>
                            {frameworkData()}
                        </PageTemplate>
                    }
                />
                <Route
                    path={routes.webCore.path}
                    exact
                    render={() =>
                        <PageTemplate title={routes.webCore.title} catalog={this.getCatalog('frameworkWebCore')}>
                            {frameworkWebCore()}
                        </PageTemplate>
                    }
                />
                <Route
                    path={routes.webSystem.path}
                    exact
                    render={() =>
                        <PageTemplate title={routes.webSystem.title} catalog={this.getCatalog('frameworkWebSystem')}>
                            {frameworkWebSystem()}
                        </PageTemplate>
                    }
                />
                <Route
                    path={routes.workOrder.path}
                    exact
                    render={() =>
                        <PageTemplate title={routes.workOrder.title} catalog={this.getCatalog('workOrder')}>
                            {workOrder()}
                        </PageTemplate>
                    }
                />
            </BrowserRouter>
        );
    }
}