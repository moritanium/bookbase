import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MyUtil.BsDAO;
import MyUtil.BsDTO;

//書籍一覧画面から書籍をクリック時に呼び出し
@WebServlet("/DispDetail")
public class DispDetail extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//jspの入力データの文字コードの指定
		req.setCharacterEncoding("UTF-8");
		//出力する文字コードの指定
		res.setContentType("text/html; charset=UTF-8");
		
		//詳細を表示する書籍のID取得
		String bookId = req.getParameter("id");
		
		//bookIdが数値か検査
		String pattern = "^[0-9]+$";
		Pattern p = Pattern.compile(pattern); 
	    Matcher m = p.matcher(bookId);
		
	    //bookIdが数値の場合
		if(m.matches()){
			//daoオブジェクト化
			BsDAO dao = new BsDAO();
			//ユーザーの照合メソッド呼び出し
			ArrayList<BsDTO> bookList = dao.searchDetail(bookId);
			//リクエストスコープにリストをセット
			req.setAttribute("bookList", bookList);
			//詳細画面に遷移
			req.getRequestDispatcher("/jsp/B03.jsp").forward(req, res);
		
		//bookIdが数値でなかった場合
		}else{
			String sucMes = "選択された書籍情報が取得できませんでした。";
			req.setAttribute("sucMes", sucMes);
			req.getRequestDispatcher("/jsp/B01.jsp").forward(req, res);
		}
		
		
	}
}
