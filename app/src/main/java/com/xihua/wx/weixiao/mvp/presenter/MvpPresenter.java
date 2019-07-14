package com.xihua.wx.weixiao.mvp.presenter;


import com.xihua.wx.weixiao.mvp.view.MvpView;

/**
 * 中介
 * Created by Dream on 16/5/26.
 */
public interface MvpPresenter<V extends MvpView> {
    //绑定找房子人(说白了就是我)
    public void attachView(V view);
    //解除绑定(说白了就是不和我联系了)
    public void detachView();
}
