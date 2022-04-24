import React, {ReactNode} from "react";
import {Anchor, Layout, Menu, Typography} from "antd";
import Catalog, {Item} from "../component/Catalog";
import {LeftOutlined} from '@ant-design/icons';

const {Content, Sider} = Layout;
const {Title} = Typography;
const {Link} = Anchor;

/**
 * @author Jiawei Zhao
 */
export default class PageTemplate extends React.Component<{
    title: string;
    catalog?: Promise<Catalog | undefined>
}, {
    title: string;
    sider?: any;
    leftPadding: string;
    headerHeight: string | number;
}> {

    constructor(props: Readonly<any>) {
        super(props);

        this.state = {
            title: this.props.title,
            leftPadding: !!this.props?.catalog ? '300px' : '0px',
            headerHeight: '0',
        };

        this.renderSider.bind(this);
        this.renderSider();
    }

    private getLinkChildren(items: Item[]): ReactNode | ReactNode[] {
        return items.map((item, index) => this.getLink(item, index));
    }

    private getLink(item: Item, index: number) {
        let children = item.children;
        if (children && children.length > 0) {
            return (
                <Link key={index} title={item.content} href={item.href}>
                    {this.getLinkChildren(children)}
                </Link>
            );
        } else {
            return <Link key={index} title={item.content} href={item.href}/>
        }
    }

    private renderSider() {

        return this.props?.catalog
            ?.then(c => {
                if (!c || !c.items || c.items.length <= 0) {
                    return null;
                }

                let links = c.items.map((item, index) => this.getLink(item, index));

                return (
                    <Sider
                        style={{
                            overflow: 'auto',
                            height: '100vh',
                            position: 'fixed',
                            left: 0,
                            borderRight: '1px solid #f0f2f5',
                        }}
                        width={this.state.leftPadding}
                    >
                        <Menu
                            mode="inline"
                            // defaultSelectedKeys={['1']}
                            // defaultOpenKeys={['sub1']}
                            style={{height: '100%', borderRight: 0}}
                        >
                            <Menu.Item key="backToIndex"><a href={"/"}><LeftOutlined/>返回主页</a></Menu.Item>
                            <Anchor affix={false}>
                                {links}
                            </Anchor>
                        </Menu>
                    </Sider>
                );
            })
            .then(sider => this.setState({sider: sider}))
    }

    render() {

        return (
            <Layout>
                <Layout>
                    {this.state.sider}
                    <Layout style={{padding: '0 24px 24px', paddingLeft: this.state.leftPadding}}>
                        <Content style={{padding: '0 48px'}}>
                            <Title style={{paddingTop: this.state.headerHeight, paddingBottom: '24px'}}>
                                {this.props.title}
                            </Title>
                            {this.props.children}
                        </Content>
                    </Layout>
                </Layout>
            </Layout>
        );
    }
}