package com.model.AccountLogin;

import java.sql.Connection;
import java.sql.SQLException;

import com.model.ConnectionManager;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class LoginLogic {
    
    // ログイン判定する
    public LoginUser isLogin(int loginId, String loginPw) throws SQLException{
        
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
