package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.model.LoginUser;
import com.model.LoginLogic;

@WebServlet("/AccountCreate")
public class AccountCreateController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {

    try{
      response.setContentType("text/html; charset=UTF-8");
      // login.jspへ転送
      request.getRequestDispatcher("/WEB-INF/account_create.jsp").forward(request, response);
    }catch(ServletException | IOException e){
      e.printStackTrace();
    }
  }

  // ブラウザからIDとパスワードの入力フォームが送信された際に処理する
  @Override 
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
    request.setCharacterEncoding("UTF-8");
    // ブラウザからのユーザ識別・ログインID・パスワードの値を取得する
    String user  = request.getParameter("user");

    // ユーザ識別変数 userの中身 から講師 or 研修生を判断する
    // 比較の際はオブジェクトの住所じゃなく中身の判断なのでequals()を使う
    if("instructor".equals(user)){
      // InstractorAccountDAOに値を渡す
      // その前にAccountCreateLogicを間に挟んで、確認用パスワードと一致するか検証
      

    }else{
      // TraineeAccountDAOに値を渡す
    }


    try{

      

      if(loginUser != null){
        // 値を次のjspに引き渡す
        request.setAttribute("login_name", loginUser.getLoginName());
        request.getRequestDispatcher("/WEB-INF/login_result_success.jsp").forward(request, response);
      }else{
        request.getRequestDispatcher("/WEB-INF/login_result_fail.jsp").forward(request, response);
      }
    }catch(SQLException e){
      e.printStackTrace();
      // View側にエラーを返す
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

  }

}