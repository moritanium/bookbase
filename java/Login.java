import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MyUtil.BsDAO;
import MyUtil.BsDTO;

//ログイン画面からログイン時に呼び出し
@WebServlet("/Login")
public class Login extends HttpServlet {
	//フォーム入力値の変数
	String userName;
	String pass;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//jspの入力データの文字コードの指定
		req.setCharacterEncoding("UTF-8");
		//出力する文字コードの指定
		res.setContentType("text/html; charset=UTF-8");
		
		//フォーム入力値を取得
		userName = req.getParameter("userName");
		pass = req.getParameter("pass");
		
		//不正な文字が使われていないか検査
		if(MySQLsanitize(userName) && MySQLsanitize(pass)) {
			//daoオブジェクト化
			BsDAO dao = new BsDAO();
			//ユーザーの照合メソッド呼び出し
			ArrayList<BsDTO> list = dao.checkUser(userName, pass);
			
			//ユーザーの照合に失敗したらメッセージを渡して元の画面に戻る
			if(list == null || list.size() == 0) {
				req.setAttribute("failMes", "ユーザー名またはパスワードが違います。");
				req.getRequestDispatcher("/jsp/B00.jsp").forward(req, res);
			
			//成功したらセッションを開始し、書籍情報を全検索する
			}else {
				//セッション開始
				HttpSession ses = req.getSession();
				BsDTO dto = list.get(0);
				userName = dto.getUserName();
				ses.setAttribute("userName", userName);
				req.getRequestDispatcher("/SearchAll").forward(req, res);
			}
		
		//不正な文字があった場合、ユーザー照合を行わずに失敗とする。
		}else {
			req.setAttribute("failMes", "ユーザー名またはパスワードが違います。");
			req.getRequestDispatcher("/jsp/B00.jsp").forward(req, res);
		}
	}
	
	//SQLインジェクション対策
	protected boolean MySQLsanitize(String target) {
		boolean result = true;
		if(target != null) {
			if(target.contains("\\")) {
				result = false;
			}
			if(target.contains("'")) {
				result = false;
			}
			if(target.contains("\"")) {
				result = false;
			}
		}
		return result;
	}
}
