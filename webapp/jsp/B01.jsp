<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="MyUtil.BsDTO"%>
<%
ArrayList<BsDTO> bookList = (ArrayList<BsDTO>)request.getAttribute("bookList");
String foundName = (String)request.getAttribute("foundName");
String foundAuthor = (String)request.getAttribute("foundAuthor");
String foundPublisher = (String)request.getAttribute("foundPublisher");
String sucMes = (String)session.getAttribute("sucMes");
session.removeAttribute("sucMes");
%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/bookshelf/css/B01.css">
	<link rel="stylesheet" href="/bookshelf/css/grid.css">
  <title>書籍一覧画面</title>
 </head>
  <%@ include file="/jsp/Navi.jsp" %>
 <body>
<%if(sucMes != null){%>
   <div class="row sucRow">
   <div class="col space" data-xs="24">
	  <p class="sucMes">
	  <%=sucMes%></p></div></div>
 <%}%>
 
 <!-- 検索＆並び替えメニュー -->
 <details>
  <summary>検索メニューを表示</summary>
  <form action="/bookshelf/SearchBook" method="GET" class="center pcForm">
  <div class="row pcRow" data-align="center">
   <div class="col inline">
  	<label class="sort"><input type="radio" name="good" value="goodup">いいね昇順</label>
  	<label class="sort"><input type="radio" name="good" value="gooddown">いいね降順</label>
  	</div><div class="col inline pcCol">
  	<label class="sort"><input type="radio" name="date" value="dateup">発行日昇順</label>
  	<label class="sort"><input type="radio" name="date" value="datedown">発行日降順</label>
  </div></div>
   
   <!-- 検索する書籍名 -->
   <div class="row">
   <div class="col space" data-xs="3"></div>
   <div class="col inline" data-xs="18" data-align="center">
   <label>書籍名：</label>
   <div class="col" data-xs="17">
   <input type="text" name="searchName" value="${empty foundName?'':foundName}"></div></div>
   <div class="col space" data-xs="3"></div></div>
   
   <!-- 検索する著者 -->
   <div class="row top-space">
   <div class="col space" data-xs="3"></div>
   <div class="col inline" data-xs="18" data-align="center">
   <label>著者　：</label>
   <div class="col" data-xs="17">
   <input type="text" name="searchAuthor" value="${empty foundAuthor?'':foundAuthor}"></div></div>
   <div class="col space" data-xs="3"></div></div>
   
   <!-- 検索する出版社 -->
   <div class="row top-space">
   <div class="col space" data-xs="3"></div>
   <div class="col inline" data-xs="18" data-align="center">
   <label>出版社：</label>
   <div class="col" data-xs="17">
   <input type="text" name="searchPublisher" value="${empty foundPublisher?'':foundPublisher}"></div></div>
   <div class="col space" data-xs="3"></div></div>
   
   <div class="row top-space">
   <div class="col" data-xs="24" data-align="middle">
   <input type="submit" value="検索" class="button"></div></div>
   </form>
</details>

<!-- 本棚部分 -->>
   <div class="bookShelf">
	<div class="row">
	<%if(!(bookList.size() == 0)){
		for(int i=0; i < bookList.size(); i++){
			BsDTO books = bookList.get(i);
			if(books.getPicture() == null){%>
			<div class="col" data-xs="five" data-sm="4" data-md="2" data-lg="1">
			<div id="book<%=i%>" class="book"><div class="line"></div></div>
			<div id="<%=i%>" class="frame anim-box popup is-animated"><%=books.getBookName()%></div>
			<input type="hidden" id="hidden<%=i%>" value="<%=books.getId()%>">
			</div>
			<%}else{%>
			<div class="col" data-xs="five" data-sm="4" data-md="2" data-lg="1">
			<div id="book<%=i%>" class="book" id="<%=i%>"><div class="line"></div></div>
			<div id="<%=i%>" class="frame anim-box popup is-animated">
			<img id="photo<%=i%>" class="bookPhoto anim-box popup is-animated" src="/bookshelf/pict/<%=books.getPicture()%>">
			</div><input type="hidden" id="hidden<%=i%>" value="<%=books.getId()%>">
			</div>
	<%}}};%>
   </div></div>
   <script type="text/javascript" src="/bookshelf/script/B01.js"></script>
 </body>
</html>