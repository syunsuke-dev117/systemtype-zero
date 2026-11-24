<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LoginResult</title>
</head>
<body>
  <h1>アカウント作成が完了しました</h1>
  <h2>あなたのアカウントIDは「${account_id}」番です。</n>メモか何かに控えておいてください。</h2>
  <h3><a href="<c:url value='/Login' />">ログインページ</a></h3>
</body>
</html>
