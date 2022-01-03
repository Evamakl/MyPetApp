package com.example.myproject;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class tipsPKClass implements Serializable {
    String text ="";
    String UserInfo ="";
    public tipsPKClass(String text, String UserInfo) {
        this.text = text;
        this.UserInfo = UserInfo;
    }

    public tipsPKClass() { }

    @Override
    public boolean equals(@Nullable Object obj) {
        String text = ((tipsPKClass)obj).getText();
        String info = ((tipsPKClass)obj).getUserInfo();
        return this.text.equals(text) && this.UserInfo.equals(info);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserInfo() {
        return UserInfo;
    }

    public void setUserInfo(String userInfo) {
        UserInfo = userInfo;
    }
}
