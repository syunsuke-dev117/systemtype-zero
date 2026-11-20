package com.model.AccountCreate;

import java.sql.Connection;
import java.sql.SQLException;

import com.model.ConnectionManager;

public class InstructorCreateLogic {

    // アカウント登録して識別IDを返す
    public int registerAccount(String name, String accountPw) throws IllegalArgumentException , SQLException{
        // スレッド割り込み防止のためフィールドにあった変数はローカルに宣言
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
