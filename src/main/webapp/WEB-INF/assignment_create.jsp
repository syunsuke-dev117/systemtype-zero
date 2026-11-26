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

<form action="<c:url value='/AssignmentCreate' />" method="post">

  <div>
    <label for="assignment_name"><b>課題名:</b></label>
    <input type="text" id="assignment_name" name="assignment_name" maxlength="100">
  </div>

  <%--- 提出期限は必要そうなら後に実装する。現段階ではつけない 
  <div>
    <label for="assignment_name">:</label>
    <input type="text" id="assignment_name" name="assignment_name" maxlength="100">
  </div>--%>

  <%-- 問題データが空の場合 --%>
  <c:if test="${empty listQuestions}">
    <p>問題がまだ空です。管理者に問い合わせてください。</p>
  </c:if>

  <%-- 問題を表示 --%>
  <c:forEach var="q" items="${listQuestions}" varStatus="st">
    <div>
        <input type="checkbox" id="check_question_id" name="check_question_id" value=<c:out value="${q.questionId}"/>>
        <label for="check_question_id"><b>第<c:out value="${st.count}"/>問</b> <c:out value="${q.questionBody}"/></label>

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

  <button type="submit">課題作成</button>
</form>

</body>
</html>
