package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.model.LoginUser;
import com.model.ConnectionManager;
import com.model.LoginDAO;

@WebServlet("/Login")
public class LoginController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {

    try{
      response.setContentType("text/html; charset=UTF-8");
      System.out.println("LoginController doGet");
      // login.jspへ転送
      request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }catch(ServletException | IOException e){
      e.printStackTrace();
    }
  }

  // ブラウザからIDとパスワードの入力フォームが送信された際に処理する
  @Override 
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
    request.setCharacterEncoding("UTF-8");
    // ブラウザからのログインID・パスワードの値を取得する
    int login_id      = Integer.parseInt(request.getParameter("login_id"));
    String login_pw   = request.getParameter("login_pw");

    try{
      // ConnectionManagerを通してDBの接続を取得
      Connection cn = null;
      cn = ConnectionManager.getConnection();

      // ConnectionとDAOを繋げる
      LoginDAO login_dao   = new LoginDAO(cn);
      // LoginLogic
      LoginUser login_user = login_dao.login(login_id, login_pw);

      // login_userの中身に照会データがあるなら
      if(login_user != null){
        // 値を次のjspに引き渡す
        request.setAttribute("login_name", login_user.getLoginName());
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