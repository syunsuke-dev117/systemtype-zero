package com.model;

import java.sql.Connection;
import java.sql.SQLException;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class AccountCreateLogic {

    // パスワード検証する
    public boolean PasswordVerification(String accountPw, String accountPwVc) throws IllegalArgumentException{

        // 値がnull(オブジェクトの住所が無い)
        if(accountPw == null || accountPwVc == null){
            throw new IllegalArgumentException("パスワードまたは確認用パスワードがnullです");
        }

        // 値が空
        if(accountPw.isEmpty() || accountPwVc.isEmpty()){
            throw new IllegalArgumentException("パスワードまたは確認用パスワードが空値です");
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
    
    // アカウントデータ追加
    public LoginUser accountCreate(int loginId, String loginPw) throws SQLException{
        
        try (Connection cn = ConnectionManager.getConnection()){
            // DAOからIDを取ってくる・IDがあればaccountに値が入ってる
            Account account = new LoginDAO(cn).findLoginId(loginId);

            // accountに値がなければnull
            if (account == null){
                return null;
            }

            // ハッシュ化専用の外部ライブラリ BCyptを用いる(メソッドチェーン方式)
            // パスワード照合 (戻り型はBCryptのResult) 照合時にはchar[]にしないといけない
            BCrypt.Result verificationResult = BCrypt.verifyer().verify(loginPw.toCharArray(), account.getLoginPw().toCharArray());

            // BCrypt.Resultのフィールド verified 戻り値はboolean
            boolean loginOK = verificationResult.verified;
    
            if(!loginOK){
                return null;
            }

            // 照合に成功すればここにくる
            // PWの重要データがないLoginUserに値を渡す
            // LoginUserはControllerに受け渡したのち、セッションに入る可能性があるため
            // 仮にセッションにLoginUserを入れてPW情報が入っていた場合
            // ログ情報などにより抜き取られる危険性がある。その防止策として
            // セッション渡す際はLoginUserとして、PW情報を持つのはAccountとする
            return new LoginUser(account.getLoginId(), account.getLoginName());

        }

    }
}
