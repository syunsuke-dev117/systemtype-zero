package com.model;

import java.io.Serializable;

public class LoginUser implements Serializable{
    // ログイン情報 ID・名前・パスワード
    private int loginId;
    private String loginName;
    private String loginPw;

    public LoginUser(int loginId, String loginName,String loginPw){
        this.loginId   = loginId;
        this.loginName = loginName;
        this.loginPw   = loginPw;
    }

    // IDゲッターメソッド
    public int getLoginId(){
        return this.loginId;
    }
    // ネームゲッターメソッド
    public String getLoginName(){
        return this.loginName;
    }
    // PWゲッターメソッド   
    public String getLoginPw(){
        return this.loginPw;
    }

}
