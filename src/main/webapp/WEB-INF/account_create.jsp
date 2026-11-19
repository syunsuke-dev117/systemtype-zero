<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AccountCreate</title>
</head>
<body>

<h1>新規登録ページ</h1>

<!-- 新規登録にいたって、ラジオボタンで講師と研修生で区別する -->
<form action="${pageContext.request.contextPath}/AccountCreate" method="post">

  <label>あなたはどちらですか？</label>
  <div>
    <label for="inst">講師</label>
    <input type="radio" id="inst" name="user" value="instructor" checked>

    <label for="trai">研修生</label>
    <input type="radio" id="trai" name="user" value="trainee">
  </div>
  
  <div>
    <label>名前</label>
    <input type="text" name="name">
  </div>
  <div>
    <label>パスワード</label>
    <input type="text" name="login_pw">
  </div>
  <div>
    <label>確認用パスワード</label>
    <input type="text" name="login_pw_vc">
  </div>
  ※ パスワードは6文字以上12文字以下で入力してください
  <p>${msgError}</p>

  <div>
    <input type="submit" value="作成">
  </div>
</form>

</body>
</html>
