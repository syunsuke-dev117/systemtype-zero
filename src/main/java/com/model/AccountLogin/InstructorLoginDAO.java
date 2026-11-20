package com.model.AccountLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructorLoginDAO {
    // DB接続時に必要なConnectionを宣言
    private Connection cn;

    public InstructorLoginDAO(Connection cn){
        this.cn = cn;
    }

    // アカウントのIDを算出してAccountに情報を格納
    public Account findLoginId(int loginId) throws SQLException{
        // IDから情報算出
        String sql = "SELECT instructor_id  , instructor_name, instructor_password FROM instructors WHERE instructor_id = ?";
        Account account = null;
        /* PreparedStatementの作成
        *  パラメータの設定
           SQL文の実行              */
        try (PreparedStatement stmt = cn.prepareStatement(sql)){
            // SQL文の「?」に代入する
            stmt.setInt(1, loginId);

            try (ResultSet res = stmt.executeQuery()){
                // 算出した結果があればLoginUserに値を格納
                if(res.next()){
                    account = new Account(
                            res.getInt(1),
                            res.getString(2),
                            res.getString(3)
                    );
                    return account;
                }
            }  
        }
        
        return null;
    }
}
