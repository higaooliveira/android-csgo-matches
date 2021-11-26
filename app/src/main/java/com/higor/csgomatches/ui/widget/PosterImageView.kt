package com.higor.csgomatches.ui.widget

import android.content.Context
import android.util.AttributeSet

class PosterImageView(
    context: Context,
    attributeSet: AttributeSet,
    defStyleAttr: Int
): ForegroundImageView(context, attributeSet, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        var preferredWidth = width
        var preferredHeight = height

        if (width >= height) {
            preferredWidth = (2 * height) / 3
        } else {
            preferredHeight = (3 * width) / 2
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(preferredWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(preferredHeight, MeasureSpec.EXACTLY))
    }
}