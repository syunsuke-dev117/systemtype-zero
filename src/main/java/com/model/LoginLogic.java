package com.model;

import com.model.LoginUser;

public class LoginLogic{
    // DBとつなげるまでの繋げ処理
    public boolean login(LoginUser loginuser){
        /// TODO: これだとへなへなシステムなので、データベースを開通する(DAOクラスの作成)
        /// TODO: まずはデータベース名・テーブル名とかを決めないと(ER図の作成)
        

        // パスワードが指定のものと違っていたらfalse
        if(loginuser.getPw().equals("hogehoge")){
            return true;
        }
        return false;
    }
}
