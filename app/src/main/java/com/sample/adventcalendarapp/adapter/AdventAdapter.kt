package com.sample.adventcalendarapp.adapter

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.adventcalendarapp.beans.AdventDay
import com.sample.adventcalendarapp.custom.EmptyDrawable
import java.util.*

class AdventAdapter(private val mContext: Context, private val mAdventList: List<AdventDay>) : RecyclerView.Adapter<AdventAdapter.AdventViewHolder>() {
    private val mLayoutInflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdventViewHolder {
        return AdventViewHolder(mLayoutInflater.inflate(com.sample.adventcalendarapp.R.layout.view_advent_day, parent,
                false))
    }

    override fun onBindViewHolder(holder: AdventViewHolder, position: Int) {
        val adventDay = mAdventList[position]
        if (mContext is Activity) {
            val activity = mContext
            val height = getHeightOfTheDevice(activity)
            val width = getWidthOfTheDevice(activity)
            val layoutParams = holder.vRootLayout.layoutParams
            layoutParams.height = height / 10
            layoutParams.width = width / 3
            if (adventDay.surpriseImage != -1) {
                if (adventDay.isEmpty) {
                    Glide.with(mContext)
                            .load(EmptyDrawable(mContext, ContextCompat.getDrawable(mContext,
                                    adventDay.surpriseImage)))
                            .override(width / 3, height / 10)
                            .into(holder.vSurpriseImage)
                } else {
                    Glide.with(mContext)
                            .load(ContextCompat.getDrawable(mContext, adventDay.surpriseImage))
                            .override(width / 3, height / 10)
                            .into(holder.vSurpriseImage)
                }
            }
        }
        if (getItemViewType(position) == VIEW_TYPE_IMAGE) {
            holder.vSurpriseImage.visibility = View.VISIBLE
            holder.vCalendarDayText.visibility = View.GONE
        } else {
            val currentDay = Calendar.getInstance()
            val day = adventDay.calendarDay?.get(Calendar.DAY_OF_MONTH)
            if (day!! <= currentDay.get(Calendar.DAY_OF_MONTH)) {
                //open surprise image
                holder.vRootLayout.setOnClickListener {
                    val adapterPosition = holder.adapterPosition
                    val mDay = mAdventList[position]
                    if (mDay.isImageVisible) {
                        holder.vSurpriseImage.visibility = View.GONE
                        holder.vCalendarDayText.visibility = View.VISIBLE
                        mDay.isImageVisible = false
                    } else {
                        holder.vSurpriseImage.visibility = View.VISIBLE
                        holder.vCalendarDayText.visibility = View.GONE
                        mDay.isImageVisible = true
                    }
                    notifyItemChanged(adapterPosition)
                }
            }
            val dayText = day.toString() + ""
            holder.vCalendarDayText.text = dayText
            holder.vCalendarDayText.setTextColor(if (adventDay.dayBackground == com.sample.adventcalendarapp.R.drawable.advent_day_background_white) ContextCompat.getColor(mContext, com.sample.adventcalendarapp.R.color.colorBlack) else ContextCompat.getColor(mContext, com.sample.adventcalendarapp.R.color.colorWhite))
            holder.vCalendarDayText.background = ContextCompat.getDrawable(mContext,
                    adventDay.dayBackground)
            if (adventDay.isImageVisible) {
                holder.vSurpriseImage.visibility = View.VISIBLE
                holder.vCalendarDayText.visibility = View.GONE
            } else {
                holder.vSurpriseImage.visibility = View.GONE
                holder.vCalendarDayText.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val adventDay = mAdventList[position]
        return if (adventDay.isEmpty) VIEW_TYPE_IMAGE else VIEW_TYPE_CALENDAR_DAY
    }

    override fun getItemCount(): Int {
        return mAdventList.size
    }

    inner class AdventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var vRootLayout: FrameLayout = itemView as FrameLayout
        var vSurpriseImage: ImageView = itemView.findViewById(com.sample.adventcalendarapp.R.id.iv_advent_image)
        var vCalendarDayText: TextView = itemView.findViewById(com.sample.adventcalendarapp.R.id.tv_advent_day)

    }

    private fun getWidthOfTheDevice(activity: Activity): Int {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    private fun getHeightOfTheDevice(activity: Activity): Int {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels - getStatusBarHeight(activity)+getNavigationBarHeight(activity)
    }

    private fun getStatusBarHeight(activity: Activity): Int {
        var result = 0
        val resourceId = activity.resources.getIdentifier("status_bar_height", "dimen",
                "android")
        if (resourceId > 0) {
            result = activity.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
    private fun getNavigationBarHeight(activity: Activity): Int {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        val usableHeight = metrics.heightPixels
        activity.windowManager.defaultDisplay.getRealMetrics(metrics)
        val realHeight = metrics.heightPixels
        return if (realHeight > usableHeight)
            realHeight - usableHeight
        else
            0
    }
    companion object {
        private const val VIEW_TYPE_IMAGE = 1
        private const val VIEW_TYPE_CALENDAR_DAY = 2
    }
}
