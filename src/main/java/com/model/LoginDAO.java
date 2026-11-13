package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    // DB接続時に必要なConnectionを宣言
    private Connection cn;

    public LoginDAO(Connection cn){
        this.cn = cn;
    }
    
    // ログインした際のIDを使用してネームを算出する
    public LoginUser findLoginUserName(int login_id) throws SQLException{
        // ログインユーザのネームを算出するSQL文を定義
        String sql = "SELECT student_id , student_name , student_password FROM students WHERE student_id = ?";

        PreparedStatement stmt = null;
        ResultSet res = null;
        LoginUser login_user = null;

        /* PreparedStatementの作成
        *  パラメータの設定
           SQL文の実行              */
        try {
            // PreparedStatmentにより、SQLの処理がはやくなる
            stmt = cn.prepareStatement(sql);
            // SQL文の「?」にlogin_idを代入する
            stmt.setInt(1, login_id);
            res  = stmt.executeQuery();

            // 算出した結果があればLoginUserに値を格納
            if(res.next()){
                login_user = new LoginUser(
                        res.getInt("student_id"),
                        res.getString("student_name"),
                        res.getString("student_password")
                );
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{

                res.close();
                stmt.close();
        }
        
        return login_user;
    }
}
