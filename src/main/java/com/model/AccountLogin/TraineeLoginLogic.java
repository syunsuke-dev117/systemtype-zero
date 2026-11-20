package com.model.AccountLogin;

import java.sql.Connection;
import java.sql.SQLException;

import com.model.ConnectionManager;

public class TraineeLoginLogic {
    
    // ログイン判定する
    public LoginUser isLogin(int loginId, String loginPw) throws SQLException, NullPointerException{
        CommonLoginLogic cll = new CommonLoginLogic();
        
        try (Connection cn = ConnectionManager.getConnection()){
            // DAOからIDを取ってくる・IDがあればaccountに値が入ってる
            Account account = new LoginDAO(cn).findLoginId(loginId);
            // ぬるぽチェック
            Account checkedAccount = cll.nullCheckAccount(account);

            // ハッシュ化専用の外部ライブラリ BCyptを用いる(メソッドチェーン方式)
            // パスワード照合
            boolean result = cll.verificationPassword(loginPw, checkedAccount);    
            
            if(!result){
                throw new NullPointerException("パスワード照会の結果がnullです");
            }
            
            // 照合に成功すればここにくる
            // PWの重要データがないLoginUserに値を渡す
            // LoginUserはControllerに受け渡したのち、セッションに入る可能性があるため
            // 仮にセッションにLoginUserを入れてPW情報が入っていた場合
            // ログ情報などにより抜き取られる危険性がある。その防止策として
            // セッション渡す際はLoginUserとして、PW情報を持つのはAccountとする
            return new LoginUser(checkedAccount.getLoginId(), checkedAccount.getLoginName());

        }

    }
}
