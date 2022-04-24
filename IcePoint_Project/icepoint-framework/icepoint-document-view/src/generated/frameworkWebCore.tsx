import Document from "../page/Document";

export default function frameworkWebCore() {
            return <Document name="Web核心模块">
                
<div className="sect1">
<h2 id="_0_概述">0. 概述</h2>
<div className="sectionbody">
<div className="paragraph">
<p>web核心主要包含MVC的辅助API、通用接口、前端返回值规范定义、前端信息返回定、数据字典。</p>
</div>
</div>
</div>
<div className="sect1">
<h2 id="_1_依赖">1. 依赖</h2>
<div className="sectionbody">
<div className="exampleblock">
<div className="title">Example 1. Maven依赖</div>
<div className="content">
<div className="listingblock">
<div className="content">
<pre className="highlight">{``}<code className="language-xml" data-lang="xml">{`<dependency>
    <groupId>com.icepoint.framework</groupId>
    <artifactId>icepoint-web-core</artifactId>
    <version>0.1-SNAPSHOT</version>
</dependency>`}</code>{``}</pre>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}<div className="sect1">{`
`}<h2 id="_2_前端返回值定义">{`2. 前端返回值定义`}</h2>{`
`}<div className="sectionbody">{`
`}<div className="sect2">{`
`}<h3 id="_2_1_顶层接口responset">{`2.1 顶层接口：Response<T>`}</h3>{`
`}<div className="paragraph">{`
`}<p>{``}<code>{`Response`}</code>{` 是前端返回对象的顶层接口，反序列化后，会包含4个字段：`}</p>{`
`}</div>{`
`}<div className="ulist">{`
`}<ul>{`
`}<li>{`
`}<p>{``}<code>{`code`}</code>{`：信息码，辨别请求处理状态和结果的标识`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`data`}</code>{`：返回数据`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`message`}</code>{`：返回信息`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`empty`}</code>{`：布尔值，表示该返回是否包含数据，true代表不包含。`}</p>{`
`}</li>{`
`}</ul>{`
`}</div>{`
`}<div className="paragraph">{`
`}<p>{`根据常见的业务场景，框架本身提供了多种 `}<code>{`Response`}</code>{` 的子类，方便使用：`}</p>{`
`}</div>{`
`}<div className="ulist">{`
`}<ul>{`
`}<li>{`
`}<p>{``}<code>{`Response<T>`}</code>{`：最基础的返回对象，`}<code>{`T`}</code>{` 就是返回数据的类型`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`CollectionResponse<T>`}</code>{`：返回集合类型数据可以使用，`}<code>{`T`}</code>{` 是集合内元素的类型，相当于 `}<code>{`Response<Collection<T>>`}</code>{``}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`PageResponse<T>`}</code>{`：返回分页数据时可以使用，`}<code>{`T`}</code>{` 是分页数据元素类型，相当于 `}<code>{`Response<Page<T>>`}</code>{``}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`MapResponse<K, V>`}</code>{`：返回 `}<code>{`Map`}</code>{` 对象时可以使用，`}<code>{`K`}</code>{` 是键的类型， `}<code>{`V`}</code>{` 是值的类型，相当于 `}<code>{`Response<Map<K, V>>`}</code>{``}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`UnsafeResponse`}</code>{`：没有泛型类型的返回对象，如果返回对象类型不确定的时候可以使用。`}</p>{`
`}</li>{`
`}</ul>{`
`}</div>{`
`}</div>{`
`}<div className="sect2">{`
`}<h3 id="_2_2_responsebuilder">{`2.2 ResponseBuilder`}</h3>{`
`}<div className="paragraph">{`
`}<p>{`返回值对象构建会有一点复杂，这个 `}<code>{`ResponseBuilder`}</code>{` 是为了方便构建 `}<code>{`Response`}</code>{` 而设计的，使用了建造者模式。`}</p>{`
`}</div>{`
`}<div className="exampleblock">{`
`}<div className="title">{`Example 2. ResponseBuilder`}</div>{`
`}<div className="content">{`
`}<div className="listingblock">{`
`}<div className="content">{`
`}<pre className="highlight">{``}<code>{`ResponseBuilder.any(data) `}<b className="conum">{`(1)`}</b>{`
    .code("200")
    .message("成功")
    .build();

ResponseBuilder.configure(response) `}<b className="conum">{`(2)`}</b>{`
    .code("404")
    .message("找不到数据")
    .build();

ResponseBuilder.justOk("没有数据"); `}<b className="conum">{`(3)`}</b>{``}</code>{``}</pre>
</div>
</div>
<div className="colist arabic">
<ol>
<li>
<p>构建一个包含 <code>data</code> 数据的 <code>Response</code></p>
</li>
<li>
<p>传入已有的 <code>Response</code>，对该对象进行再配置</p>
</li>
<li>
<p>构建一个表示请求处理成功的 <code>Response</code>，并且返回信息 <code>"没有数据"</code></p>
</li>
</ol>
</div>
</div>
</div>
</div>
<div className="sect2">
<h3 id="_2_3_responseutils">2.3 ResponseUtils</h3>
<div className="paragraph">
<p><code>ResponseUtils</code> 是根据常见的业务场景，对 <code>ResponseBuilder</code> 做进一步封装，增加对不同数据的情况下，对 <code>Response</code> 做业务逻辑相关处理。</p>
</div>
<div className="exampleblock">
<div className="title">Example 3. ResponseUtils</div>
<div className="content">
<div className="listingblock">
<div className="content">
<pre className="highlight">{``}<code>{`ResponseUtils.any(data); `}<b className="conum">{`(1)`}</b>{`

ResponseUtils.one(data); `}<b className="conum">{`(2)`}</b>{`

ResponseUtils.many(collection); `}<b className="conum">{`(3)`}</b>{`

ResponseUtils.map(map); `}<b className="conum">{`(4)`}</b>{`

ResponseUtils.page(page); `}<b className="conum">{`(5)`}</b>{`

ResponseUtils.operate(boolean, "500"); `}<b className="conum">{`(6)`}</b>{``}</code>{``}</pre>
</div>
</div>
<div className="colist arabic">
<ol>
<li>
<p>构建 <code>Response</code> 对象，<code>data</code> 允许是任何值，一定会返回请求处理成功</p>
</li>
<li>
<p>构建 <code>Response</code> 对象，<code>data</code> 为null时会返回前端找不到数据的信息码</p>
</li>
<li>
<p>构建 <code>Response</code> 对象，<code>data</code> 必须是 <code>Collection</code> 类型或者子类，若为 <code>null</code> 或者没有元素，会返回前端找不到数据的信息码</p>
</li>
<li>
<p>构建 <code>Response</code> 对象，<code>data</code> 必须是 <code>Map</code> 类型或者子类，若为 <code>null</code> 或者没有元素，会返回前端找不到数据的信息码</p>
</li>
<li>
<p>构建 <code>Response</code> 对象，<code>data</code> 必须是 <code>Page</code> 类型或者子类，若为 <code>null</code> 或者没有元素，会返回前端找不到数据的信息码</p>
</li>
<li>
<p>构建 <code>Response</code> 对象，<code>data</code> 必须是 <code>boolean</code> 类型，当为 <code>false</code> 时会以第二个参数作为信息码返回</p>
</li>
</ol>
</div>
</div>
</div>
</div>
<div className="sect2">
<h3 id="_2_4_responseprocessor">2.4 ResponseProcessor</h3>
<div className="paragraph">
<p>返回对象处理器，允许在 <code>Response</code> 对象反序列化之前做处理或替换返回值数据等操作。</p>
</div>
<div className="exampleblock">
<div className="title">Example 4. ResponseProcessor</div>
<div className="content">
<div className="listingblock">
<div className="content">
<pre className="highlight">{``}<code className="language-java" data-lang="java">{`public class MyResponseProcessor implements ResponseProcessor {
    @Override
    public Response<?> process(Response<?> body, ServerHttpRequest request,
        ServerHttpResponse response) { `}<b className="conum">{`(1)`}</b>{`

        body.setCode(CoreMessage.NOT_FOUND.getCode());
        return body;
    }

    @Override
    public boolean supports(Class<?> type) { `}<b className="conum">{`(2)`}</b>{`
        return Response.class.isAssignableFrom(type);
    }
}`}</code>{``}</pre>{`
`}</div>{`
`}</div>{`
`}<div className="colist arabic">{`
`}<ol>{`
`}<li>{`
`}<p>{`对返回对象 `}<code>{`Response`}</code>{` 做处理，允许返回新的对象。`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{`传入的对象是目前正在处理的 `}<code>{`Response`}</code>{` 的类型，这个方法返回 `}<code>{`true`}</code>{` 才会执行 `}<code>{`process()`}</code>{` 方法`}</p>{`
`}</li>{`
`}</ol>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}<div className="sect2">{`
`}<h3 id="_2_5_hateoas支持">{`2.5 HATEOAS支持`}</h3>{`
`}<div className="paragraph">{`
`}<p>{`待完善`}</p>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}<div className="sect1">{`
`}<h2 id="_3_前端消息系统">{`3. 前端消息系统`}</h2>{`
`}<div className="sectionbody">{`
`}<div className="paragraph">{`
`}<p>{`所有的信息以信息码作为标识进行查找，目前信息会存储在数据库表 `}<code>{`sys_response_message`}</code>{`。`}</p>{`
`}</div>{`
`}<div className="sect2">{`
`}<h3 id="_3_1_顶层接口_message">{`3.1 顶层接口 Message`}</h3>{`
`}<div className="paragraph">{`
`}<p>{`通过信息码和异常类型的绑定，当应用允许的时候抛出被绑定的异常类型后，会通过异常类型绑定的信息码，去查找对应的信息。`}</p>{`
`}</div>{`
`}<div className="exampleblock">{`
`}<div className="title">{`Example 5. Message`}</div>{`
`}<div className="content">{`
`}<div className="listingblock">{`
`}<div className="content">{`
`}<pre className="highlight">{``}<code className="language-java" data-lang="java">{`public interface Message {

    String getCode(); `}<b className="conum">{`(1)`}</b>{`

    @Nullable
    Class<?>[] getExTypes(); `}<b className="conum">{`(2)`}</b>{`
}`}</code>{``}</pre>{`
`}</div>{`
`}</div>{`
`}<div className="colist arabic">{`
`}<ol>{`
`}<li>{`
`}<p>{`返回值会作为绑定的信息码`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{`返回值会作为绑定的异常类型`}</p>{`
`}</li>{`
`}</ol>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}<div className="sect2">{`
`}<h3 id="_3_2_动态信息">{`3.2 动态信息`}</h3>{`
`}<div className="paragraph">{`
`}<p>{`数据库存储的信息允许含有占位符 `}<code>{`{paramName}`}</code>{`，在解析信息的时候，会从异常类调用对应的Getter方法，如果没有则会填充 `}<code>{`"null"`}</code>{``}</p>{`
`}</div>{`
`}<div className="exampleblock">{`
`}<div className="title">{`Example 6. 动态信息`}</div>{`
`}<div className="content">{`
`}<div className="listingblock">{`
`}<div className="content">{`
`}<pre className="highlight">{``}<code>{`// some code...
throws new IllegalArgumentException("参数不正确");

// 绑定的信息
"参数异常: {message}"

// 解析后的信息
"参数异常: 参数不正确"`}</code>{``}</pre>
</div>
</div>
</div>
</div>
</div>
<div className="sect2">
<h3 id="_3_3_messageexception">3.3 MessageException</h3>
<div className="paragraph">
<p><code>MessageException</code> 是异常类型的顶层父类，预设了以下几个异常类型：</p>
</div>
<div className="ulist">
<ul>
<li>
<p><code>CodedMessageException</code>：构建该异常需要传入参数 <code>code</code>，解析时会根据 <code>code</code> 值查找信息。</p>
</li>
<li>
<p><code>ReasonableMessageException</code>：继承自 <code>CodedMessageException</code>，多增加了一个字段 <code>reason</code></p>
</li>
<li>
<p><code>RootCauseMessageException</code>：继承自 <code>ReasonableMessageException</code>，构造方法需要传入一个 <code>Throwable</code> 对象，会递归查找最底层的异常信息，作为 <code>reason</code> 字段的值。</p>
</li>
<li>
<p><code>NullArgumentMessageException</code>：空参数异常，当参数为空时可以抛出，需要传入空对象的名称</p>
</li>
<li>
<p><code>DataNotFoundMessageException</code>：数据找不到时抛出，可以不需要参数构造该异常</p>
</li>
</ul>
</div>
</div>
</div>
</div>
<div className="sect1">
<h2 id="_4_增强api">4. 增强API</h2>
<div className="sectionbody">
<div className="sect2">
<h3 id="_4_1_应用初始化器applicationinitializer">4.1 应用初始化器：ApplicationInitializer</h3>
<div className="paragraph">
<p>实现这个接口，应用会在启动的时候运行其中的 <code>initialize()</code> 方法，如果验证不通过可以抛出异常终止程序启动。</p>
</div>
</div>
</div>
</div>
            </Document>;
        }