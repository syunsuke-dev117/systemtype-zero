package com.controller;

import com.model.LoginUser;
import com.model.LoginLogic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

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

  // ブラウザから名前とパスワードの入力フォームが送信された際に処理する
  @Override 
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
    request.setCharacterEncoding("UTF-8");
    // ブラウザからの名前とパスワードの値を取得する
    String name    = request.getParameter("user");
    String pasword = request.getParameter("pw");
    // LoginUserオブジェクト取得
    LoginUser loginUser = new LoginUser(name, pasword);

    // Loginロジッククラスを呼ぶ
    LoginLogic isLogin = new LoginLogic();
    boolean loginResult = isLogin.login(loginUser);

    try{
      // 指定のPWなら
      if(loginResult){
        // 値を次のjspに引き渡す
        request.setAttribute("user", name);
        request.getRequestDispatcher("/WEB-INF/login_result_success.jsp").forward(request, response);
      }else{
        request.getRequestDispatcher("/WEB-INF/login_result_fail.jsp").forward(request, response);
      }
    }catch(IOException e){
      e.printStackTrace();
    }

  }

}