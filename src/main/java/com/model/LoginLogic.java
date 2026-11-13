package com.model;

import com.model.LoginUser;

public class LoginLogic{
    // DBとつなげるまでの繋げ処理
    public boolean login(LoginUser loginuser){
        /// TODO: 実際のDB内部にあるパスワードデータと参照するロジックを考える
        
        // パスワードが指定のものと違っていたらfalse
        if(loginuser.getLoginPw().equals("hoge@Taro")){
            return true;
        }
        return false;
    }
}
