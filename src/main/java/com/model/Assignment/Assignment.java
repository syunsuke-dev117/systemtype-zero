package com.model.Assignment;

public class Assignment {
    private int assignmentId;
    private String assignmentTitle;
    private String assignmentCreatedById;

    public Assignment(int assignmentId, String assignmentTitle, String assignmentCreatedById){
        this.assignmentId          = assignmentId;
        this.assignmentTitle       = assignmentTitle;
        this.assignmentCreatedById = assignmentCreatedById;
    }

    // 各ゲッター
    public int getAssignmentId(){
        return assignmentId;
    }

    public String getAssignmentTitle(){
        return assignmentTitle;
    }

    public String getAssignmentCreatedById(){
        return assignmentCreatedById;
    }
    
}
