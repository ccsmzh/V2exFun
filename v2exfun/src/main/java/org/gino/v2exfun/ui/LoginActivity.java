package org.gino.v2exfun.ui;

import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.content.Context;
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
import org.gino.v2exfun.utils.VLog;

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

        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setTitle("登录");
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

        private ResizeTextView mForgetPwdTextView;

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

            mInputLinearLayout = (LinearLayout) rootView.findViewById(R.id.fl_ll_inputlayout);
            mLogoImageView = (ImageView) rootView.findViewById(R.id.fl_iv_logo);

            mInputLinearLayout.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
                @Override
                public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                    defaultY = mInputLinearLayout.getY();

                    upAnimator = ValueAnimator.ofFloat(mInputLinearLayout.getY(),mInputLinearLayout.getY() - 150 );
                    upAnimator.setDuration(200);
                    upAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            mInputLinearLayout.setY((Float) animation.getAnimatedValue());
                        }
                    });
                    upAnimator.setInterpolator(new DecelerateInterpolator());

                    logoUpAnimator = ValueAnimator.ofFloat(mLogoImageView.getY(),mLogoImageView.getY() - 150 );
                    logoUpAnimator.setDuration(200);
                    logoUpAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            mLogoImageView.setY((Float) animation.getAnimatedValue());
                        }
                    });
                    logoUpAnimator.setInterpolator(new DecelerateInterpolator());

                    downAnimator = ValueAnimator.ofFloat(mInputLinearLayout.getY() - 150,mInputLinearLayout.getY());
                    downAnimator.setDuration(200);
                    downAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            mInputLinearLayout.setY((Float) animation.getAnimatedValue());
                        }
                    });

                    logoDownAnimator = ValueAnimator.ofFloat(mLogoImageView.getY() - 150,mLogoImageView.getY());
                    logoDownAnimator.setDuration(200);
                    logoDownAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            mLogoImageView.setY((Float) animation.getAnimatedValue());
                        }
                    });

                    mInputLinearLayout.getViewTreeObserver().removeOnGlobalFocusChangeListener(this);
                }
            });


            ((ResizeRelativeLayout) rootView).setOnResizeListener(new ResizeRelativeLayout.OnResizeListener() {
                @Override
                public void onSizeChanged(int w, int h, int oldw, int oldh) {

                }

                @Override
                public boolean onLayout(boolean changed, int l, int t, int r, int b) {
                    android.view.WindowManager windowsManager =
                            (android.view.WindowManager) MyApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
                    android.view.Display display = windowsManager.getDefaultDisplay();
                    int screenHeightIntPx = display.getHeight();//
                    if (b < screenHeightIntPx * 0.8) {
                        VLog.e("弹出");
                        upAnimator.start();
                        logoUpAnimator.start();
                        return false;
                    } else {
                        VLog.e("收回");
                        if(mInputLinearLayout.getY() != defaultY && downAnimator != null){
                            VLog.e("收回动画");
                            downAnimator.start();
                            logoDownAnimator.start();
                        }
                        return true;
                    }
                }
            });
            mUserNameEditText = (EditText) rootView.findViewById(R.id.fl_et_username);
            mPassWordEditText = (EditText) rootView.findViewById(R.id.fl_et_password);

            mLoginButton = (Button) rootView.findViewById(R.id.fl_btn_login);
            mLoginButton.setOnClickListener(this);



            mForgetPwdTextView = (ResizeTextView) rootView.findViewById(R.id.fl_rtv_forgetpwd);
//            mForgetPwdTextView.setOnResizeListener(new ResizeTextView.OnResizeListener() {
//                @Override
//                public void onResize(boolean changed, int left, int top, int right, int bottom) {
//                    int tTotleHeight = mInputLinearLayout.getMeasuredHeight() + mLogoImageView.getMeasuredHeight();
//                    if((bottom - mForgetPwdTextView.getMeasuredHeight()) <= tTotleHeight){
////                        mLogoImageView.scrollTo(0,50);
////                        mLogoImageView.setTranslationY(-100);
//                        mLogoImageView.setY(mLogoImageView.getY()-100);
////                        mInputLinearLayout.setTranslationY(-150);
////                        mInputLinearLayout.offsetTopAndBottom(-150);
//                        mInputLinearLayout.setY(mInputLinearLayout.getY()-100);
////                        mLoginButton.setTranslationY(-100);
////                        mInputLinearLayout.scrollTo(0,50);
////                        mLoginButton.scrollTo(0,50);
//                    }else{
////                        mLogoImageView.setTranslationY(0);
////                        mInputLinearLayout.setTranslationY(0);
////                        mLogoImageView.setY(0);
//                        mLogoImageView.requestLayout();
//                        mInputLinearLayout.requestLayout();
////                        mInputLinearLayout.setY(0);
////                        mLoginButton.setTranslationY(0);
////                        mLogoImageView.scrollTo(0,0);
////                        mInputLinearLayout.scrollTo(0,0);
////                        mLoginButton.scrollTo(0,0);
//                    }
//
//                    Log.e("TAG","height=>" + tTotleHeight);
//                    Log.e("TAG","bottom=>" + bottom);
//                    Log.e("TAG","top=>" + top);
//
//                }
//            });

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
    }

}
