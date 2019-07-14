package com.xihua.wx.weixiao.mvp.presenter.impl;


import com.xihua.wx.weixiao.mvp.presenter.MvpPresenter;
import com.xihua.wx.weixiao.mvp.view.MvpView;

/**
 * Created by Dream on 16/5/26.
 */
public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (view !=null){
            view = null;
        }
    }
}
