package com.model.Assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO {
    // DB接続時に必要なConnectionを宣言
    private Connection cn;

    public AssignmentDAO(Connection cn){
        this.cn = cn;
    }

    // 課題名と講師IDを保存して、課題IDを取得する
    public Assignment saveAssignment(String assignmentTitle, int instructorCreatedById) throws SQLException{
        int autoIncrementKey;
        // 名前とハッシュ化パスワードを保存するSQLを持つ変数
        String insertSql = "INSERT INTO assignments (title, instructor_id) VALUES (?, ?);";

        /* PreparedStatementの作成
        *  パラメータの設定
           SQL文の実行              */
        try (PreparedStatement stmt = cn.prepareStatement(insertSql, java.sql.Statement.RETURN_GENERATED_KEYS)){
            // SQL文の「?」に代入する
            stmt.setString(1, assignmentTitle);
            stmt.setInt(2, instructorCreatedById);
            // DB側で実行       
            stmt.executeUpdate();

            // 自動の識別番号を取得
            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()){
                    autoIncrementKey = rs.getInt(1);
                    return new Assignment(autoIncrementKey, assignmentTitle, instructorCreatedById);
                }
            }
    
        }

        return null;
    }
}
