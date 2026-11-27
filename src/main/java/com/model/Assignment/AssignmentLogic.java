package com.model.Assignment;

import java.sql.Connection;
import java.sql.SQLException;

import com.model.ConnectionManager;

public class AssignmentLogic {
    // AssignmentControllerとDAOの仲介役
    public Assignment saveAssignment(String assignmentTitle, int instructorCreatedById) throws SQLException{

        try (Connection cn = ConnectionManager.getConnection()) {
            // DAOを呼び、課題名をDAOに渡す。
            Assignment assignmentInfo = new AssignmentDAO(cn).saveAssignment(assignmentTitle, instructorCreatedById);
            return assignmentInfo;
        }
    }
}
