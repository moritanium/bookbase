import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import MyUtil.BsDAO;
import MyUtil.BsDTO;

//書籍登録画面から登録ボタンをクリック時に呼び出し
@WebServlet("/Registar")
@MultipartConfig()
public class Registar extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//jspの入力データの文字コードの指定
		req.setCharacterEncoding("UTF-8");
		//出力する文字コードの指定
		res.setContentType("text/html; charset=UTF-8");
		
		//セッション取得
		HttpSession ses = req.getSession();
		
		int failNum = 1;
		
		String bookName = null;
		String author = null;
		String publisher = null;
		String publishDate = null;
		int good = 0;
		String picName = null;
		String failMes = null;
		
		bookName = req.getParameter("bookName");
		author = req.getParameter("author");
		publisher = req.getParameter("publisher");
		publishDate = req.getParameter("publishDate");
		Part part =req.getPart("picture");

		//dtoに書籍情報を格納
		BsDTO dto = new BsDTO();
		
		//Stringの前後に空白があるか検査し、空文字になったらnullに置き換える
		bookName = checkSpace(bookName);
		author = checkSpace(author);
		publisher = checkSpace(publisher);
		publishDate = checkSpace(publishDate);
		
		//一か所でも不正な値があれば処理終了
		inputCheck: while(failNum == 1) {
			//goodが1-10か検査
	        for(int i = 1; i < 11; i++) {
	            String pattern = Integer.toString(i);
	            if(req.getParameter("good").matches(pattern)){
	            	good = Integer.parseInt(req.getParameter("good"));
	                break;
	            }
	            if(i == 10 && !(req.getParameter("good").isEmpty())) {
	            	failMes = "いいねの値が不正です。";
	            	failNum = 0;
	            	break inputCheck;
	            }
	        }
			
			//必須の書籍名のみnull検査
			if(bookName == null) {
				failMes = "書籍名は必須です。";
				failNum = 0;
				break;
			}
			
	        //発行日以外のStringの文字数検査
	        if(100 < bookName.length()) {
	        	failMes = "書籍名の文字数は100以下にしてください。";
	        	failNum = 0;
	        	break;
	        }
	        if(author != null && 40 < author.length()) {
	        	failMes = "著者の文字数は40以下にしてください。";
	        	failNum = 0;
	        	break;
	        }
	        if(publisher != null && 20 < publisher.length()) {
	        	failMes = "出版社の文字数は20以下にしてください。";
	        	failNum = 0;
	        	break;
	        }
	        
	        //ファイルサイズ検査
	        if(3145728 < part.getSize()) {
	        	failMes = "画像のファイルサイズは3MB以下にしてください。";
	        	failNum = 0;
	        	break;
	        }
	        
	        //発行日の値検査
	        if(publishDate != null && !(publishDate.isEmpty())) {
	        	Pattern p = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
		        Matcher m = p.matcher(publishDate);
		        if(m.find() == false) {
		        	failMes = "発行日の値が不正です。";
		        	failNum = 0;
		        	break;
		        }
	        }
	        
	        //画像のファイル名を変更
	        if(part.getSize() != 0) {
	        	BsDAO dao = new BsDAO();
	        	int id = dao.countId() + 1;
	        	
	        	//画像か検査
	        	InputStream file = part.getInputStream();
	        	BufferedImage Bfile = ImageIO.read(file);
	        	if(Bfile != null) {
	        		
	        	}else {
	        		failMes = "画像を選択してください。";
		        	failNum = 0;
		        	break inputCheck;
	        	}
	        	
	        	//画像の拡張子検査
	        	String fileName = part.getSubmittedFileName();
	        	String checkExtension = fileName.toUpperCase();
	        	if(checkExtension.endsWith(".JPG")
	        	|| checkExtension.endsWith(".JEPG")
	        	|| checkExtension.endsWith(".PNG")
	        	|| checkExtension.endsWith(".BMP")
	        	|| checkExtension.endsWith(".GIF")
	        	){
	        		//ファイル名をid+拡張子に変更
	        		String extension = fileName.substring(fileName.lastIndexOf("."));
	        		picName = id + extension;
	        	}else {
	        		failMes = "画像を指定の拡張子にしてください。";
		        	failNum = 0;
		        	break inputCheck;
	        	}
	        }
	        
	        //ループ処理を終了
	        break;
		}
		
		//検査に異常がない場合の処理
		if(failNum == 1) {
			//MySQLインジェクション対策
			bookName = MySQLsanitize(bookName);
			author = MySQLsanitize(author);
			publisher = MySQLsanitize(publisher);
			
			dto.setBookName(bookName);
			dto.setAuthor(author);
			dto.setPublisher(publisher);
			dto.setPublishDate(publishDate);
			dto.setGood(good);
			dto.setPicture(picName);
			
			//daoオブジェクト化
			BsDAO dao = new BsDAO();
			//書籍登録メソッド呼び出し
			failNum = dao.insertBook(dto);
			//画像をアップロード
			if(part.getSize() != 0) {
			String path = getServletContext().getRealPath("/pict");
			part.write(path + File.separator + picName);
			}
			
			String sucMes = failNum+"件の書籍の登録に成功しました。";
			
			ses.setAttribute("sucMes", sucMes);
			
			//書籍を全検索
			//リロード対策のためにリダイレクトで遷移
			res.sendRedirect("/bookshelf/SearchAll");
		
		//検査に異常がある場合の処理
		}else if(failNum == 0){
			bookName = XSSsanitize(bookName);
			author = XSSsanitize(author);
			publisher = XSSsanitize(publisher);
			publishDate = XSSsanitize(publishDate);
			
			//リクエストスコープにリストをセット
			req.setAttribute("bookName", bookName);
			req.setAttribute("author", author);
			req.setAttribute("publisher", publisher);
			req.setAttribute("publishDate", publishDate);
			req.setAttribute("good", good);
			req.setAttribute("failMes", failMes);
			
			//登録画面に遷移
			req.getRequestDispatcher("/jsp/B04.jsp").forward(req, res);
		}
	}
	
	//文字列の前後にある空白のみを削除
	//nullの場合はそのまま返却
	protected String checkSpace(String target) {
		if(target != null) {
			List<String> words = new ArrayList<String>(Arrays.asList(target.split("")));

			for(int i = 0; i < words.size(); i++) {
				if(words.get(i).isEmpty()) {
					words.remove(i);
					i--;
				}else{
					break;
				}
			}
			
            for(int i = words.size() -1; 0 <= i; i--) {
				if(words.get(i).equals(" ") || words.get(i).equals("　")) {
					words.remove(i);
				}else{
					break;
				}
			}
			
			target = String.join("", words);
			//空文字のみになったらnullにする
			if(target.length() == 0){
				target = null;
			}
		}
		
		return target;
	}
	
	//XSS対策
	protected String XSSsanitize(String target) {
		if(target != null) {	
			target = target.replaceAll("&", "&amp;");
			target = target.replaceAll("<", "&lt;");
			target = target.replaceAll(">", "&gt;");
			target = target.replaceAll("\"", "&quot;");
			target = target.replaceAll("'", "&#39;");
		}
		return target;
	}
	
	//SQLインジェクション対策
	protected String MySQLsanitize(String target) {
		if(target != null) {
			target = target.replaceAll("\\\\", "\\\\\\\\");
			target = target.replaceAll("'", "''");
			target = target.replaceAll("\"", "\\\"");
		}
		return target;
	}
}
