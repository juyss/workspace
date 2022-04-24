import React from "react";
import {List as AntdList, Typography} from "antd";

const {Link, Text} = Typography;

declare type Item = {
    title: string;
    description: string;
    href: string;
}

export default class List extends React.Component<{
    data: Item[]
    onClick?: (event: React.MouseEvent<HTMLAnchorElement, MouseEvent>, title: string) => void;
}, any> {

    private renderItem(item: Item) {
        return (
            <AntdList.Item
                style={{padding: '16px 0px'}}
            >
                <AntdList.Item.Meta
                    title={<Link
                        href={item.href}
                        style={{fontWeight: 'bold'}}
                        onClick={event => {
                            if (typeof this.props?.onClick === 'function') {
                                this.props.onClick(event, item.title);
                            }
                        }}
                    >
                        {item.title}
                    </Link>}
                    description={<Text type="secondary">{item.description}</Text>}
                />
            </AntdList.Item>
        )
    }

    render() {
        return (
            <AntdList
                size="large"
                dataSource={this.props.data}
                renderItem={item => this.renderItem(item)}
            />
        );
    }
}