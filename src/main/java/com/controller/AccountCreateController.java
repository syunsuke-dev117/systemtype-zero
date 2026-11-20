package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.model.AccountCreate.CommonAccountCreateLogic;
import com.model.AccountCreate.InstructorCreateLogic;
import com.model.AccountCreate.TraineeCreateLogic;

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
    // ブラウザからのユーザ識別値・名前・パスワード/確認用パスワードを取得する
    String user          = request.getParameter("user");
    String account_name  = request.getParameter("account_name");
    String accountPw     = request.getParameter("account_pw");
    String accountPwVc   = request.getParameter("account_pw_vc");

    // ユーザ識別変数 userの中身 から講師 or 研修生を判断する
    // 比較の際はオブジェクトの住所じゃなく中身の判断なのでequals()を使う
    if("instructor".equals(user)){

      try{

        // AccountCreateLogicを間に挟んで、確認用パスワードと一致するか検証
        boolean isPwMatch = new CommonAccountCreateLogic().PasswordVerification(accountPw, accountPwVc);
        // 不一致
        if(!isPwMatch){
          request.setAttribute("msgError", "パスワードが一致しません");
          request.getRequestDispatcher("/WEB-INF/account_create.jsp").forward(request, response);
          return;
        }

      }catch(IllegalArgumentException e){
        request.setAttribute("msgError", e.getMessage());
        request.getRequestDispatcher("/WEB-INF/account_create.jsp").forward(request, response);
        return;
      }

      // 一致ならここにくる
      // パスワードをハッシュ化させて、DBに保存する。返り値は識別ID
      try{
          int accountId = new InstructorCreateLogic().passwordHash(account_name, accountPw);

          if(accountId != -1){
            request.setAttribute("account_id", accountId);
            request.getRequestDispatcher("/WEB-INF/account_create_success.jsp").forward(request, response);
          }else{
            request.getRequestDispatcher("/WEB-INF/account_create_fail.jsp").forward(request, response);
            return;
          }
      }catch(IllegalArgumentException e){
        request.setAttribute("msgError", e.getMessage());
        request.getRequestDispatcher("/WEB-INF/account_create.jsp").forward(request, response);
        return;
        
      }catch(SQLException e){
          // SQLエラー関連はログに詳細を記したい
          e.printStackTrace();
          // ブラウザ側は500エラーと表示
          response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
          return;
      }

    }else{

      try{

        // TraineeCreateLogicを間に挟んで、確認用パスワードと一致するか検証
        boolean isPwMatch = new CommonAccountCreateLogic().PasswordVerification(accountPw, accountPwVc);
        // 不一致
        if(!isPwMatch){
          request.setAttribute("msgError", "パスワードが一致しません");
          request.getRequestDispatcher("/WEB-INF/account_create.jsp").forward(request, response);
          return;
        }

      }catch(IllegalArgumentException e){
        request.setAttribute("msgError", e.getMessage());
        request.getRequestDispatcher("/WEB-INF/account_create.jsp").forward(request, response);
        return;
      }

      // 一致ならここにくる
      // パスワードをハッシュ化させて、DBに保存する。返り値は識別ID
      try{
          int accountId = new TraineeCreateLogic().passwordHash(account_name, accountPw);

          if(accountId != -1){
            request.setAttribute("account_id", accountId);
            request.getRequestDispatcher("/WEB-INF/account_create_success.jsp").forward(request, response);
          }else{
            request.getRequestDispatcher("/WEB-INF/account_create_fail.jsp").forward(request, response);
            return;
          }
      }catch(IllegalArgumentException e){
        request.setAttribute("msgError", e.getMessage());
        request.getRequestDispatcher("/WEB-INF/account_create.jsp").forward(request, response);
        return;
        
      }catch(SQLException e){
          // SQLエラー関連はログに詳細を記したい
          e.printStackTrace();
          // ブラウザ側は500エラーと表示
          response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
          return;
      }
      
    }

  }

}