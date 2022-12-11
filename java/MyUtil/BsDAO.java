package MyUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//データベースに接続
public class BsDAO {
	
	//接続用の情報をフィールドに定数として定義
	private static final String RDB_DRIVE="com.mysql.cj.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/bookshelf";
	private static final String USER="root";
	private static final String PASSWD="fire1378";
	Logger log = LogManager.getLogger();

	//データベース接続を行うメソッド
	//データベース接続用定義を基にデータベースへ接続し、戻り値としてコネクション情報を返す
	private static Connection getConnection(){
		try{
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		}catch(ClassNotFoundException e){
			throw new IllegalStateException(e);
		}catch(SQLException e) {
			throw new IllegalStateException(e);
		}
	}
	
	//ログイン時のユーザー名とパスワードの検索を行うメソッド
	//入力された情報がテーブルに存在するかを確認し、ArrayList<BsDTO>型オブジェクトへ格納し、戻り値として返す
	public ArrayList<BsDTO> checkUser(String userName, String password){
		//変数宣言
		Connection con = null;  // DBコネクション
		PreparedStatement smt = null;   // SQLステートメント
				
		//配列宣言
		ArrayList<BsDTO> list = new ArrayList<BsDTO>();
		
		// SQL文作成
		String sql = "SELECT * FROM userinfo WHERE username=? AND password=?";
		
		try{
			//DBに接続
			con = BsDAO.getConnection();
			smt = con.prepareStatement(sql);
			smt.setString(1, userName);
			smt.setString(2, password);
			
			//SQL文発行
			ResultSet rs = smt.executeQuery();
			
			//検索結果をArrayListに格納
			while(rs.next()){
				BsDTO objDto = new BsDTO();
				//objDto.setId(rs.getInt("id"));
				objDto.setUserName(rs.getString("username"));
				list.add(objDto);
			}
		}catch(SQLException e){
			log.error(e);
		}finally{
			//リソースの開放
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}
		return list;
		
	}

	//データベースから全ての書籍情報の検索を行うメソッド
	//テーブルに登録された全てのデータをArrayList<BsDTO>型オブジェクトへ格納し、戻り値として返す
	public ArrayList<BsDTO> searchAll(){
		// 変数宣言
		Connection con = null;  // DBコネクション
		PreparedStatement smt = null;   // SQLステートメント
		
		//配列宣言
		ArrayList<BsDTO> list = new ArrayList<BsDTO>();
	
		//SQL文作成
		String sql = "SELECT * FROM bookinfo";
	
		try{
			//DBに接続
			con = BsDAO.getConnection();
			smt = con.prepareStatement(sql);
	
			// SQL文発行
			ResultSet rs = smt.executeQuery();
	
			//検索結果をArrayListに格納
			while(rs.next()){
				BsDTO objDto = new BsDTO();
				objDto.setId(rs.getInt("id"));
				objDto.setBookName(rs.getString("bookname"));
				objDto.setAuthor(rs.getString("author"));
				objDto.setPublisher(rs.getString("publisher"));
				objDto.setPublishDate(rs.getString("publishDate"));
				objDto.setGood(rs.getInt("good"));
				objDto.setPicture(rs.getString("picture"));
				list.add(objDto);
			}
		}catch(SQLException e){
			log.error(e);
		}finally{
			// リソースの開放
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}
		return list;
	}
	
	//検索条件に合致する書籍情報を検索するメソッド
	public ArrayList<BsDTO> searchBook(String name, String author, String publisher){
		// 変数宣言
		Connection con = null;  // DBコネクション
		PreparedStatement smt = null;   // SQLステートメント
		
		//配列宣言
		ArrayList<BsDTO> list = new ArrayList<BsDTO>();
		
		//SQL文作成
		String sql = "SELECT * FROM bookinfo "
		+ "WHERE bookname like ? AND author like ? "
		+ "AND publisher like ?";
		
		try{
			//DBに接続
			con = BsDAO.getConnection();
			smt = con.prepareStatement(sql);
			smt.setString(1, "%"+name+"%");
			smt.setString(2, "%"+author+"%");
			smt.setString(3, "%"+publisher+"%");
			
			//SQL文発行
			ResultSet rs = smt.executeQuery();
		
			// 検索結果をArrayListに格納
			while(rs.next()){
				BsDTO objDto = new BsDTO();
				objDto.setId(rs.getInt("id"));
				objDto.setBookName(rs.getString("bookname"));
				objDto.setAuthor(rs.getString("author"));
				objDto.setPublisher(rs.getString("publisher"));
				objDto.setPublishDate(rs.getString("publishDate"));
				objDto.setGood(rs.getInt("good"));
				objDto.setPicture(rs.getString("picture"));
				list.add(objDto);
			}
		}catch(SQLException e){
			log.error(e);
		}finally{
			//リソースの開放
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}
		return list;
	}
	
	//データベースから全ての書籍情報で最もidの数値が大きい検索を行うメソッド
	public ArrayList<BsDTO> searchDetail(String bookId){
		// 変数宣言
		Connection con = null;  // DBコネクション
		PreparedStatement smt = null;   // SQLステートメント
		
		//配列宣言
		ArrayList<BsDTO> list = new ArrayList<BsDTO>();
		
		//SQL文作成
		String sql = "SELECT * FROM bookinfo WHERE id=?";
		
		try{
			//DBに接続
			con = BsDAO.getConnection();
			smt = con.prepareStatement(sql);
			smt.setString(1, bookId);
			
			//SQL文発行
			ResultSet rs = smt.executeQuery();
		
			//検索結果をArrayListに格納
			while(rs.next()){
				BsDTO objDto = new BsDTO();
				objDto.setBookName(rs.getString("bookname"));
				objDto.setAuthor(rs.getString("author"));
				objDto.setPublisher(rs.getString("publisher"));
				objDto.setPublishDate(rs.getString("publishDate"));
				objDto.setGood(rs.getInt("good"));
				objDto.setPicture(rs.getString("picture"));
				list.add(objDto);
			}
		}catch(SQLException e){
			log.error(e);
		}finally{
			//リソースの開放
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}
		return list;
	}
	
	//データベースから全ての書籍情報で最もidの数値が大きい検索を行うメソッド
	//戻り値としてidの数値を返す
	public int countId(){
		// 変数宣言
		Connection con = null;  // DBコネクション
		PreparedStatement smt = null;   // SQLステートメント
		
		int id = 0;

		//SQL文作成
		String sql = "SELECT MAX(id) as id FROM bookinfo";
		
		try{
			//DBに接続
			con = BsDAO.getConnection();
			smt = con.prepareStatement(sql);
		
			//SQL文発行
			ResultSet rs = smt.executeQuery();
		
			//検索結果をArrayListに格納
			while(rs.next()){
				id = rs.getInt("id");
			}
		}catch(SQLException e){
			log.error(e);
		}finally{
			//リソースの開放
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}
		return id;
	}
	
	//書籍情報を登録するメソッド
	//引数に渡された書籍情報をデータベースへ登録し、戻り値として登録件数を返す
	public int insertBook(BsDTO books){
	// 変数宣言
	Connection con = null;  // DBコネクション
	PreparedStatement smt = null;   // SQLステートメント
	
	int rowsCount = 0;
	String author = books.getAuthor();
	String publisher = books.getPublisher();
	String publishDate = books.getPublishDate();
	String picture = books.getPicture();
	
	//nullでないときにINSERTできる形にする
	if(author != null) {
		author = "'"+author+"'";
	}
	if(publisher != null) {
		publisher = "'"+publisher+"'";
	}
	if(publishDate != null) {
		publishDate = "'"+publishDate+"'";
	}
	if(picture != null) {
		picture = "'"+picture+"'";
	}
	
	//SQL文
	//preparedStatementで変数をセットしたかったが、セットが上手くいかなかったので直接変数を代入
	String sql = "INSERT INTO bookinfo(bookname,author,publisher,publishdate,good,picture) " +
		"VALUES('" + books.getBookName() + "'," + author + "," +
			publisher + "," + publishDate + ",'" +
			books.getGood() + "'," + picture + ")";
	
	try{
		//DBに接続
		con = BsDAO.getConnection();
		smt = con.prepareStatement(sql);
	
		//SQL文発行
		rowsCount = smt.executeUpdate(sql);
	}catch(SQLException e){
		log.error(e);
	}finally{
		//リソースの開放
		if(smt != null){
			try{smt.close();}catch(SQLException ignore){}
		}
		if(con != null){
			try{con.close();}catch(SQLException ignore){}
		}
	}
		return rowsCount;
	}

}
