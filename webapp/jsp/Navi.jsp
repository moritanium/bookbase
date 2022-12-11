<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String userName = (String)session.getAttribute("userName");
%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/bookshelf/css/Navi.css">
  <title>ナヴィゲーション画面</title>
 </head>
<nav>
   <a class="none"></a>
   <a href="/bookshelf/SearchAll"><img class="navIcon" src="/bookshelf/pict/home.png"><br>ホーム</a>
   <a href="/bookshelf/jsp/B04.jsp"><img class="navIcon" src="/bookshelf/pict/regi.png"><br>登録</a>
   <a href="/" onclick="sendPost()"><img class="navIcon" src="/bookshelf/pict/out.png"><br>ログアウト</a>
   <a class="navUser"><%=userName%>さん</a>
</nav>
<script type="text/javascript" src="/bookshelf/script/Navi.js"></script>
</html>