package com.model;

import java.io.Serializable;

public class LoginUser implements Serializable{
    // ログイン情報 名前とパスワード
    private String name;
    private String pw;

    public LoginUser(String name, String pw){
        this.name = name;
        this.pw   = pw;
    }

    // 名前とパスワードのゲッターメソッド
    public String getName(){
        return this.name;
    }

    public String getPw(){
        return this.pw;
    }

}
