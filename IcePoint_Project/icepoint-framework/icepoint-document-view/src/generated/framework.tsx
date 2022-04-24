import Document from "../page/Document";

export default function framework() {
            return <Document name="冰点后端框架文档">
                
<div className="sect1">
<h2 id="_0_概述">0 概述</h2>
<div className="sectionbody">
<div className="paragraph">
<p>本文档既针对框架的使用者，也针对框架的开发者进行说明。</p>
</div>
</div>
</div>
<div className="sect1">
<h2 id="_1_整体架构">1 整体架构</h2>
<div className="sectionbody">
<div className="paragraph">
<p>整体框架包含核心模块（Core）、数据层模块（Data）、网络模块（Web）以及自动装配模块（SpringBoot），所有模块均以Spring Framework为基础进行开发。</p>
</div>
<div className="paragraph">
<p>核心模块主要是针对JAVA API以及Spring核心（IoC、AOP等）做的增强、封装以及额外通用的编程功能等</p>
</div>
<div className="paragraph">
<p>数据层模块建立在核心模块之上，对JPA以及ORM框架做的增强、封装等。</p>
</div>
<div className="paragraph">
<p>网络模块建立在数据层模块之上，主要针对网络请求、通用接口、以及根据不同的业务功能抽象出来，做统一的接口规范以及基础实现，主要目的是为了减轻在不同的项目中，实现类似需求时的重复性工作。</p>
</div>
<div className="paragraph">
<p>自动装配模块是基于SpringBoot，不同模块之间根据依赖的情况实现自动化配置，减轻框架应用的复杂度。</p>
</div>
</div>
</div>
            </Document>;
        }