package org.gino.v2exfun.ui;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import org.gino.v2exfun.R;
import org.gino.v2exfun.data.serialize.database.DatabaseHelper;
import org.gino.v2exfun.data.serialize.model.Member;
import org.gino.v2exfun.data.serialize.model.V2exSession;
import org.gino.v2exfun.ui.model.LoginUiModel;
import org.gino.v2exfun.ui.model.event.LoginUiModelEvent;

public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener{

//        private DatabaseHelper

        private EditText mUserNameEditText;
        private EditText mPassWordEditText;

        private Button mLoginButton;

        private LoginUiModel mLoginUiModel;
        private LoginUiModelEvent mEvent;

        private DatabaseHelper databaseHelper = null;
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_login, container, false);
            mUserNameEditText = (EditText) rootView.findViewById(R.id.fl_et_username);
            mPassWordEditText = (EditText) rootView.findViewById(R.id.fl_et_password);
            mLoginButton = (Button) rootView.findViewById(R.id.fl_btn_login);
            mLoginButton.setOnClickListener(this);

            mEvent = new LoginUiModelEvent() {
                @Override
                public void onLogin(String userName, String passWord) {
//                    getHelper().getV2exSessionDataDao().cre
                }

                @Override
                public void onLoginSucceed(V2exSession session, Member member) {

                }

                @Override
                public void onLoginFaild() {

                }
            };
            mLoginUiModel = new LoginUiModel();
            mLoginUiModel.regEvent(mEvent);
            return rootView;
        }

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.fl_btn_login:
                    mLoginUiModel.onLogin(mUserNameEditText.getText().toString(),mPassWordEditText.getText().toString());
                    break;
            }
        }

        private DatabaseHelper getHelper() {
            if (databaseHelper == null) {
                databaseHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
            }
            return databaseHelper;
        }
    }

}
