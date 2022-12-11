<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="MyUtil.BsDTO"%>
<%
ArrayList<BsDTO> bookList = (ArrayList<BsDTO>)request.getAttribute("bookList");
BsDTO books = bookList.get(0);
String auth = books.getAuthor();
String pub = books.getPublisher();
String pubD = books.getPublishDate();
%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/bookshelf/css/B03.css">
    <link rel="stylesheet" href="/bookshelf/css/grid.css">
  <title>書籍詳細画面</title>
 </head>
  <%@ include file="/jsp/Navi.jsp" %>
 <body>
   <div class="bookShelf">
   
   <!-- 画像 -->
   <div class="row heights">
   <div class="col heights" data-align="center" data-xs="24" data-md="8" data-lg="7">
   <%if(books.getPicture() == null){%>
   <div class="noImage">no<br>image</div>
   <%}else{%>
   <img src="/bookshelf/pict/<%=books.getPicture()%>">
   <%}%>
   </div>
   
   <!-- 画像以外 -->
   <div class="col"  data-xs="24" data-md="16" data-lg="17">
   <div class="row" data-space="normal">
   <div class="col top-space" data-xs="24"></div>
   <div class="col top-font"  data-xs="24"><%=books.getBookName()%></div>
   
   <div class="col"  data-xs="24">
   <div class="row">
   <div class="col bold" data-xs="6" data-md="5" data-lg="3">著者　：</div>
   <div class="col" data-xs="18" data-md="19" data-lg="21"><%=auth == null ? "": auth%></div>
   </div></div>
   
   <div class="col" data-xs="24">
   <div class="row">
   <div class="col bold" data-xs="6" data-md="5" data-lg="3">出版社：</div>
   <div class="col" data-xs="18" data-md="19" data-lg="21"><%=pub == null ? "": pub%></div>
   </div></div>
   
   <div class="col" data-xs="24">
   <div class="row">
   <div class="col bold" data-xs="6" data-md="5" data-lg="3">発行日：</div>
   <div class="col" data-xs="18" data-md="19" data-lg="21"><%=pubD == null ? "": pubD%></div>
   </div></div>
   
   <div class="col" data-xs="24">
   <div class="row">
   <div class="col bold" data-xs="6" data-md="5" data-lg="3">いいね：</div>
   <div class="col" data-xs="18" data-md="19" data-lg="21"><%=books.getGood()%></div>
   </div></div>
   </div></div></div></div>
 </body>
</html>