package com.example.digiturkfilms.base

class BaseContract {
    interface Presenter<in T>

    interface View {
        fun showProgress()
        fun hideProgress()
    }
}