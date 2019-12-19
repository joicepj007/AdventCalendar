package com.sample.adventcalendarapp.presenter

import com.sample.adventcalendarapp.beans.AdventDay
import com.sample.adventcalendarapp.model.AdventModelImpl
import com.sample.adventcalendarapp.view.interfaces.AdventView

import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock

import java.util.ArrayList

import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks

class AdventPresenterImplTest {
    @Mock
    internal var mAdventModel: AdventModelImpl? = null
    @Mock
    internal var mAdventView: AdventView? = null
    @Captor
    internal var mAdventCallbackArgumentCaptor: ArgumentCaptor<AdventModelImpl.AdventCallback>? = null

    private var mAdventPresenter: AdventPresenterImpl? = null

    @Before
    fun beforeTest() {
        initMocks(this)
        mAdventPresenter = AdventPresenterImpl(mAdventView!!, mAdventModel!!)
    }

    @Test
    fun shouldFetchAdventList() {
        val adventDayList = ArrayList<AdventDay>()
        mAdventPresenter!!.getAdventDayList()
        verify<AdventModelImpl>(mAdventModel).getAdventDays(mAdventCallbackArgumentCaptor!!.capture())
        mAdventCallbackArgumentCaptor!!.value.onAdventDayFetchSuccess(adventDayList)
        verify<AdventView>(mAdventView).onAdventDayFetchSuccess(eq<List<AdventDay>>(adventDayList))
    }
}