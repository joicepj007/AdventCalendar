package com.sample.adventcalendarapp.presenter

import com.sample.adventcalendarapp.beans.AdventDay
import com.sample.adventcalendarapp.model.AdventModelImpl
import com.sample.adventcalendarapp.view.interfaces.AdventView

class AdventPresenterImpl(private val mAdventView: AdventView, private val mAdventModel: AdventModelImpl) {

    fun getAdventDayList() {
        mAdventModel.getAdventDays(object : AdventModelImpl.AdventCallback {
            override fun onAdventDayFetchSuccess(adventDayList: List<AdventDay>) {
                mAdventView.onAdventDayFetchSuccess(adventDayList)
            }
        })
    }
}
