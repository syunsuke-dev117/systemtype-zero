package com.model.AccountCreate;

import java.sql.Connection;
import java.sql.SQLException;

import com.model.ConnectionManager;

public class InstructorCreateLogic {

    // 入力値の名前のチェック・パスワードの検証・それらをクリアしたら登録する
    public int registerAccount(String name, String accountPw) throws IllegalArgumentException , SQLException{
        // アカウントID
        int accountId;

        // 登録する名前に不備がないか確認
        String checkedName   = new CommonAccountCreateLogic().validateName(name);
        // ハッシュ化
        String accountPwHash = new CommonAccountCreateLogic().passwordHash(accountPw);

        try(Connection cn = ConnectionManager.getConnection()){
            // DAOにハッシュを渡す
            accountId = new InstructorCreateDAO(cn).saveUserInfomation(checkedName, accountPwHash);
        }
        
        return accountId;
    }
   
}
