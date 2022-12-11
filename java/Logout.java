import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//ナビゲーションからログアウトボタンをクリック時に呼び出し
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//セッション取得
		HttpSession ses = req.getSession(true);
		
		//セッション破棄
		ses.invalidate();
		
		//ログアウト画面に遷移
		req.getRequestDispatcher("/jsp/B02.jsp").forward(req, res);
	}
	//ブラウザバック時にセッションが破棄されているので、
	//ブラウザバック時の画面でセッション変数を取得する
	//操作で意図的にエラーを起こし、エラーをキャッチしてリダイレクト
}
