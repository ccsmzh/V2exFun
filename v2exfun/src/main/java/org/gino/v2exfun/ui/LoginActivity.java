package org.gino.v2exfun.ui;

import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import org.gino.v2exfun.MyApplication;
import org.gino.v2exfun.R;
import org.gino.v2exfun.data.serialize.database.DatabaseHelper;
import org.gino.v2exfun.data.serialize.model.Member;
import org.gino.v2exfun.data.serialize.model.V2exSession;
import org.gino.v2exfun.ui.model.LoginUiModel;
import org.gino.v2exfun.ui.model.event.LoginUiModelEvent;
import org.gino.v2exfun.ui.view.ResizeRelativeLayout;
import org.gino.v2exfun.ui.view.ResizeTextView;
import org.gino.v2exfun.utils.CommonUtils;
import org.gino.v2exfun.utils.VLog;

public class LoginActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setTitle("登录");
//        actionBar.setIcon(R.drawable.ab_icon_v2exfun);
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
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener {

//        private DatabaseHelper

        private EditText mUserNameEditText;
        private EditText mPassWordEditText;

        private Button mLoginButton;

        private LinearLayout mInputLinearLayout;

        private ImageView mLogoImageView;

        private LoginUiModel mLoginUiModel;
        private LoginUiModelEvent mEvent;

        private DatabaseHelper databaseHelper = null;

        private ValueAnimator upAnimator = null;
        private ValueAnimator downAnimator = null;
        private ValueAnimator logoUpAnimator = null;
        private ValueAnimator logoDownAnimator = null;
        private float defaultY = -1;

        public PlaceholderFragment() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_login, container, false);

            mUserNameEditText = (EditText) rootView.findViewById(R.id.fl_et_username);
            mPassWordEditText = (EditText) rootView.findViewById(R.id.fl_et_password);

            mInputLinearLayout = (LinearLayout) rootView.findViewById(R.id.fl_ll_inputlayout);
            mLogoImageView = (ImageView) rootView.findViewById(R.id.fl_iv_logo);

            ((ResizeRelativeLayout) rootView).setOnResizeListener(new ResizeRelativeLayout.OnResizeListener() {
                @Override
                public void onSizeChanged(int w, int h, int oldw, int oldh) {

                }

                @Override
                public boolean onLayout(boolean changed, int l, int t, int r, int b) {
                    int screenHeightIntPx = CommonUtils.getDisplayHeight(PlaceholderFragment.this.getActivity());
                    if (b < screenHeightIntPx * 0.8) {
                        if (downAnimator == null) {
                            initAnimator(b);
                        }
                        upAnimator.start();
                        logoUpAnimator.start();
                        return false;
                    } else {
                        if (mInputLinearLayout.getY() != defaultY && downAnimator != null) {
                            downAnimator.start();
                            logoDownAnimator.start();
                        }
                        return true;
                    }
                }
            });

            mLoginButton = (Button) rootView.findViewById(R.id.fl_btn_login);
            mLoginButton.setOnClickListener(this);

            mEvent = new LoginUiModelEvent() {
                @Override
                public void onLogin(String userName, String passWord) {
//                    getHelper().getV2exSessionDataDao().cre
                }

                @Override
                public void onLoginSucceed(V2exSession session, Member member) {
                    Log.e("TAG", "succeed");
                }

                @Override
                public void onLoginFaild() {
                    Log.e("TAG", "faild");
                }
            };
            mLoginUiModel = new LoginUiModel();
            mLoginUiModel.regEvent(mEvent);
            return rootView;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fl_btn_login:
                    mLoginUiModel.onLogin(mUserNameEditText.getText().toString(), mPassWordEditText.getText().toString());
                    break;
            }
        }

        private DatabaseHelper getHelper() {
            if (databaseHelper == null) {
                databaseHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
            }
            return databaseHelper;
        }

        private void initAnimator(int height) {
            defaultY = mInputLinearLayout.getY();

            int displayHeight = CommonUtils.getDisplayHeight(PlaceholderFragment.this.getActivity());
            int layoutHeight = mInputLinearLayout.getMeasuredHeight();

            int srcMarginBottom = (displayHeight - layoutHeight) / 2;
            int dstMarginBottom = (height - mInputLinearLayout.getMeasuredHeight()) / 5 + (displayHeight - height) - srcMarginBottom;

            upAnimator = ValueAnimator.ofFloat(mInputLinearLayout.getY(), mInputLinearLayout.getY() - dstMarginBottom);
            upAnimator.setDuration(200);
            upAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mInputLinearLayout.setY((Float) animation.getAnimatedValue());
                }
            });
            upAnimator.setInterpolator(new DecelerateInterpolator());

            logoUpAnimator = ValueAnimator.ofFloat(mLogoImageView.getY(), mLogoImageView.getY() - dstMarginBottom);
            logoUpAnimator.setDuration(200);
            logoUpAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mLogoImageView.setY((Float) animation.getAnimatedValue());
                }
            });
            logoUpAnimator.setInterpolator(new DecelerateInterpolator());

            downAnimator = ValueAnimator.ofFloat(mInputLinearLayout.getY() - dstMarginBottom, mInputLinearLayout.getY());
            downAnimator.setDuration(200);
            downAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mInputLinearLayout.setY((Float) animation.getAnimatedValue());
                }
            });

            logoDownAnimator = ValueAnimator.ofFloat(mLogoImageView.getY() - dstMarginBottom, mLogoImageView.getY());
            logoDownAnimator.setDuration(200);
            logoDownAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mLogoImageView.setY((Float) animation.getAnimatedValue());
                }
            });
        }
    }

}
