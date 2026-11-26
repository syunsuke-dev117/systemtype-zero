package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.AccountLogin.LoginUser;
import com.model.Question.Question;
import com.model.Question.QuestionLogic;

@WebServlet("/AssignmentCreate")
public class AssignmentCreateController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {

    response.setContentType("text/html; charset=UTF-8");
    // セッションを取り出す
    HttpSession session = request.getSession(false);
    // セッションがない またloginUserがnullの場合
    if (session == null || session.getAttribute("loginUser") == null){
      response.sendRedirect(request.getContextPath() + "/Login");
      return;
    }
    LoginUser loginUser = (LoginUser)session.getAttribute("loginUser");

    // ロールチェック
    // 講師だけ課題作成ページにアクセスできるようにする
    if ("instructor".equals(loginUser.getRole())){

      try {
        // 問題データリストを取ってくる
        List<Question> listQuestions = new QuestionLogic().findAll();
        
      }catch(SQLException e){
          // SQLエラー関連はログに詳細を記したい
          e.printStackTrace();
          // ブラウザ側は500エラーと表示
          response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
          return;
      }
      


      // assignment_create.jspへ転送
      request.getRequestDispatcher("/WEB-INF/assignment_create.jsp").forward(request, response);
      return;
    }
    // それ以外はアクセスする権限がないので、403エラー表示
    response.sendError(HttpServletResponse.SC_FORBIDDEN);
  }
}