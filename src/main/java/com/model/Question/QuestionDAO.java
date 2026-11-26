package com.model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    // DB接続時に必要なConnectionを宣言
    private Connection cn;

    public QuestionDAO(Connection cn){
        this.cn = cn;
    }

    // DBに入っている問題情報をすべて算出する
    // QuestionLogicに問題データを返すためfindAllを作成した
    public List<Question> findAll() throws SQLException{

        String sql = "SELECT * FROM questions";
        List<Question> listQuestions = new ArrayList<>();
        
        /* PreparedStatementの作成
        *  パラメータの設定
           SQL文の実行              */
        try (PreparedStatement stmt = cn.prepareStatement(sql)){

            try (ResultSet res = stmt.executeQuery()){
                // 問題データがあればlistQuestionsリストに追加でいれていく
                while(res.next()){
                    Question q = new Question(
                        res.getInt("question_id"),
                        res.getString("question_body"),
                        res.getString("question_code"),
                        res.getString("choice_a"),
                        res.getString("choice_b"),
                        res.getString("choice_c"),
                        res.getString("choice_d"),
                        res.getString("answer_choide"),
                        res.getString("answer_explanation"),
                        res.getInt("genre_id")
                    );
                    listQuestions.add(q);
                }
            }  
        }
        // 問題情報リストを返す。データがない場合は空値で返る。
        return listQuestions;
    }
}
