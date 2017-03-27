package com.manishkprboilerplate.models;

/**
 * Created by Munish on 16/3/17.
 */

import android.support.v4.app.Fragment;

public class FragmentItem {

    private String title;
    private int idMenuItem;
    private Fragment fragment;

    public FragmentItem(Fragment fragment, String title, int idMenuItem) {

        this.fragment = fragment;
        this.title = title;
        this.idMenuItem = idMenuItem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public int getIdMenuItem() {
        return idMenuItem;
    }

    public void setIdMenuItem(int idMenuItem) {
        this.idMenuItem = idMenuItem;
    }
}
