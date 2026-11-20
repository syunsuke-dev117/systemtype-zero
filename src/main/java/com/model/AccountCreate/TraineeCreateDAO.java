package com.model.AccountCreate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TraineeCreateDAO {
    // DB接続時に必要なConnectionを宣言
    private Connection cn;

    public TraineeCreateDAO(Connection cn){
        this.cn = cn;
    }

    // アカウントを作成して、アカウントに応じたIDを返す
    public int saveUserInfomation(String name, String accountHashedPw) throws SQLException{
        // スレッド割り込み防止のためフィールドにあったものをローカルで宣言
        int autoIncrementKey;
        // 名前とハッシュ化パスワードを保存するSQLを持つ変数
        String insertSql = "INSERT INTO trainees (trainee_name, trainee_password) VALUES (?, ?);";

        /* PreparedStatementの作成
        *  パラメータの設定
           SQL文の実行              */
        try (PreparedStatement stmt = cn.prepareStatement(insertSql, java.sql.Statement.RETURN_GENERATED_KEYS)){
            // SQL文の「?」に代入する
            stmt.setString(1, name);
            stmt.setString(2, accountHashedPw);
            // DB側で実行       
            stmt.executeUpdate();

            // 自動の識別番号を取得
            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()){
                    autoIncrementKey = rs.getInt(1);
                    return autoIncrementKey;
                }
            }
    
        }
        
        return 0;
    }
}
