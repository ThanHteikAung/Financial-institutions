package com.tha.testforexam.mvp.presenters

import com.tha.testforexam.mvp.views.MainView

interface MainPresenter {
    fun initView(view: MainView)
    fun onTapSearchBtn()
    fun onTapClearBtn()
}