import Document from "../page/Document";

export default function frameworkCore() {
            return <Document name="框架核心文档">
                
<div className="sect1">
<h2 id="_0_概述">0. 概述</h2>
<div className="sectionbody">
<div className="paragraph">
<p>框架核心功能模块</p>
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
    <artifactId>icepoint-core</artifactId>
    <version>0.1-SNAPSHOT</version>
</dependency>`}</code>{``}</pre>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}<div className="sect1">{`
`}<h2 id="_2_io">{`2. IO`}</h2>{`
`}<div className="sectionbody">{`
`}<div className="sect2">{`
`}<h3 id="_2_1_序列化相关">{`2.1 序列化相关`}</h3>{`
`}<div className="sect3">{`
`}<h4 id="_2_1_1_序列化器基础接口serializer_deserializer_standardserializer">{`2.1.1 序列化器基础接口：Serializer / Deserializer / StandardSerializer`}</h4>{`
`}<div className="ulist">{`
`}<ul>{`
`}<li>{`
`}<p>{``}<code>{`Serializer`}</code>{` 只有一个序列化方法 `}<code>{`serialize()`}</code>{`，是序列化器的顶层接口。`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`Deserializer`}</code>{` 只有一个反序列化方法 `}<code>{`deserialize()`}</code>{`，是反序列化器的顶层接口。`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`Serializer`}</code>{` 和 `}<code>{`Deserializer`}</code>{` 都继承了接口 `}<code>{`SerializationExceptionHandlerProvider`}</code>{`，这个接口只有一个方法 `}<code>{`getExceptionHandler()`}</code>{`，用于处理序列化和反序列化中出现的异常。`}</p>{`
`}</li>{`
`}<li>{`
`}<p>{``}<code>{`StandardSerializer<S>`}</code>{` 包含序列化方法和反序列化方法，继承自 `}<code>{`Serializer`}</code>{` 和 `}<code>{`Deserializer`}</code>{`，`}<code>{`S`}</code>{` 是真正实现功能对象的类型。新增加了获取真正实现序列化和反序列化功能的对象的方法 `}<code>{`getSource()`}</code>{`；新增了支持泛型反序列的方法；新增了直接反序列化成字符串或者直接将字符串反序列的方法。`}</p>{`
`}</li>{`
`}</ul>{`
`}</div>{`
`}<div className="sidebarblock">{`
`}<div className="content">{`
`}<div className="paragraph">{`
`}<p>{`开发新的序列化器时，建议实现StandardSerializer，因为它的功能是基本齐全的，使用的时候会比较方便。`}</p>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}</div>{`
`}<div className="sect3">{`
`}<h4 id="_2_1_2_基于jackson的实现_objectmapperserializer">{`2.1.2 基于Jackson的实现： ObjectMapperSerializer`}</h4>{`
`}<div className="paragraph">{`
`}<p>{`针对jackson的 `}<code>{`ObjectMapper`}</code>{`，做了基础的实现，根据构建时传入的 `}<code>{`ObjectMapper`}</code>{` 的具体实现，即可实现不同序列化功能。`}</p>{`
`}</div>{`
`}</div>{`
`}<div className="sect3">{`
`}<h4 id="_2_1_3_静态工具类serializers">{`2.1.3 静态工具类：Serializers`}</h4>{`
`}<div className="paragraph">{`
`}<p>{`用于获取不同类型序列化器的工具类，目前以配置的有针对XML的和JSON的序列化器。`}</p>{`
`}</div>{`
`}<div className="exampleblock">{`
`}<div className="title">{`Example 2. Serializers`}</div>{`
`}<div className="content">{`
`}<div className="listingblock">{`
`}<div className="content">{`
`}<pre className="highlight">{``}<code>{`Serializers.json().serialize(outputStream, object); `}<b className="conum">{`(1)`}</b>{`

Serializers.xml().deserialize(inputStream, ValueType.class); `}<b className="conum">{`(2)`}</b>{``}</code>{``}</pre>
</div>
</div>
<div className="colist arabic">
<ol>
<li>
<p>使用JSON序列化器序列化对象</p>
</li>
<li>
<p>使用XML序列化器反序列化对象</p>
</li>
</ol>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
<div className="sect1">
<h2 id="_3_辅助功能类">3. 辅助功能类</h2>
<div className="sectionbody">
<div className="sect2">
<h3 id="_3_1_timerange">3.1 TimeRange</h3>
<div className="paragraph">
<p>这是一个包含开始时间和结束时间的对象，用于表示一个准确的时间范围，内含时间格式化器，可以直接获取格式化后的时间字符串，也可以获取开时间和结束时间之间的时间长度对象 <code>Duration</code>。</p>
</div>
<div className="exampleblock">
<div className="title">Example 3. TimeRange</div>
<div className="content">
<div className="listingblock">
<div className="content">
<pre className="highlight">{``}<code>{`TimeRange timeRange = TimeRange.of(start, end); `}<b className="conum">{`(1)`}</b>{`

LocalDateTime start = timeRange.getStart(); `}<b className="conum">{`(2)`}</b>{`

long endMills = timeRange.getEndMills(); `}<b className="conum">{`(3)`}</b>{`

timeRange.setDefaultFormatter(DateTimeFormatter.ISO_DATE_TIME); `}<b className="conum">{`(4)`}</b>{`

String startStr = timeRange.getStartStr(); `}<b className="conum">{`(5)`}</b>{`

String startStr = timeRange.getEndStr(DateTimeFormatter.ISO_DATE); `}<b className="conum">{`(6)`}</b>{`

Duration duration = timeRange.getDuration(); `}<b className="conum">{`(7)`}</b>{``}</code>{``}</pre>
</div>
</div>
<div className="colist arabic">
<ol>
<li>
<p>用开始时间 <code>start</code> 和结束时间 <code>end</code> 构建一个 <code>TimeRange</code> 对象。</p>
</li>
<li>
<p>获取开始时间。</p>
</li>
<li>
<p>获取结束时间戳。</p>
</li>
<li>
<p>设置默认的时间格式化器为 <code>DateTimeFormatter.ISO_DATE_TIME</code>，即便不设置自身也会自带一个时间格式化器，格式是： <code>yyyy-MM-dd HH:mm:ss</code>。</p>
</li>
<li>
<p>使用默认的时间格式化器，获取开始时间字符串。</p>
</li>
<li>
<p>使用也定的时间格式化器 <code>DateTimeFormatter.ISO_DATE</code>，获取结束时间字符串。</p>
</li>
<li>
<p>获取开始时间和结束时间之间的时长对象 <code>Duration</code>。</p>
</li>
</ol>
</div>
</div>
</div>
</div>
<div className="sect2">
<h3 id="_3_2_timeranges">3.2 TimeRanges</h3>
<div className="paragraph">
<p>方便构造 <code>TimeRange</code> 对象的工具类。</p>
</div>
<div className="exampleblock">
<div className="title">Example 4. TimeRanges</div>
<div className="content">
<div className="listingblock">
<div className="content">
<pre className="highlight">{``}<code>{`LocalDateTime dateTime = LocalDateTime.now();

TimeRange year = TimeRanges.year(dateTime); `}<b className="conum">{`(1)`}</b>{`

TimeRange month = TimeRanges.month(dateTime); `}<b className="conum">{`(2)`}</b>{`

TimeRange week = TimeRanges.week(dateTime); `}<b className="conum">{`(3)`}</b>{`

TimeRange workday = TimeRanges.workday(dateTime); `}<b className="conum">{`(4)`}</b>{`

TimeRange day = TimeRanges.day(dateTime); `}<b className="conum">{`(5)`}</b>{`

TimeRange hour = TimeRanges.hour(dateTime); `}<b className="conum">{`(6)`}</b>{``}</code>{``}</pre>
</div>
</div>
<div className="colist arabic">
<ol>
<li>
<p>构建包含 <code>dateTime</code> 对应的年份的 <code>TimeRange</code> 对象</p>
</li>
<li>
<p>构建包含 <code>dateTime</code> 对应的月份的 <code>TimeRange</code> 对象</p>
</li>
<li>
<p>构建包含 <code>dateTime</code> 对应的周的 <code>TimeRange</code> 对象</p>
</li>
<li>
<p>构建包含 <code>dateTime</code> 对应的工作日的 <code>TimeRange</code> 对象</p>
</li>
<li>
<p>构建包含 <code>dateTime</code> 对应的日期的 <code>TimeRange</code> 对象</p>
</li>
<li>
<p>构建包含 <code>dateTime</code> 对应的小时的 <code>TimeRange</code> 对象</p>
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