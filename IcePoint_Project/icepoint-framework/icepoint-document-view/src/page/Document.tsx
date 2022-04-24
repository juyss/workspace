import React from "react";
import './document.less';

export default class Document extends React.Component<any, any> {

    render() {
        return this.props.children;
    }
}