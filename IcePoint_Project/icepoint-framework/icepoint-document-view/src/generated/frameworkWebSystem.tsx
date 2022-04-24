import Document from "../page/Document";

export default function frameworkWebSystem() {
            return <Document name="Web系统模块">
                
<div className="sect1">
<h2 id="_0_概述">0. 概述</h2>
<div className="sectionbody">
<div className="paragraph">
<p>以项目的维度构建的web模块，包括项目、模块、函数、数据实体等的元数据定义，以及通用接口等。</p>
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
    <artifactId>icepoint-web-system</artifactId>
    <version>0.1-SNAPSHOT</version>
</dependency>`}</code>{``}</pre>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}<div className="sect1">{`
`}<h2 id="_2_通用接口">{`2. 通用接口`}</h2>{`
`}<div className="sectionbody">{`
`}<div className="paragraph">{`
`}<p>{`通用接口需要配置一个请求路径，增删改查都是根据请求路径+RESTFUL标准来进行设计的。`}</p>{`
`}</div>{`
`}<div className="paragraph">{`
`}<p>{`例如现在有一个通用接口为：/sample，那么该接口增删改查的请求方式如下`}</p>{`
`}</div>{`
`}<table className="tableblock frame-all grid-all stretch">{`
`}<colgroup>{`
`}<col style={{"width": "33.3333%"}}/>{`
`}<col style={{"width": "33.3333%"}}/>{`
`}<col style={{"width": "33.3334%"}}/>{`
`}</colgroup>{`
`}<thead>{`
`}<tr>{`
`}<th className="tableblock halign-left valign-top">{`请求方法`}</th>{`
`}<th className="tableblock halign-left valign-top">{`请求路径`}</th>{`
`}<th className="tableblock halign-left valign-top">{`说明`}</th>{`
`}</tr>{`
`}</thead>{`
`}<tbody>{`
`}<tr>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`GET`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`/sample`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`查询列表，如果带分页参数，则是查询分页，分页参数分别是页码 `}<code>{`page`}</code>{` 和条数 `}<code>{`size`}</code>{`，`}<code>{`page`}</code>{` 从0开始，注意分页参数必须放在url参数上`}</p>{``}</td>{`
`}</tr>{`
`}<tr>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`GET`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`/sample/{id}`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`根据id查询`}</p>{``}</td>{`
`}</tr>{`
`}<tr>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`POST`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`/sample`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`新增数据，参数放到请求体中`}</p>{``}</td>{`
`}</tr>{`
`}<tr>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`PATCH`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`/sample`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`根据id进行局部更新，也是没有传的字段不会被更新，传的字段即便为 `}<code>{`null`}</code>{` ，也会进行更新`}</p>{``}</td>{`
`}</tr>{`
`}<tr>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`PUT`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`/sample`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`根据id进行全量更新，没有传的字段会被设置为 `}<code>{`null`}</code>{``}</p>{``}</td>{`
`}</tr>{`
`}<tr>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`DELETE`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`/sample/{id}`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`根据id删除数据，会根据配置自动进行物理删除或者逻辑删除`}</p>{``}</td>{`
`}</tr>{`
`}</tbody>{`
`}</table>{`
`}<div className="paragraph">{`
`}<p>{`所有GET请求均可添加查询参数，参数全部放在url参数上，简单的参数可以直接字段=值的方式进行查询。`}</p>{`
`}</div>{`
`}<div className="paragraph">{`
`}<p>{`复杂的条件查询的参数名是 `}<code>{`_query`}</code>{`，里面需要传递一个表达式。其中有可能会有部分字符需要用URI编码进行编码。`}</p>{`
`}</div>{`
`}<div className="paragraph">{`
`}<p>{`通用查询有一个概念叫 `}<code>{`path`}</code>{`，简单来说就是要查询的字段名，但也不完全相同，可以暂且当作是查询的字段名来看，后面会做详细解释。`}</p>{`
`}</div>{`
`}<div className="sect2">{`
`}<h3 id="_2_1_单条件查询">{`2.1 单条件查询`}</h3>{`
`}<div className="paragraph">{`
`}<p>{`最基础的表达式格式是: `}<code>{`{path} {operator} {value}`}</code>{`。`}</p>{`
`}</div>{`
`}<div className="ulist">{`
`}<ul>{`
`}<li>{`
`}<p>{``}<code>{`{path}`}</code>{`: 查询的字段名`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`{operator}`}</code>{`: 查询的条件`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`{value}`}</code>{`: 查询的值`}</p>{`
`}</li>{`
`}</ul>{`
`}</div>{`
`}<table className="tableblock frame-all grid-all stretch">{`
`}<caption className="title">{`Table 1. 支持的operator`}</caption>{`
`}<colgroup>{`
`}<col style={{"width": "33.3333%"}}/>{`
`}<col style={{"width": "33.3333%"}}/>{`
`}<col style={{"width": "33.3334%"}}/>{`
`}</colgroup>{`
`}<thead>{`
`}<tr>{`
`}<th className="tableblock halign-left valign-top">{`Operator(不区分大小写)`}</th>{`
`}<th className="tableblock halign-left valign-top">{`作用`}</th>{`
`}<th className="tableblock halign-left valign-top">{`说明`}</th>{`
`}</tr>{`
`}</thead>{`
`}<tbody>{`
`}<tr>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`eq`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`等于`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`支持所有数据类型`}</p>{``}</td>{`
`}</tr>{`
`}<tr>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`ne`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`不等于`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`支持所有数据类型`}</p>{``}</td>{`
`}</tr>{`
`}<tr>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`gt`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`大于`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`支持时间或者数字类型`}</p>{``}</td>{`
`}</tr>{`
`}<tr>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`ge`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`大于等于`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`同上`}</p>{``}</td>{`
`}</tr>{`
`}<tr>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`lt`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`小于`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`同上`}</p>{``}</td>{`
`}</tr>{`
`}<tr>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`le`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`小于等于`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`同上`}</p>{``}</td>{`
`}</tr>{`
`}<tr>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`like`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`模糊查询`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`需要添加匹配符"%"，例如完全模糊查询是"%query%"`}</p>{``}</td>{`
`}</tr>{`
`}<tr>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`between`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`区间查询`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`逗号分隔，如"5, 10"`}</p>{``}</td>{`
`}</tr>{`
`}<tr>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`in`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`多值查询等于`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`逗号分隔，如"1, 3, 5, 7"`}</p>{``}</td>{`
`}</tr>{`
`}<tr>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`notin`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`多值查询不等于`}</p>{``}</td>{`
`}<td className="tableblock halign-left valign-top">{``}<p className="tableblock">{`逗号分隔，如"1, 3, 5, 7"`}</p>{``}</td>{`
`}</tr>{`
`}</tbody>{`
`}</table>{`
`}<div className="exampleblock">{`
`}<div className="title">{`Example 2. 单条件查询表达式`}</div>{`
`}<div className="content">{`
`}<div className="listingblock">{`
`}<div className="content">{`
`}<pre className="highlight">{``}<code>{`text eq hello `}<b className="conum">{`(1)`}</b>{`
number gt 5 `}<b className="conum">{`(2)`}</b>{`
number between 5, 10 `}<b className="conum">{`(3)`}</b>{`
text in hello, world, icepoint `}<b className="conum">{`(4)`}</b>{`
text like %hello% `}<b className="conum">{`(5)`}</b>{``}</code>{``}</pre>
</div>
</div>
<div className="colist arabic">
<ol>
<li>
<p>查询字段 <code>text</code> 字段等于 <code>hello</code> 的数据，类似: <code>text == "hello"</code></p>
</li>
<li>
<p>查询字段 <code>number</code> 等于 <code>5</code> 的数据，类似 : <code>number &gt; 5</code></p>
</li>
<li>
<p>查询字段 <code>number</code> 大于等于 <code>5</code> 小于等于 <code>10</code> 的数据，类似: <code>number &gt;= 5 &amp;&amp; number &lt;= 10</code></p>
</li>
<li>
<p>查询字段 <code>text</code> 等于 <code>"hello"</code> 或 <code>"world"</code> 或 <code>"icepoint"</code> 的数据，类似: <code>text == "hello" || text == "world" || text == "icepoint"</code></p>
</li>
<li>
<p>查询字段 <code>text</code> 含有 <code>"hello"</code> 的数据</p>
</li>
</ol>
</div>
</div>
</div>
</div>
<div className="sect2">
<h3 id="_2_2_多条件查询">2.2 多条件查询</h3>
<div className="paragraph">
<p>在单条件的基础上，增加 <code>&#123;connector&#125;</code>，如：</p>
</div>
<div className="paragraph">
<p><code>&#123;path&#125; &#123;operator&#125; &#123;value&#125; &#123;connector&#125; &#123;path&#125; &#123;operator&#125; &#123;value&#125;</code></p>
</div>
<div className="paragraph">
<p>支持的 <code>&#123;connector&#125;</code> :</p>
</div>
<div className="ulist">
<ul>
<li>
<p><code>&amp;&amp;</code>: 与</p>
</li>
<li>
<p><code>||</code>: 或</p>
</li>
</ul>
</div>
<div className="exampleblock">
<div className="title">Example 3. 多条件查询表达式</div>
<div className="content">
<div className="listingblock">
<div className="content">
<pre className="highlight">{``}<code>{`text eq hello && number gt 5 `}<b className="conum">{`(1)`}</b>{`
number between 5, 10 && number ne 8 `}<b className="conum">{`(2)`}</b>{``}</code>{``}</pre>
</div>
</div>
<div className="colist arabic">
<ol>
<li>
<p>查询字段 <code>text</code> 字段等于 <code>hello</code> 的数据，类似: <code>text == "hello &amp;&amp; number &gt; 5"</code></p>
</li>
<li>
<p>查询字段 <code>number</code> 大于等于5，小于等于10，并且不等于8的数据，类似: <code>number &gt;= 5 &amp;&amp; number &lt;= 5 &amp;&amp; number != 8</code></p>
</li>
</ol>
</div>
</div>
</div>
</div>
<div className="sect2">
<h3 id="_2_3_查询路径path">2.3 查询路径path</h3>
<div className="paragraph">
<p><code>path</code> 跟简单的字段名不同的地方在于，<code>path</code> 是基于表关联的基础上，对字段的查询增加有深度的关联查询的功能。</p>
</div>
<div className="exampleblock">
<div className="title">Example 4. 关联查询</div>
<div className="content">
<div className="paragraph">
<p>例如现在有老师 (Teacher) 和学生 (Student) 两个表，它们都有一个字段 <code>name</code>，它们是多对多的关联关系。</p>
</div>
<div className="paragraph">
<p>通用查询接口是直接对应表的，而如果现在要查询所有老师中，有学生的 <code>name</code> 是 <code>Mark</code> 的，那么此时调用老师表的通用接口，要查询的path就可以是:</p>
</div>
<div className="listingblock">
<div className="content">
<pre className="highlight">{``}<code>{`students.name eq Mark `}<b className="conum">{`(1)`}</b>{``}</code>{``}</pre>
</div>
</div>
<div className="colist arabic">
<ol>
<li>
<p>这里也会涉及到"根节点"的概念，例如这里的 <code>students</code>，是基于根节点 <code>Teacher</code> 的，是 <code>Teacher</code> 关联的 <code>Student</code>，类似于: <code>teacher.students</code></p>
</li>
</ol>
</div>
</div>
</div>
</div>
</div>
</div>
            </Document>;
        }