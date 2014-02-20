package org.gino.v2exfun.data.serialize.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.HashMap;

/**
 * Created by hongzhuo on 14-1-20.
 */
@DatabaseTable(tableName = "user_sessions")
public class V2exSession {
    @DatabaseField(id = true)
    public String userName;

    @DatabaseField
    public String pb3Session;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public HashMap<String, String> cookies;

    public V2exSession(String userName, HashMap<String, String> cookies) {
        this.userName = userName;
        this.cookies = cookies;
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

    public HashMap<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(HashMap<String, String> cookies) {
        this.cookies = cookies;
    }
}
