<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AssignmentCreate</title>
</head>
<body>
  <h1>課題作成</h1>
  <h2>ログイン中の講師:<c:out value="${sessionScope.loginUser.loginName}" /></h2>

  <%-- 問題データが空の場合 --%>
  <c:if test="${empty listQuestions}">
    <p>問題がまだ空です。管理者に問い合わせてください。</p>
  </c:if>

  <%-- 問題を表示 --%>
  <c:forEach var="q" items="${listQuestions}" varStatus="st">
    <div>
      <p><b>第<c:out value="${st.count}"/>問</b> <c:out value="${q.questionBody}"/></p>

      <%-- コードがある問題はさらに表示 --%>
      <c:if test="${not empty q.questionCode}">
        <pre><c:out value="${q.questionCode}" /></pre>
      </c:if>

      <%-- 4択問題 --%>
      <ul>
        <li>A. <c:out value="${q.choiceA}" /></li>
        <li>B. <c:out value="${q.choiceB}" /></li>
        <li>C. <c:out value="${q.choiceC}" /></li>
        <li>D. <c:out value="${q.choiceD}" /></li>
      </ul>

    </div>
  </c:forEach>


</body>
</html>
