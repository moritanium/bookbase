package MyUtil;

//データベースの情報を格納
public class BsDTO {
	
	//変数の宣言
	private String userName;
	private String password;
	private int id;
	private String bookName;
	private String author;
	private String publisher;
	private String publishDate;
	private int good;
	private String picture;
	
	
    public BsDTO() {
        // コンストラクタでの初期化処理
    	userName = null;
    	password = null;
    	id = 0;
    	bookName = null;
    	author = null;
    	publisher = null;
    	publishDate = null;
    	good = 0;
    	picture = null;
    }
    
    public String getUserName() {
    	return userName;
    }
    
    public void setUserName(String userName) {
    	this.userName = userName;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public String getBookName() {
    	return bookName;
    }
    
    public void setBookName(String bookName) {
    	this.bookName = bookName;
    }
    
    public String getAuthor() {
    	return author;
    }
    
    public void setAuthor(String author) {
    	this.author = author;
    }
    
    public String getPublisher() {
    	return publisher;
    }
    
    public void setPublisher(String publisher) {
    	this.publisher = publisher;
    }
    
    public String getPublishDate() {
    	return publishDate;
    }
    
    public void setPublishDate(String publishDate) {
    	this.publishDate = publishDate;
    }
    
    public int getGood() {
    	return good;
    }
    
    public void setGood(int good) {
    	this.good = good;
    }
    
    public String getPicture() {
    	return picture;
    }
    
    public void setPicture(String picture) {
    	this.picture = picture;
    }
}
