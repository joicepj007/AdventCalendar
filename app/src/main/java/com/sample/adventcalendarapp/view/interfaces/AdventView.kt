package com.sample.adventcalendarapp.view.interfaces

import com.sample.adventcalendarapp.beans.AdventDay

interface AdventView {
    fun onAdventDayFetchSuccess(adventDayList: List<AdventDay>)
}
