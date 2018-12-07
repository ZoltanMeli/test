package com.ml.zszabo.segunda.Util;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventUtils {

    public static class LoginEvent {
        private final int event;
        public static final int LOGIN_SUCCESSFULL = 1;
        public static final int LOGOUT_SUCCESSFULL = 2;
        public static final int LOGIN_FAILED = 0;


        public LoginEvent(int event) {
            this.event = event;
        }

        public int getEvent() {
            return event;
        }
    }
}
