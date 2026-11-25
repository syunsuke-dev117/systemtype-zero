package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AccountSuccess")
public class AccountSuccessController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {

    try{

      response.setContentType("text/html; charset=UTF-8");
      // セッションを取り出す
      HttpSession session = request.getSession(false);
      // セッションがない(アカウント作成せずに開いた場合) またアカウントIDがnullの場合
      if (session == null || session.getAttribute("account_id") == null){
        response.sendRedirect(request.getContextPath() + "/AccountCreate");
        return;
      }
      // セッションからアカウントIDを取り出す
      Object accountId = session.getAttribute("account_id");
      // JSPで表示できるようにreuqestに入れる
      request.setAttribute("account_id", accountId);
      // login.jspへ転送
      request.getRequestDispatcher("/WEB-INF/account_create_success.jsp").forward(request, response);

    }catch(ServletException | IOException e){
      e.printStackTrace();
    }

  }
}