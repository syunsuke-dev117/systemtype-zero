package com.model;

import com.model.LoginUser;

public class LoginLogic{
    // DBとつなげるまでの繋げ処理
    public boolean login(LoginUser loginuser){
        // パスワードが指定のものと違っていたらfalse
        if(loginuser.getPw().equals("hogehoge")){
            return true;
        }
        return false;
    }
}
