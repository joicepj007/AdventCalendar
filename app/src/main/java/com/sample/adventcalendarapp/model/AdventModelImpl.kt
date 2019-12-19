package com.sample.adventcalendarapp.model

import com.sample.adventcalendarapp.R
import com.sample.adventcalendarapp.beans.AdventDay

import java.util.ArrayList
import java.util.Calendar
import java.util.Random

open class AdventModelImpl {

    private val emptySpaceImages = intArrayOf(R.drawable.santa1, R.drawable.santa2, R.drawable.santa3, R.drawable.santa4, R.drawable.santa5)
    private val surpriseImages = intArrayOf(R.drawable.xmas1, R.drawable.xmas2, R.drawable.xmas3, R.drawable.xmas4, R.drawable.xmas5, R.drawable.xmas6, R.drawable.xmas7, R.drawable.xmas8)
    private val backgroundImages = intArrayOf(R.drawable.advent_day_background_grey, R.drawable.advent_day_background_pink, R.drawable.advent_day_background_white, R.drawable.advent_day_background_red, R.drawable.advent_day_background_cyan, R.drawable.advent_day_background_blue, R.drawable.advent_day_background_white, R.drawable.advent_day_background_pink, R.drawable.advent_day_background_violet, R.drawable.advent_day_background_red, R.drawable.advent_day_background_cyan, R.drawable.advent_day_background_violet, R.drawable.advent_day_background_white, R.drawable.advent_day_background_blue)

    fun getAdventDays(callback: AdventCallback) {
        val adventDayList = ArrayList<AdventDay>()
        val daysList = createDays()
        while (daysList.size != 0) {
            val calendarDay = Calendar.getInstance()
            val adventDay = AdventDay()
            val random = Random()
            val position: Int
            position = if (daysList.size - 1 != 0) {
                random.nextInt(daysList.size - 1)
            } else {
                0
            }
            val day = daysList[position]
            daysList.removeAt(position)
            calendarDay.set(Calendar.DAY_OF_MONTH, day)
            adventDay.calendarDay = calendarDay
            adventDay.dayBackground = getRandomImages(backgroundImages)
            adventDay.surpriseImage = getRandomImages(surpriseImages)
            adventDayList.add(adventDay)
        }
        for (emptySpace in 0..4) {
            val adventDay = AdventDay()
            adventDay.isEmpty = true
            adventDay.surpriseImage = emptySpaceImages[emptySpace]
            val random = Random()
            adventDayList.add(random.nextInt(adventDayList.size - 1), adventDay)
        }
        callback.onAdventDayFetchSuccess(adventDayList)
    }

    private fun getRandomImages(images: IntArray): Int {
        val random = Random()
        return images[random.nextInt(images.size - 1)]
    }

    private fun createDays(): MutableList<Int> {
        val days = ArrayList<Int>()
        days.add(1)
        days.add(2)
        days.add(3)
        days.add(4)
        days.add(5)
        days.add(6)
        days.add(7)
        days.add(8)
        days.add(9)
        days.add(10)
        days.add(11)
        days.add(12)
        days.add(13)
        days.add(14)
        days.add(15)
        days.add(16)
        days.add(17)
        days.add(18)
        days.add(19)
        days.add(20)
        days.add(21)
        days.add(22)
        days.add(23)
        days.add(24)
        days.add(25)
        return days
    }

    interface AdventCallback {
        fun onAdventDayFetchSuccess(adventDayList: List<AdventDay>)
    }
}
