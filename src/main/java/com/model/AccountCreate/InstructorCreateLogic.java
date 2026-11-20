package com.model.AccountCreate;

import java.sql.Connection;
import java.sql.SQLException;

import com.model.ConnectionManager;

public class InstructorCreateLogic {
    // アカウントID
    private  int accountId;

    // パスワードをハッシュ化してDAOに渡し、出力後の識別番号をコントローラーに返す
    public int passwordHash(String name, String accountPw) throws IllegalArgumentException , SQLException{

        // ハッシュ化
        String accountPwHash = new CommonAccountCreateLogic().passwordHash(accountPw);

        try(Connection cn = ConnectionManager.getConnection()){
            // DAOにハッシュを渡す
            accountId = new InstructorCreateDAO(cn).saveUserInfomation(name, accountPwHash);
        }
        
        return accountId;
    }
   
}
