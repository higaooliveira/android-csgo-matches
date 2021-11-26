package com.higor.csgomatches.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.higor.csgomatches.R

@SuppressLint("CustomViewStyleable", "Recycle")
open class ForegroundImageView(
    context: Context,
    attributeSet: AttributeSet,
    defStyleAttr: Int
) : AppCompatImageView(context, attributeSet, defStyleAttr) {

    private var foreground: Drawable? = null

    init {
        val styledAttribute = context.obtainStyledAttributes(attributeSet, R.styleable.ForegroundView)

        val drawable = styledAttribute.getDrawable(R.styleable.ForegroundView_android_foreground)

        drawable?.let {
            setForeground(it)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        this.foreground?.setBounds(0, 0, w, h)
    }

    override fun hasOverlappingRendering(): Boolean {
        return false
    }

    override fun verifyDrawable(dr: Drawable): Boolean {
        return super.verifyDrawable(dr) || (dr == this.foreground)
    }

    override fun jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState()
        this.foreground?.jumpToCurrentState()
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()

        if (this.foreground?.isStateful == true) {
            this.foreground?.state = drawableState
        }
    }

    override fun getForeground(): Drawable? {
        return this.foreground
    }

    override fun setForeground(drawable: Drawable) {
        if (this.foreground != drawable) {
            this.foreground?.callback = null
            unscheduleDrawable(this.foreground)
        }

        this.foreground = drawable

        this.foreground?.setBounds(0, 0, width, height)
        setWillNotDraw(false)
        this.foreground?.callback = this

        if(this.foreground?.isStateful == true) {
            this.foreground?.state = drawableState
        }

        invalidate()

    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        if(this.foreground != null) {
            this.foreground?.draw(canvas)
        }
    }
}