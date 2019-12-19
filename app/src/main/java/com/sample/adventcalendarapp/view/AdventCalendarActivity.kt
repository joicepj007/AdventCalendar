package com.sample.adventcalendarapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.os.Bundle

import com.sample.adventcalendarapp.R
import com.sample.adventcalendarapp.adapter.AdventAdapter
import com.sample.adventcalendarapp.beans.AdventDay
import com.sample.adventcalendarapp.model.AdventModelImpl
import com.sample.adventcalendarapp.presenter.AdventPresenterImpl
import com.sample.adventcalendarapp.view.interfaces.AdventView

import java.util.ArrayList

class AdventCalendarActivity : AppCompatActivity(), AdventView {

    private var vAdventRecyclerView: RecyclerView? = null
    private val mAdventDayList = ArrayList<AdventDay>()
    private var mAdventAdapter: AdventAdapter? = null
    private var mAdventPresenter: AdventPresenterImpl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advent)
        init()
        setValues()
    }

    private fun init() {
        vAdventRecyclerView = findViewById(R.id.rv_advent_list)
        mAdventAdapter = AdventAdapter(this, mAdventDayList)
        mAdventPresenter = AdventPresenterImpl(this, AdventModelImpl())
    }

    private fun setValues() {
        vAdventRecyclerView!!.layoutManager = GridLayoutManager(this, 3)
        vAdventRecyclerView!!.adapter = mAdventAdapter
    }

    override fun onAdventDayFetchSuccess(adventDayList: List<AdventDay>) {
        mAdventDayList.clear()
        mAdventDayList.addAll(adventDayList)
        mAdventAdapter!!.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        mAdventPresenter!!.getAdventDayList()
    }
}
