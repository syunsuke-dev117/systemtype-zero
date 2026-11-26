package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AssigmentCreate")
public class AssigmentCreateController extends HttpServlet {
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
    // login_result_successへ転送
    request.getRequestDispatcher("/WEB-INF/assigment_create.jsp").forward(request, response);

  }
}