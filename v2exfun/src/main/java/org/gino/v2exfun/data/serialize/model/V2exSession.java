package org.gino.v2exfun.data.serialize.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by hongzhuo on 14-1-20.
 */
@DatabaseTable(tableName =  "user_sessions")
public class V2exSession {
    @DatabaseField(id = true)
    public String userName;

    @DatabaseField
    public String pb3Session;

    public V2exSession(String userName, String pb3Session){
        this.userName = userName;
        this.pb3Session = pb3Session;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPb3Session() {
        return pb3Session;
    }

    public void setPb3Session(String pb3Session) {
        this.pb3Session = pb3Session;
    }
}
