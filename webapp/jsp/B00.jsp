<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String failMes = (String)request.getAttribute("failMes"); %>

<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/bookshelf/css/B00.css">
 <head>
  <title>ログイン画面</title>
 </head>
 <body>
	<div class="topfont left-space">One's<br>Secret<br>Shelf</div>
	<div class="backbox center">
      <form action="/bookshelf/Login" method="POST" class="center top-space pcForm">
      <!-- PC用のフォーム -->
      <table class="center">
      <tr>
      <th><label>ユーザー名</label></th>
      <th class="form-space"><label class="form-space">パスワード</label></th>
      </tr>
      <tr>
      <td><input type="text" name="userName" id="userName" required></td>
      <td class="form-space"><input type="password" name="pass" id="pass" required></td>
      <td class="button-space"><input type="submit" value="ログイン" class="button"></td>
      </tr>
      </table>
   </form>
   
   <!-- スマホ用のフォーム -->
   <form action="/bookshelf/Login" method="POST" class="center">
      <div class="spForm top-space">
      <label>ユーザー名</label><br>
      <input type="text" name="userName" id="userName" required><br>
      <label>パスワード</label><br>
      <input type="password" name="pass" id="pass" required><br><br>
      <input type="submit" value="ログイン" class="button">
      </div>
   </form>
   <%if(failMes != null){%>
	  <p class="failMes">
	  <%=failMes%></p>
   <%}%>
   </div>
 </body>
</html>