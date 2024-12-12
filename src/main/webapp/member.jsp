<%@page import="java.util.List" import="openhome.model.Blah" import="java.util.Date" import="java.text.SimpleDateFormat"%>
<meta content='text/html; charset=UTF-8' http-equiv='content-type'>
<title>Gossip 微网志</title>
<link rel='stylesheet' href='css/member.css' type='text/css'>
</head>
<body>

<!-- logo及注销区 -->
<div class='leftPanel'>
<img src='images/caterpillar.jpg' alt='Gossip 微网志' /><br><br>
 <!--  注销链接 -->
<a href='logout.do'>注销</a><br>
</div>

<div style=\"float: left;\">

<!-- 发微博信息区 -->
<form method='post' action='message.do'>
分享新鲜事...<br>
讯息要 140 字以内<br>
<textarea cols='40' rows='5' name='blabla'></textarea>
<br>
<button type='submit'>新增</button>
</form>

<!-- 会员信息显示区 -->
<table style='text-align: left; width: 510px; height: 88px;' border='0' cellpadding='2' cellspacing='2'>
  <tbody>
  <%
    List<Blah> blahs = (List<Blah>) session.getAttribute("blahs");
    for (Blah blah : blahs) {
        // 迭代遍历显示微博信息（带删除超链接）
         Date date = blah.getDate();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
         String dateStr = sdf.format(date);
         out.println("<tr><td>"+ blah.getUsername() +":<br>"+ blah.getTxt() +"<br>" + dateStr
          +"<a href='delete.do?message="+ date.getTime() +"'>删除</a>" + "<hr></td></tr>");
    }
  %>
  </tbody>


</table>
<hr style='width: 100%;height: 1px;' />
</body>
</html>