package com.model.AccountLogin;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class CommonLoginLogic {

    // パスワード照合 (戻り型はboolean) 
    public boolean verificationPassword(String loginPassword ,Account account){
        BCrypt.Result result = BCrypt.verifyer().verify(loginPassword.toCharArray(), account.getLoginPw().toCharArray());
        boolean loginOK = result.verified;
        return loginOK;
    }
    
}
