package com.codepath.yjoh.yumspot;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Message")
public class Message extends ParseObject {
    public static final String USER_ID_KEY = "userId";
    public static final String BODY_KEY = "body";
    public static final String USER_NUMBER_KEY = "number";

    public String getUserId() {
        return getString(USER_ID_KEY);
    }

    public String getBody() {
        return getString(BODY_KEY);
    }

    public void setUserId(String userId) {
        put(USER_ID_KEY, userId);
    }

    public void setBody(String body) {
        put(BODY_KEY, body);
    }

    public int getNumber() { return getInt(USER_NUMBER_KEY); }

    public void setUserNumberKey(int userNumber) { put(USER_NUMBER_KEY, userNumber);
    }
}
