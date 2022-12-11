import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MyUtil.BsDAO;
import MyUtil.BsDTO;

//Login.javaからログイン成功時、もしくはナビゲーションのホームボタンクリック時に呼び出し
@WebServlet("/SearchAll")
public class SearchAll extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//daoオブジェクト化
		BsDAO dao = new BsDAO();
		//ユーザーの照合メソッド呼び出し
		ArrayList<BsDTO> bookList = dao.searchAll();
		//リクエストスコープにリストをセット
		req.setAttribute("bookList", bookList);
		//管理画面に遷移
		req.getRequestDispatcher("/jsp/B01.jsp").forward(req, res);
	}
}
