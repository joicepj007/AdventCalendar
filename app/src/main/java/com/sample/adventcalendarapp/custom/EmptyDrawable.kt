package com.sample.adventcalendarapp.custom

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.sample.adventcalendarapp.R

class EmptyDrawable(context: Context, private val mDrawable: Drawable?) : Drawable() {
    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mStrokePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPaint.color = Color.WHITE
        mStrokePaint.style = Paint.Style.STROKE
        mStrokePaint.color = ContextCompat.getColor(context, R.color.colorLightGrey)
    }

    override fun draw(canvas: Canvas) {
        val bounds = bounds
        val width = bounds.right / 2
        canvas.drawRect(bounds, mPaint)
        canvas.drawRect(bounds, mStrokePaint)
        val left = bounds.right / 2 - width / 2
        val right = left + width
        val top = bounds.bottom / 2 - width / 2
        val bottom = top + width
        mDrawable?.setBounds(left, top, right, bottom)
        mDrawable?.draw(canvas)
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }
}
