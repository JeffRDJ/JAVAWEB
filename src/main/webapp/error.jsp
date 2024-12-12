<%@ page import="java.util.List" %>
  <head
     <meta content='text/html; charset=UTF-8'http-equiv='content-type'>
<title>新增会员失败</title>
</head>
<body>
<h1>新增会员失败</h1>
<ul style='color: rgb(255, 0, 0);'>

        <% List<String> errors =(List<String>) request.getAttribute("errors");%>
        反馈错误信息
       <%for(String error:errors){
            out.println("<li>"+error+"</li>");
        }
            %>
</ul>
<a href='register.html'>返回注册页面</a>
</body>
</html>
