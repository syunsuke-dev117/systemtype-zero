package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.model.LoginUser;
import com.model.LoginLogic;
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
    /// TODO: 現在login_pwが使われていないので、この変数を利用してパスワード照会ロジックを考える
    String login_pw   = request.getParameter("login_pw");

    try{
      // ConnectionManagerを通してDBの接続を取得
      Connection cn = null;
      cn = ConnectionManager.getConnection();

      // DAOクラスを呼び出しSQLにて結果を算出してユーザネームを取得する
      LoginDAO login_dao   = new LoginDAO(cn);
      LoginUser login_user = login_dao.findLoginUserName(login_id);

      // Loginロジッククラスを呼ぶ
      LoginLogic login_is  = new LoginLogic();
      boolean login_result = login_is.login(login_user);

      // 指定のPWなら
      if(login_result){
        // 値を次のjspに引き渡す
        request.setAttribute("login_name", login_user.getLoginName());
        request.getRequestDispatcher("/WEB-INF/login_result_success.jsp").forward(request, response);
      }else{
        request.getRequestDispatcher("/WEB-INF/login_result_fail.jsp").forward(request, response);
      }
    }catch(SQLException e){
      e.printStackTrace();
      // View側にエラーを返す7
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

  }

}