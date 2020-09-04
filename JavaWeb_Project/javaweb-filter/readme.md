### ProjectDescription
Filter的使用
1. 创建类实现javax.servlet.Filter.
2. 重写方法:
    1. init(); //过滤器初始化
    2. doFilter(); //过滤请求
        1.chain.doFilter(request, response); //向下转发请求
    3. destroy(); //销毁过滤器
3. 在web.xml中注册
    ```xml
    <filter>
      <filter-name>filter</filter-name>
      <filter-class>com.filter.FilterTest</filter-class>
    </filter>
    <filter-mapping>
      <filter-name>filter</filter-name>
      <url-pattern>/filter/*</url-pattern>
    </filter-mapping>
    ```
