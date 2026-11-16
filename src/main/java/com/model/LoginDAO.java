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

    // ログイン照会する際の処理
    public LoginUser login(int login_id, String login_pw) throws SQLException{
        // ログインユーザの情報を算出
        String sql = "SELECT student_id  , student_name, student_password FROM students WHERE student_id = ? AND student_password = ?";
        LoginUser login_user = null;

        /* PreparedStatementの作成
        *  パラメータの設定
           SQL文の実行              */
        try (PreparedStatement stmt = cn.prepareStatement(sql)){
            // SQL文の「?」にlogin_idとlogin_pwを代入する
            stmt.setInt(1, login_id);
            stmt.setString(2, login_pw);

            try (ResultSet res = stmt.executeQuery()){
                // 算出した結果があればLoginUserに値を格納
                // またこの処理により、データがある際はログイン成功となり、ない場合は失敗となる。
                if(res.next()){
                    login_user = new LoginUser(
                            res.getInt("student_id"),
                            res.getString("student_name"),
                            res.getString("student_password")
                    );
                    return login_user;
                }
            }  
        }
        
        return null;
    }
}
