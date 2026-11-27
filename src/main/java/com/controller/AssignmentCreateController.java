package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.model.AccountLogin.LoginUser;
import com.model.Assignment.Assignment;
import com.model.Assignment.AssignmentLogic;
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
        request.setAttribute("listQuestions", listQuestions);
        
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

  @Override 
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
    response.setContentType("text/html; charset=UTF-8");

    // 課題名とチェックしたIDを取得する
    String assignmentTitle      = request.getParameter("assignment_name");
    // チェックしたIDは複数なので受け取り型は文字列配列
    String[] checkQuestionList = request.getParameterValues("check_question_id");

    // セッションを取り出す
    HttpSession session = request.getSession(false);
    // セッションがない またloginUserがnullの場合
    if (session == null || session.getAttribute("loginUser") == null){
      response.sendRedirect(request.getContextPath() + "/Login");
      return;
    }
    LoginUser loginUser = (LoginUser)session.getAttribute("loginUser");
    
    // ロールチェック
    // 講師以外は課題を保存する権限がないので、ここで弾く
    if("instructor".equals(loginUser.getRole())){
        // 課題を保存するためにLogicを通してDAOから結果を返す ☑
        // 保存するのは課題名・チェックしたID・講師ID ☑
        // 講師IDはセッションに保存されているので、loginUserからIDを取る ☑
        // 1. 課題名を課題表に保存する ☑
        // 2. 保存の際に識別の課題IDも取得する ☑
        // 3. 課題IDを取得したのちにチェックした問題IDとともに課題問題表に保存する
        // 4.課題問題表に保存後は、JOINを使って問題表と課題問題表を結合させたデータを抽出して表示させる
        // Ex. Logicクラスでnullチェックなどを設ける

        // 2. まで完了して課題表に課題名が入っていることを動作確認済み
        try{
          Assignment asssignmentInfo = new AssignmentLogic().saveAssignment(assignmentTitle, loginUser.getLoginId());
          return;
        }catch(SQLException e){
          // SQLエラー関連はログに詳細を記したい
          e.printStackTrace();
          // ブラウザ側は500エラーと表示
          response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
          return;
        }
        
    }
    // それ以外は権限ないので403エラー
    response.sendError(HttpServletResponse.SC_FORBIDDEN);
  }
}