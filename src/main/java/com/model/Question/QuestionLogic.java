package com.model.Question;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import com.model.ConnectionManager;

public class QuestionLogic {
    
    public List<Question> findAll() throws SQLException{

        try (Connection cn = ConnectionManager.getConnection()) {

            List<Question> listQuestions = new QuestionDAO(cn).findAll();
            return listQuestions;

        }
        
    }
    
}
