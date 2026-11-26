package com.model.Question;

public class Question {
    private int questionId;
    private String questionBody;
    private String questionCode;
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String choiceD;
    private String answerChoice;
    private String answerExplanation;
    private int genreId;

    // 問題文や選択肢などの情報を保持するクラス
    public Question(int questionId, String questionBody, String questionCode, 
                    String choiceA, String choiceB, String choiceC, String choiceD, 
                    String answerChoice, String answerExplanation, int genreId){
        this.questionId        = questionId;
        this.questionBody      = questionBody;
        this.questionCode      = questionCode;
        this.choiceA           = choiceA;
        this.choiceB           = choiceB;
        this.choiceC           = choiceC;
        this.choiceD           = choiceD;
        this.answerChoice      = answerChoice;
        this.answerExplanation = answerExplanation;
        this.genreId           = genreId;
    }

    // 各ゲッター
    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionBody() {
        return questionBody;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public String getAnswerChoice() {
        return answerChoice;
    }

    public String getAnswerExplanation() {
        return answerExplanation;
    }

    public int getGenreId() {
        return genreId;
    }
    
}
