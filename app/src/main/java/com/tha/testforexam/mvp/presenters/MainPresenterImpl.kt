package com.tha.testforexam.mvp.presenters

import androidx.lifecycle.ViewModel
import com.tha.testforexam.mvp.views.MainView

class MainPresenterImpl : ViewModel(), MainPresenter {
    private lateinit var mView: MainView
    override fun initView(view: MainView) {
        mView = view
    }

    //show user data in UI/UX
    override fun onTapSearchBtn() {
        mView.showUserAccountData()
    }

    //call clear method
    override fun onTapClearBtn() {
        mView.clearText()
    }

}