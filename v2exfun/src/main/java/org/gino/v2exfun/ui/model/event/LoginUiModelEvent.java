package org.gino.v2exfun.ui.model.event;

/**
 * Created by hongzhuo on 14-1-16.
 */
public interface LoginUiModelEvent extends BaseUiModelEvent{
    public void onLogin(String userName, String passWord);
}
