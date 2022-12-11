function sendPost() {
  event.preventDefault();
  var form = document.createElement('form');
  form.action = '/bookshelf/Logout';
  form.method = 'post';
  document.body.appendChild(form);
  form.submit();
}