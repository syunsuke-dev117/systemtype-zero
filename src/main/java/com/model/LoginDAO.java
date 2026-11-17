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
    public LoginUser login(int loginId, String loginPw) throws SQLException{
        // ログインユーザの情報を算出
        String sql = "SELECT instructor_id  , instructor_name, instructor_password FROM instructors WHERE instructor_id = ? AND instructor_password = ?";
        LoginUser loginUser = null;

        /* PreparedStatementの作成
        *  パラメータの設定
           SQL文の実行              */
        try (PreparedStatement stmt = cn.prepareStatement(sql)){
            // SQL文の「?」にlogin_idとlogin_pwを代入する
            stmt.setInt(1, loginId);
            stmt.setString(2, loginPw);

            try (ResultSet res = stmt.executeQuery()){
                // 算出した結果があればLoginUserに値を格納
                // またこの処理により、データがある際はログイン成功となり、ない場合は失敗となる。
                if(res.next()){
                    loginUser = new LoginUser(
                            res.getInt("instructor_id"),
                            res.getString("instructor_name"),
                            res.getString("instructor_password")
                    );
                    return loginUser;
                }
            }  
        }
        
        return null;
    }
}
