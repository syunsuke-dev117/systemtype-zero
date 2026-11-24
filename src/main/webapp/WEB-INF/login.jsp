<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>

<h1>ログインページ</h1>

<form action="<c:url value='/Login' />" method="post">

  <label>あなたはどちらですか？</label>
  <div>
    <label for="inst">講師</label>
    <input type="radio" id="inst" name="user" value="instructor" checked>

    <label for="trai">研修生</label>
    <input type="radio" id="trai" name="user" value="trainee">
  </div>

  <div>
    <label>ログインID</label>
    <input type="text" name="login_id" required>
  </div>
  <div>
    <label>ログインパスワード</label>
    <input type="text" name="login_pw" required>
  </div>
  <div>
    <input type="submit" value="ログイン">
  </div>
</form>
<p><c:out value="${msgError}" /></p>

<h1>新規登録</h1>
<h3><a href="<c:url value='/AccountCreate' />">アカウントを作っていない方はこちらから</a></h3>

</body>
</html>
