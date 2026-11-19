package com.model;

import java.sql.Connection;
import java.sql.SQLException;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class InstructorCreateLogic {
    // アカウントID
    private  int accountId;

    // パスワード検証する
    public boolean PasswordVerification(String accountPw, String accountPwVc) throws IllegalArgumentException{

        // 値がnull(オブジェクトの住所が無い)
        if(accountPw == null || accountPwVc == null){
            throw new IllegalArgumentException("パスワードまたは確認用パスワードがnullです");
        }

        // 値が空
        if(accountPw.isBlank() || accountPwVc.isBlank()){
            throw new IllegalArgumentException("パスワードまたは確認用パスワードが空値またはパスワードに余白スペースが入っています");
        }

        // 6文字未満 または 13文字以上
        if (accountPw.length() < 6 || accountPwVc.length() < 6){
            throw new IllegalArgumentException("パスワードは6文字以上にしてください");
        } else if(accountPw.length() >= 13 || accountPwVc.length() >= 13){
            throw new IllegalArgumentException("パスワードは13文字未満にしてください");
        }

        // オブジェの住所じゃなく中身が一致するか判定したいので equals()
        if (!accountPw.equals(accountPwVc)){
            return false;
        }

        // 一致
        return true;
    }
    
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
        String accountPwHash = BCrypt.withDefaults().hashToString(12, accountPw.toCharArray());

        try(Connection cn = ConnectionManager.getConnection()){
            // DAOにハッシュを渡す
            accountId = new InstructorCreateDAO(cn).saveUserInfomation(name, accountPwHash);
        }
        
        return accountId;
   }
   
}
