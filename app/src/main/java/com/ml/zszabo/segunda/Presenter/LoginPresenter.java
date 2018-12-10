package com.ml.zszabo.segunda.Presenter;

import android.os.Handler;
import android.text.TextUtils;

import com.ml.zszabo.segunda.Model.User;
import com.ml.zszabo.segunda.Util.EventUtils;

import org.greenrobot.eventbus.EventBus;

public class LoginPresenter {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEmail(String email) {
        if (user == null) {
            user = new User();
        }
        user.setEmail(email);
    }

    public void setPassword(String password) {
        if (user == null) {
            user = new User();
        }
        user.setPassword(password);
    }

    public void attemptLogin() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new EventUtils.LoginEvent(EventUtils.LoginEvent.LOGIN_SUCCESSFULL));
            }
        }, 500);
    }

    public boolean isPasswordValid() {
        return user != null && user.getPassword()!=null && (user.getPassword().length() > 4 || user.getPassword().length() == 0);
    }


    public boolean isEmailValid() {
        return user != null && user.getEmail().contains("@");
    }
}
