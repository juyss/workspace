import Document from "../page/Document";

export default function frameworkData() {
            return <Document name="数据层文档">
                
<div className="sect1">
<h2 id="_0_概述">0. 概述</h2>
<div className="sectionbody">
<div className="paragraph">
<p>数据层框架核心</p>
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
    <groupId>com.icepoint.framework.boot</groupId>
    <artifactId>spring-boot-starter-icepoint-data</artifactId>
    <version>0.1-SNAPSHOT</version>
</dependency>`}</code>{``}</pre>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}<div className="sect1">{`
`}<h2 id="_2_领域模型">{`2. 领域模型`}</h2>{`
`}<div className="sectionbody">{`
`}<div className="sect2">{`
`}<h3 id="_2_1_identifiableid">{`2.1 Identifiable<ID>`}</h3>{`
`}<div className="paragraph">{`
`}<p>{`所有数据实体的顶级接口，只有一个方法 `}<code>{`getId()`}</code>{`。`}</p>{`
`}</div>{`
`}</div>{`
`}<div className="sect2">{`
`}<h3 id="_2_1_baseentity_longbaseentity_stringbaseentity">{`2.1 BaseEntity / LongBaseEntity / StringBaseEntity`}</h3>{`
`}<div className="paragraph">{`
`}<p>{`所有数据实体的顶级父类，`}<code>{`Identifiable`}</code>{` 的基础实现，另外实现了JPA的接口 `}<code>{`Persistable<ID>`}</code>{`，以及序列化接口 `}<code>{`Serializable`}</code>{`。`}</p>{`
`}</div>{`
`}<div className="paragraph">{`
`}<p>{``}<code>{`LongBaseEntity`}</code>{` 和 `}<code>{`StringBaseEntity`}</code>{` 分别是已定义好泛型为Long或者String的 `}<code>{`BaseEntity`}</code>{`。`}</p>{`
`}</div>{`
`}</div>{`
`}<div className="sect2">{`
`}<h3 id="_2_2_stdentityid_uid_longstdentity_stringstdentity">{`2.2 StdEntity<ID, UID> / LongStdEntity / StringStdEntity`}</h3>{`
`}<div className="paragraph">{`
`}<p>{`基于 `}<code>{`BaseEntity`}</code>{` 做的扩展，包含了一个应用标准实体类的属性，除了 `}<code>{`id`}</code>{` 以外新增了属性：`}</p>{`
`}</div>{`
`}<div className="ulist">{`
`}<ul>{`
`}<li>{`
`}<p>{``}<code>{`platformId`}</code>{`: 平台id`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`appId`}</code>{`: 应用id`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`ownerId`}</code>{`: 所有者id`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`createUserId`}</code>{`: 创建用户id`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`createTime`}</code>{`: 创建时间`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`updateUserId`}</code>{`: 更新用户id`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`updateTime`}</code>{`: 更新时间`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`deleted`}</code>{`: 逻辑删除标识，false是未删，true是已删除`}</p>{`
`}</li>{`
`}</ul>{`
`}</div>{`
`}<div className="paragraph">{`
`}<p>{``}<code>{`UID`}</code>{` 是用户实体的id类型。`}</p>{`
`}</div>{`
`}<div className="paragraph">{`
`}<p>{``}<code>{`LongStdEntity`}</code>{` 和 `}<code>{`StringStdEntity`}</code>{` 分别是已定义好泛型为Long或者String的 `}<code>{`StdEntity`}</code>{`。`}</p>{`
`}</div>{`
`}</div>{`
`}<div className="sect2">{`
`}<h3 id="_2_3_ordered">{`2.3 Ordered`}</h3>{`
`}<div className="paragraph">{`
`}<p>{`排序顶级接口，这个是Spring自带的接口，其他需要特定顺序的类都使用这个接口进行排序。`}</p>{`
`}</div>{`
`}</div>{`
`}<div className="sect2">{`
`}<h3 id="_2_4_treenode">{`2.4 TreeNode`}</h3>{`
`}<div className="paragraph">{`
`}<p>{`树形结构的包装类，底层存储用的是Map，构建属性结构列表可以使用工具类 `}<code>{`TreeUtils`}</code>{`。`}</p>{`
`}</div>{`
`}</div>{`
`}<div className="sect2">{`
`}<h3 id="_2_5_jsonanyproperties">{`2.5 JsonAnyProperties`}</h3>{`
`}<div className="paragraph">{`
`}<p>{`抽象类，继承此类可以往里面放置任意属性名的属性，并且JSON序列化时会放到该类同级属性下，底层存储用的是Map。`}</p>{`
`}</div>{`
`}<div className="exampleblock">{`
`}<div className="title">{`Example 2. JsonAnyProperties`}</div>{`
`}<div className="content">{`
`}<div className="listingblock">{`
`}<div className="content">{`
`}<pre className="highlight">{``}<code>{`public class ABean extends JsonAnyProperties {

    private String name;

    private Integer number;

    // getters setters ...

}

...

ABean bean = new ABean("aName", 5);

bean.setProperty("extraProperty", "I am inevitable");

// 序列化结果
{
    "name": "aName",
    "number": 5,
    "extraProperty": "I am inevitable"
}`}</code>{``}</pre>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
<div className="sect1">
<h2 id="_3_dao">3. DAO</h2>
<div className="sectionbody">
<div className="paragraph">
<p>目前数据持久层结合了Mybatis Plus和Spring Data Jpa两个框架。</p>
</div>
<div className="sect2">
<h3 id="_3_1_jpa">3.1 JPA</h3>
<div className="sect3">
<h4 id="_3_1_1_baserepositoryt_id_longbaserepositoryt_stringbaserepositoryt">3.1.1 BaseRepository&lt;T, ID&gt; / LongBaseRepository&lt;T&gt; / StringBaseRepository&lt;T&gt;</h4>
<div className="paragraph">
<p>JPA数据访问的顶层接口，实现了 <code>JpaRepository</code>、 <code>JpaSpecificationExecutor</code> 和 <code>QuerydslPredicateExecutor</code> 三个接口，具有基本的增删改查，分页查询、复杂条件查询、Querydsl查询等功能。</p>
</div>
<div className="paragraph">
<p><code>LongBaseRepository&lt;T&gt;</code> 和 <code>StringBaseRepository&lt;T&gt;</code> 分别是定义好的Long型或String型id的 <code>BaseRepository</code>。</p>
</div>
</div>
<div className="sect3">
<h4 id="_3_1_2_stdrepositoryt_id_longstdrepositoryt_id_stringstdrepositoryt_id">3.1.2 StdRepository&lt;T, ID&gt; / LongStdRepository&lt;T, ID&gt; / StringStdRepository&lt;T, ID&gt;</h4>
<div className="paragraph">
<p>增加了对逻辑删除的支持，所有查询方法都增加多一个参数 <code>boolean deleted</code> 的同名方法，如果不传，会查询所有相关记录，如果为true，会查询所有已被逻辑删除的相关记录，如果为false，则会查询所有未被逻辑删除的相关记录。</p>
</div>
</div>
</div>
<div className="sect2">
<h3 id="_3_2_mybatis">3.2 Mybatis</h3>
<div className="sect3">
<h4 id="_3_2_1_repositorymapper">3.2.1 RepositoryMapper</h4>
<div className="paragraph">
<p>Mybatis的数据访问层顶级接口。在Mybatis Plus的 <code>BaseMapper</code> 的基础上增加了对 <code>Pageable</code> 以及 <code>Sort</code> 的查询方法，</p>
</div>
</div>
<div className="sect3">
<h4 id="_3_2_2_paginationadapterinterceptor">3.2.2 PaginationAdapterInterceptor</h4>
<div className="paragraph">
<p>对 <code>Pageable</code> 增加支持的查询拦截器，适用于查询方法中 <code>Pageable</code> 或者 <code>Sort</code> 参数的方法。目前有对PageHelper和Mybatis Plus分页的支持，如果需要增加自定义的分页方式，新增 <code>AbstractPaginationInterceptor</code> 的子类，并注册为Spring Bean即可。</p>
</div>
<div className="literalblock">
<div className="content">
<pre>`AbstractPaginationInterceptor` 的实现方式可以参考 `MybatisPlusSupportInterceptor` 或者 `PageHelperSupportInterceptor`。</pre>
</div>
</div>
</div>
</div>
</div>
</div>
<div className="sect1">
<h2 id="_4_事务相关transactionaspect_readtransaction_writetransaction">4. 事务相关：TransactionAspect / @ReadTransaction / @WriteTransaction</h2>
<div className="sectionbody">
<div className="paragraph">
<p>目前会根据业务层和数据访问执行的方法名，自动添加事务只读或写入事务，后面要做成可配置的，待完善，目前只读和写入的方法分别是：</p>
</div>
<div className="ulist">
<ul>
<li>
<p>只读： "find", "get", "query", "select", "list", "page", "tree", "count", "exists"</p>
</li>
<li>
<p>写入： "save", "add", "create", "new", "insert", "upload", "update", "set", "change", "delete", "remove"</p>
</li>
</ul>
</div>
<div className="paragraph">
<p>@ReadTransaction是只读事务的注解。 @WriteTransaction是写入事务的注解。</p>
</div>
</div>
</div>
            </Document>;
        }