package com.model.AccountCreate;

import java.sql.Connection;
import java.sql.SQLException;

import com.model.ConnectionManager;

public class TraineeCreateLogic {
    
    // 入力値の名前のチェック/パスワードハッシュ化をおこない、そのあと情報を登録する
    public int registerAccount(String name, String accountPw) throws IllegalArgumentException , SQLException{
        // アカウントID
        int accountId;

        CommonAccountCreateLogic cl = new CommonAccountCreateLogic();
        // 登録する名前に不備がないか確認
        String checkedName   = cl.validateName(name);
        // ハッシュ化
        String accountPwHash = cl.passwordHash(accountPw);

        try(Connection cn = ConnectionManager.getConnection()){
            // DAOにハッシュを渡す
            accountId = new TraineeCreateDAO(cn).saveUserInfomation(checkedName, accountPwHash);
        }
        
        return accountId;
    }
   
}
