import Document from "../page/Document";

export default function java() {
            return <Document name="冰点Java开发手册">
                
<div className="sect1">
<h2 id="_0_文档阅读指南">0. 文档阅读指南</h2>
<div className="sectionbody">
<div className="paragraph">
<p>【强制】: 必须按照规范执行</p>
</div>
<div className="paragraph">
<p>【推荐】: 推荐按照规范执行，可以提高团队沟通效率</p>
</div>
<div className="paragraph">
<p>【参考】: 仅供参考，根据个人喜好执行</p>
</div>
</div>
</div>
<div className="sect1">
<h2 id="_1_编程规约">1. 编程规约</h2>
<div className="sectionbody">
<div className="sect2">
<h3 id="_1_1_命名规约">1.1 命名规约</h3>
<div className="paragraph">
<p>1.1.1 【强制】 代码中的命名不能或美元符号开始，不能以下划线或美元符号结束。特殊情况下允许以下划线开始，例如框架级别的请求参数（避免与其他变量重名），以下划算开始的命名必须是私有的。</p>
</div>
<div className="paragraph">
<p>1.1.2 【强制】 代码中的命名不允许使用纯拼音（除了部分国际通用的拼音之外），不允许使用拼音与英文混合的方式，更不允许直接使用中文的方式。</p>
</div>
<div className="paragraph">
<p>1.1.3 【强制】 类名使用UpperCamelCase风格，但某些通用的缩写例外，可以使用全大写或者UpperCamelCase风格，如：DTO/VO/PO/UID/JPA等。</p>
</div>
<div className="paragraph">
<p>1.1.4 【强制】 方法名、参数名、成员变量、局部变量都统一使用lowerCamelCase风格，但特殊情况可以例外使用下划线开头（例如与关键字冲突，另起名字又会引起歧义的），但这种命名的不能是public修饰符。</p>
</div>
<div className="paragraph">
<p>1.1.5 【强制】 常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长。</p>
</div>
<div className="paragraph">
<p>1.1.6 【强制】 异常类命名使用Exception结尾，测试类命名以它要测试的类的名称开始，以Test结尾。</p>
</div>
<div className="paragraph">
<p>1.1.7 【强制】 接口的基础实现或者模版模式的类名使用Abstract或者Base开头，一般情况下建议使用Abstract。</p>
</div>
<div className="paragraph">
<p>1.1.8 【强制】 包名统一使用小写，统一使用单数形式，类名如果含有复数形式，可以在类名上使用复数形式。</p>
</div>
<div className="paragraph">
<p>1.1.9 【推荐】 静态工具类的设计建议一个工具类只有对某一类或者强关联的类相关的方法，避免工具类设计的大而杂。对某一类进行操作的工具类类名建议使用类名+Utils作为结尾，而专注帮助构造某一类对象的工具类用类名的复数形式，如：帮助构造Optional的工具类可以命名为Optionals，针对Optional进行操作的工具类可以命名为OptionalUtils。</p>
</div>
<div className="paragraph">
<p>1.1.10 【推荐】 如果模块、接口、类、方法使用了设计模式，在命名时需体现出具体模式，如：MapperFactory/ServiceHandlerAdapter等。</p>
</div>
<div className="paragraph">
<p>1.1.11 【强制】 Service层暴露的服务必须是接口，内部实现类结尾加Impl进行区别。</p>
</div>
<div className="paragraph">
<p>1.1.12 【强制】 领域模型命名规范：数据表实体放在entity包名内，前端参数接收对象用DTO结尾，前端返回数据用View结尾，数据模型用Model结尾或放在model包名内。</p>
</div>
<div className="paragraph">
<p>1.1.13 【强制】 数据库的表名以及列名统一使用小写下划线命名规则。</p>
</div>
</div>
<div className="sect2">
<h3 id="_1_2_代码设计规范">1.2 代码设计规范</h3>
<div className="paragraph">
<p>1.2.1 【强制】 基础类型的包装类不能使用运算符号进行运算，因为包装类可以赋值为null，此时进行运算会抛出空指针异常，使用equals或者Objects.equals。比较常见的是Boolean的表达式，如：if(someBoolean)，正确的写法可以是：if(Boolean.TRUE.equals(someBoolean))。</p>
</div>
<div className="paragraph">
<p>1.2.2 【强制】 静态工具类必须有且仅有一个无参空构造器，也必须用private修饰符进行修饰。</p>
</div>
<div className="paragraph">
<p>1.2.3 【强制】 类成员的访问修饰符使用最小限度访问控制原则，也就是在合理的使用范围内尽可能的控制访问权限。例如不能被修改的变量或者不能被重载的方法使用final修饰，只在内部使用的成员变量使用private。</p>
</div>
<div className="paragraph">
<p>1.2.4 【推荐】 避免在一行中使用过多的链式调用，不利于阅读，如：list.stream().map().filter().collect(Collectors.toList()).forEach()。</p>
</div>
<div className="paragraph">
<p>1.2.5 【推荐】 创建集合对象的时候，尽可能指定集合初始值大小，减少扩容的次数或者减低集合的大小能够提高性能和减少资源消耗。</p>
</div>
<div className="paragraph">
<p>1.2.6 【推荐】 避免采用取反逻辑运算符，如：if(!true)。</p>
</div>
<div className="paragraph">
<p>1.2.7 【推荐】 尽可能避免代码的复制粘贴，可以把同样的逻辑抽取出来。因为如果复制的代码需要修改，会是一个非常痛苦的过程。</p>
</div>
<div className="paragraph">
<p>1.2.8 【强制】 防止NPE，应该在任何可能产生NPE的代码上做非空验证。</p>
</div>
<div className="paragraph">
<p>1.2.9 【强制】 鉴于尽可能的避免出现NPE，代码设计中遵守以下规范：除了Bean的getter/setter方法以外，默认所有参数以及方法返回值都不能返回null，否则必须在文档进行说明，在什么情况下会返回null，进一步可以使用Spring包下的@Nullable注解进行标记。</p>
</div>
<div className="paragraph">
<p>1.2.10 【推荐】 允许返回null值的方法可以考虑用包装类进行包装，例如Optional，但是不允许包装类本身是null。返回集合时建议用空集合代替返回null。</p>
</div>
<div className="paragraph">
<p>1.2.11 【强制】 任何需要运算的小数类型数据，必须使用BigDecimal或者其他可以精确计算小数运算的类型，禁止使用double或者float进行运算。创建BigDecimal不要使用new BigDecimal，使用BigDecimal.valueOf。</p>
</div>
</div>
<div className="sect2">
<h3 id="_1_5_第三方框架相关代码设计规范">1.5 第三方框架相关代码设计规范</h3>
<div className="paragraph">
<p>1.5.1 【推荐】 设计Spring的Bean的时候，尽可能使用构造器注入的方式，这个也是Spring官方推荐使用的方式。</p>
</div>
<div className="paragraph">
<p>1.5.2 【推荐】 SpringBoot自动装配的配置类以AutoConfiguration结尾，普通配置类以Configuration结尾，配置文件属性类用Properties结尾。</p>
</div>
<div className="paragraph">
<p>1.5.3 【强制】 Controller层不要有复杂的业务逻辑，只负责路由转发，以及基本的参数以及返回值处理过滤等等即可，具体的业务逻辑放在Service层。</p>
</div>
<div className="paragraph">
<p>1.5.4 【推荐】 自定义的配置文件属性不要直接就以功能名开头，这样配置会混乱，如：file.path。可以设计一个前缀进行分类，例如：project.file.path。</p>
</div>
<div className="paragraph">
<p>1.5.5 【参考】 Mybatis的Mapper接口和xml可以放在同一个包类，这样有利于开发人员查找sql以及归类。</p>
</div>
</div>
<div className="sect2">
<h3 id="_1_4_注释规范">1.4 注释规范</h3>
<div className="paragraph">
<p>1.4.1 【强制】 所有接口类和抽象方法必须编写文档。</p>
</div>
<div className="paragraph">
<p>1.4.2 【强制】 不能使用过时的类或方法，如果发现方法过时，需要去考证替换的方案，并使用更新后的方案。如果对代码标注过时，也必须在文档上提供替代方案的相关信息。</p>
</div>
</div>
</div>
</div>
            </Document>;
        }