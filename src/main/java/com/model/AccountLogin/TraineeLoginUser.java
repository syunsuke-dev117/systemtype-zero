package com.model.AccountLogin;

import java.io.Serializable;

/**
 * LoginControllerとの間で値の情報の受け渡しを行う。
 * セッションに入る際のPW情報渡しの防止のため、PW情報は無し
 */

public class TraineeLoginUser implements Serializable{
    // ログイン情報 ID・名前
    private int loginId;
    private String loginName;

    public TraineeLoginUser(int loginId, String loginName){
        this.loginId   = loginId;
        this.loginName = loginName;
    }

    // IDゲッターメソッド
    public int getLoginId(){
        return this.loginId;
    }
    // ネームゲッターメソッド
    public String getLoginName(){
        return this.loginName;
    }

}
