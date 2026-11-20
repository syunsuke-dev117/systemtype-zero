package com.model;

import java.sql.Connection;
import java.sql.SQLException;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.model.CommonAccountCreateLogic;

public class TraineeCreateLogic {
    // アカウントID
    private  int accountId;
    
    // パスワードをハッシュ化してDAOに渡し、出力後の識別番号をコントローラーに返す
    public int passwordHash(String name, String accountPw) throws IllegalArgumentException , SQLException{

        // 名前がnull
        if(name == null){
            throw new IllegalArgumentException("名前がnullです");
        }

        // 名前が空/スペース空白
        if(name.isBlank()){
            throw new IllegalArgumentException("名前が空値です");
        }

        // ハッシュ化
        String accountPwHash = new CommonAccountCreateLogic().PasswordHash(accountPw);

        try(Connection cn = ConnectionManager.getConnection()){
            // DAOにハッシュを渡す
            accountId = new TraineeCreateDAO(cn).saveUserInfomation(name, accountPwHash);
        }
        
        return accountId;
    }
   
}
