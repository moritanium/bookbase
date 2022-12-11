import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MyUtil.BsDAO;
import MyUtil.BsDTO;

//書籍一覧画面から書籍を検索時に呼び出し
@WebServlet("/SearchBook")
public class SearchBook extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//jspの入力データの文字コードの指定
		req.setCharacterEncoding("UTF-8");
		//出力する文字コードの指定
		res.setContentType("text/html; charset=UTF-8");
		
		//詳細を表示する書籍のID取得
		String name = req.getParameter("searchName");
		String author = req.getParameter("searchAuthor");
		String publisher = req.getParameter("searchPublisher");
		
		//サニタイズ
		String tName = MySQLsanitize(name);
		String tAuthor = MySQLsanitize(author);
		String tPublisher = MySQLsanitize(publisher);
		
		//daoオブジェクト化
		BsDAO dao = new BsDAO();
		//ユーザーの照合メソッド呼び出し
		ArrayList<BsDTO> bookList = dao.searchBook(tName, tAuthor, tPublisher);
		
		name = XSSsanitize(name);
		author = XSSsanitize(author);
		publisher = XSSsanitize(publisher);
		
		//リクエストスコープにリストをセット
		req.setAttribute("bookList", bookList);
		req.setAttribute("foundName", name);
		req.setAttribute("foundAuthor", author);
		req.setAttribute("foundPublisher", publisher);
		
		//一覧画面に遷移
		req.getRequestDispatcher("/jsp/B01.jsp").forward(req, res);
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
}
