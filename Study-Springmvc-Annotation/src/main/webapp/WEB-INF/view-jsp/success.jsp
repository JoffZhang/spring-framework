<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/10
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
自定义视图解析器

<h1>success</h1>


@Override
public void configureViewResolvers(ViewResolverRegistry registry) {
//默认所有页面jsp("/WEB-INF/", ".jsp")
registry.jsp("WEB-INF/view-jsp/",".jsp");
}
</body>
</html>
