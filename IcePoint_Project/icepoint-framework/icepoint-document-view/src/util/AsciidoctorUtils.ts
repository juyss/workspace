import {Item} from "../component/Catalog";
import axios from "axios";

let asciidoctor = require('asciidoctor')();

export default class AsciidoctorUtils {

    static getCatalogHtml(html: string): string | undefined {

        const sign = '<div id="toc" class="toc">';
        let startIndex = html.indexOf(sign);
        if (startIndex < 0) {
            return undefined;
        }

        let searchIndex = startIndex + 78;
        let endIndex = html.indexOf('</div>', searchIndex);

        if (endIndex < 0) {
            throw new Error('目录html解析失败');
        }

        endIndex += 6;

        return html.substring(startIndex, endIndex);
    }

    static read(name: string) {
        return axios.get(`/document/${name}.adoc`)
            .then(res => {
                return asciidoctor.convert(res.data);
            })
    }

    static getItems(ul: any): Item[] | undefined {

        if (!ul) {
            return undefined;
        }

        let liElements = ul[0]?.li;

        if (!liElements || liElements.length <= 0) {
            return undefined;
        }

        return liElements.map((li: any) => {

            let itemElement = li.a[0];
            let href = itemElement.$.href;
            let content = itemElement._;

            let item = new Item(href, content);
            if (li.ul) {
                item.children = AsciidoctorUtils.getItems(li.ul);
            }
            return item;
        });
    }
}