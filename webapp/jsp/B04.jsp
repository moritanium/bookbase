<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Objects"%>
<%@ page import="MyUtil.BsDTO"%>
<% String failMes = (String)request.getAttribute("failMes"); %>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/bookshelf/css/B04.css">
    <link rel="stylesheet" href="/bookshelf/css/grid.css">
  <title>書籍登録画面</title>
 </head>
  <%@ include file="/jsp/Navi.jsp" %>
 <body>
   <div class="bookShelf">
   <%if(failMes != null){%>
   <div class="row failRow">
   <div class="col space" data-xs="24">
	  <p class="failMes">
	  <%=failMes%></p></div></div>
   <%}%>
   <form action="/bookshelf/Registar" method="POST" enctype="multipart/form-data">
   
   <!-- 書籍名 -->
   <div class="row <%if(failMes != null){%>failRow <%}%>">
   <div class="col space" data-xs="2" data-md="3"></div>
   <div class="col inline" data-xs="20" data-md="18">
   <label>書籍名：</label>
   <div class="col" data-xs="18">
   <textarea name="bookName" id="bookName" required>${empty bookName?'':bookName}</textarea>
   </div></div></div>
   
   <!-- 著者 -->
   <div class="row">
   <div class="col space" data-xs="2" data-md="3"></div>
   <div class="col inline" data-xs="20" data-md="18">
   <label>著者　：</label>
   <div class="col" data-xs="18">
   <input type="text" name="author" id="author" value="${empty author?'':author}">
   </div></div></div>
   
   <!-- 出版社 -->
   <div class="row">
   <div class="col space" data-xs="2" data-md="3"></div>
   <div class="col inline" data-xs="20" data-md="18">
   <label>出版社：</label>
   <div class="col" data-xs="18">
   <input type="text" name="publisher" id="publisher" value="${empty publisher?'':publisher}">
   </div></div></div>
   
   <!-- 発行日 -->
   <div class="row">
   <div class="col space" data-xs="2" data-md="3"></div>
   <div class="col inline" data-xs="20" data-md="18">
   <label>発行日：</label>
   <div class="col" data-xs="18">
   <input type="date" name="publishDate" id="publishDate" value="${empty publishDate?'':publishDate}">
   </div></div></div>
   
   <!-- おすすめ度 -->
   <div class="row">
   <div class="col space" data-xs="2" data-md="3"></div>
   <div class="col inline" data-xs="20" data-md="18">
   <label>いいね：</label>
   <div class="col" data-xs="18">
   <input type="number" name="good" id="good" min="1" max="10" value="${good == 0?'':good}">
   </div></div></div>
   
   <!-- 画像 -->
   <div class="row">
   <div class="col space" data-xs="2" data-md="3"></div>
   <div class="col inline" data-xs="20" data-md="18">
   <label>画像　：</label>
   <label class="fileDeco">ファイル選択
   <input type="file" name="picture" id="picture" accept="image/*">
   </label>
   <div id="preTxL" class="noPreviewL">選択されていません</div></div>
   <div class="col space" data-xs="2" data-md="3"></div>
   <div class="col space noPreviewS" data-xs="2" data-md="3"></div>
   <div id="preTxS" class="noPreviewS">選択されていません</div>
   </div>
   
   <!-- プレビュー表示部分 登録ボタンと同じrow -->
   <div class="row">
   <div class="col space" data-xs="2" data-md="3"></div>
   <div class="col inline" data-xs="20" data-md="18">
   <div class="txSpace"></div>
   <div class="col  inline" data-xs="18">
   <div class="col" data-align="top" data-xs="12" data-md="16" data-lg="19">
   <img src="" id="preImage"></div>
	
	<!-- 登録ボタン -->
	<div class="col" data-align="bottom" data-xs="12" data-md="8" data-lg="5">
	<input type="submit" value="登録" class="button">
	</div></div></div></div>
	
   </form></div>
   
	<script type="text/javascript" src="/bookshelf/script/B04.js"></script>
 </body>
</html>