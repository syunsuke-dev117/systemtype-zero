<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>

<h1>ログインページ</h1>

<form action="${pageContext.request.contextPath}/Login" method="post">
  <div>
    <label>LoginId</label>
    <input type="text" name="login_id" required>
  </div>
  <div>
    <label>LoginPw</label>
    <input type="text" name="login_pw" required>
  </div>
  <div>
    <input type="submit" value="ログイン">
  </div>
</form>

</body>
</html>
