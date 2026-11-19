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
    <label>LoginId</label>
    <input type="text" name="login_id" required>
  </div>
  <div>
    <label>LoginPw</label>
    <input type="text" name="login_pw" required>
  </div>
  <div>
    <input type="submit" value="作成">
  </div>
</form>

</body>
</html>
