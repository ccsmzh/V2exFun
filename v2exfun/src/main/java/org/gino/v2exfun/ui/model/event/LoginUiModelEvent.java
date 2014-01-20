package org.gino.v2exfun.ui.model.event;

import org.gino.v2exfun.data.serialize.model.Member;
import org.gino.v2exfun.data.serialize.model.V2exSession;

/**
 * Created by hongzhuo on 14-1-16.
 */
public interface LoginUiModelEvent extends BaseUiModelEvent{
    public void onLogin(String userName, String passWord);
    public void onLoginSucceed(V2exSession session,Member member);
    public void onLoginFaild();
}
