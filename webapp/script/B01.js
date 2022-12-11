//bookクラスにマウスオーバーした場合の処理
document.addEventListener("mouseover", function(event) {
	if(event.target.className == 'book'){
		//ウィンドウの横幅取得
		var viewWidth = window.innerWidth / 2;
		//要素のid取得
		var bookId = event.target.id.substr(4);
	
		// 要素の位置取得
		var clientRect = event.target.getBoundingClientRect() ;
		var x = clientRect.top;
		var yL = clientRect.left;
		var yR = clientRect.right;
	
		//位置を決定
		//ウィンドウの中央より要素が右の場合
		if(viewWidth < yL){
			document.getElementById(bookId).style.top = x + 20 + "px";
			document.getElementById(bookId).style.left = yR - 200 + "px";
		//ウィンドウの中央より要素が左の場合
		}else{
			document.getElementById(bookId).style.top = x + 20 + "px";
			document.getElementById(bookId).style.left = yL + 18 + "px";
		}
	}
});

//本をクリックした場合の遷移処理
document.addEventListener('DOMContentLoaded',function(){
	//bookクラスを取得
    var books = document.getElementsByClassName('book');
    var bookLength = books.length;
    var bookValues = {};
    var bookId;
    
    //bookクラスの数までクリックイベントを作成
    for(var i = 0; i < bookLength; i++){
		//hiddenの値を取得
		bookValues[i] = document.getElementById('hidden'+i).value;
		
		//bookクラスがクリックされた場合
		//iでbookIdを代用しようとするとiの最大値になるので不可能だった
        books[i].addEventListener('click',function(event){
			bookId = event.target.id.substr(4);
            location.href = '/bookshelf/DispDetail?id='+bookValues[bookId];
        });
        
        //ポップアップがクリックされた場合
        document.getElementById(i).addEventListener('click',function(event){
            //imgタグがクリックされた場合
            if(event.target.tagName === "IMG"){
				bookId = event.target.id.substr(5);
			//frameクラスがクリックされた場合
			}else{
				bookId = event.target.id;
			}
			
            location.href = '/bookshelf/DispDetail?id='+bookValues[bookId];
        });
    }
});

books[i].addEventListener('click',function(event){
	bookId = event.target.id.substr(4);
	location.href = '/bookshelf/DispDetail?id='+bookValues[bookId];
});