//画像用の変数宣言
var file = document.getElementById('picture');
var preImage = document.getElementById('preImage');

//inputのimageに画像が選択された場合の処理
file.addEventListener('change', () => {
	//画像のファイル名の表示
	if(file.value == null || file.value === ""){
		document.getElementById('preTxS').textContent= '選択されていません';
		document.getElementById('preTxL').textContent= '選択されていません';
	}else{
		document.getElementById('preTxS').textContent= file.value;
		document.getElementById('preTxL').textContent= file.value;
	}
	
	//画像の表示
   	const fileReader = new FileReader();
   	fileReader.onload = (function() {
		preImage.src = fileReader.result;
	});
	if(file.files[0] === undefined){
		preImage.src = '';
		preImage.style.display = 'none';
	}else{
		preImage.style.display = 'flex';
		fileReader.readAsDataURL(file.files[0]);
	}
});