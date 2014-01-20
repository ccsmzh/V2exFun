package org.gino.v2exfun.data.serialize.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;

import org.gino.v2exfun.data.serialize.model.V2exSession;

import java.sql.SQLException;

/**
 * Created by hongzhuo on 14-1-20.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper{

    private static final String DATABASE_NAME = "";
    private static final int DATABASE_VERSION = 1;

    private Dao<V2exSession, Integer> v2exSessionDataDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {

    }

    public Dao<V2exSession,Integer> getV2exSessionDataDao() throws SQLException {
        if(v2exSessionDataDao == null){
            v2exSessionDataDao = getDao(V2exSession.class);
        }
        return v2exSessionDataDao;
    }
}
