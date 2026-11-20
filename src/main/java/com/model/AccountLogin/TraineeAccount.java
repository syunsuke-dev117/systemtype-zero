package com.model.AccountLogin;

import java.io.Serializable;

/**
 * LoginDAOからの結果を受け持つクラス
 * 直接セッションに入ることはないため、PW情報を保持する変数loginPWは有
 */

public class TraineeAccount implements Serializable{
    // 情報 ID・名前・パスワード
    private int loginId;
    private String loginName;
    private String loginPw;

    public TraineeAccount(int loginId, String loginName,String loginPw){
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
