export default class Catalog {

    private _items: Item[] | undefined;

    get items(): Item[] | undefined {
        return this._items;
    }

    set items(items: Item[] | undefined) {
        this._items = items;
    }
}

export class Item {

    private _href: string;

    private _content: string;

    private _children: Item[] | undefined;

    constructor(href: string, content: string) {
        this._href = href;
        this._content = content;
    }

    set href(href: string) {
        this._href = href;
    }

    get href(): string {
        return this._href;
    }

    set content(content: string) {
        this._content = content;
    }

    get content(): string {
        return this._content;
    }

    set children(children: Item[] | undefined) {
        this._children = children;
    }

    get children(): Item[] | undefined {
        return this._children;
    }
}
