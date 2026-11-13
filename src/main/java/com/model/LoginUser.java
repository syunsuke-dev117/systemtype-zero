package com.model;

import java.io.Serializable;

public class LoginUser implements Serializable{
    // ログイン情報 ID・名前・パスワード
    private int login_id;
    private String login_name;
    private String login_pw;

    public LoginUser(int login_id, String login_name,String login_pw){
        this.login_id   = login_id;
        this.login_name = login_name;
        this.login_pw   = login_pw;
    }

    // IDゲッターメソッド
    public int getLoginId(){
        return this.login_id;
    }
    // ネームゲッターメソッド
    public String getLoginName(){
        return this.login_name;
    }
    // PWゲッターメソッド   
    public String getLoginPw(){
        return this.login_pw;
    }

}
