package com.model.AccountLogin;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class CommonLoginLogic {
    
    // アカウントのぬるぽチェック
    public Account nullCheckAccount(Account account) throws NullPointerException{

        if (account == null){
            throw new NullPointerException("アカウントがnullです");
        }

        return account;
    } 

    // パスワード照合 (戻り型はboolean) 
    public boolean verificationPassword(String loginPassword ,Account checkedAccount){
        BCrypt.Result result = BCrypt.verifyer().verify(loginPassword.toCharArray(), checkedAccount.getLoginPw().toCharArray());
        boolean loginOK = result.verified;
        return loginOK;
    }
    
}
