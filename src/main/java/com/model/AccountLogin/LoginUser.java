package com.model.AccountLogin;

import java.io.Serializable;

/**
 * LoginControllerとの間で値の情報の受け渡しを行う。
 * セッションに入る際のPW情報渡しの防止のため、PW情報は無し
 * ログイン後に課題作成機能を扱えるのが「講師」であり、それ以外は使わせないためにroleを設ける
 */

public class LoginUser implements Serializable{
    // ログイン情報 ID・名前・ロール
    private int loginId;
    private String loginName;
    private String role;

    public LoginUser(int loginId, String loginName, String role){
        this.loginId   = loginId;
        this.loginName = loginName;
        this.role      = role;
    }

    // IDゲッターメソッド
    public int getLoginId(){
        return this.loginId;
    }
    // ネームゲッターメソッド
    public String getLoginName(){
        return this.loginName;
    }
    // ロールゲッターメソッド
    public String getRole(){
        return this.role;
    }

}
