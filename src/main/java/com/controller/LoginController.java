package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import com.model.AccountLogin.InstructorLoginLogic;
import com.model.AccountLogin.TraineeLoginLogic;
import com.model.AccountLogin.LoginUser;

@WebServlet("/Login")
public class LoginController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {

    try{
      response.setContentType("text/html; charset=UTF-8");
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

    try{
      // ブラウザからのログインID・パスワードの値を取得する
      int loginId     = Integer.parseInt(request.getParameter("login_id"));
      String loginPw  = request.getParameter("login_pw");
      String user     = request.getParameter("user");
    
      // userの値により、講師か研修生か区別
      if("instructor".equals(user)){

          try{

            // ログイン判定→OKなら値がLoginUserに格納、なければnull
            LoginUser loginUser = new InstructorLoginLogic().isLogin(loginId, loginPw);

            if(loginUser != null){
              // セッションID作成
              request.changeSessionId();
              // ログインの情報をセッションに保存する
              HttpSession session = request.getSession();
              session.setAttribute("loginUser", loginUser);

              request.getRequestDispatcher("/WEB-INF/login_result_success.jsp").forward(request, response);
            }else{
              request.getRequestDispatcher("/WEB-INF/login_result_fail.jsp").forward(request, response);
              return;
            }
          }catch(SQLException e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
          }


      }else if ("trainee".equals(user)){

        try{

          // ログイン判定→OKなら値がLoginUserに格納、なければnull
          LoginUser loginUser = new TraineeLoginLogic().isLogin(loginId, loginPw);

          if(loginUser != null){
            // セッションID作成
            request.changeSessionId();
            // ログインの情報をセッションに保存する
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);
            request.getRequestDispatcher("/WEB-INF/login_result_success.jsp").forward(request, response);
          }else{
            request.getRequestDispatcher("/WEB-INF/login_result_fail.jsp").forward(request, response);
            return;
          }
        }catch(SQLException e){
          e.printStackTrace();
          response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
          return;
        }

      }else{
        // F12開発ツールから値を書き換えられた場合の対処
        // 普通のケースだと起きないが、一応対策として書いておく
        request.setAttribute("msgError", "講師か研修生を選択してください。またF12キーによる値の置き換えはしないでください。");
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
      }
    
    }catch(NumberFormatException e){
        request.setAttribute("msgError", "IDは数字で入力してください");
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return;
    }
  }

}