package com.mostafagazar.insetdivideritemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class InsetDividerItemDecoration(
    context: Context,
    private val insetDividerLeft: Int) : RecyclerView.ItemDecoration() {

    private val attributesArray = intArrayOf(android.R.attr.listDivider)

    private var dividerDrawable: Drawable? = null

    init {
        val typedArray = context.obtainStyledAttributes(attributesArray)
        dividerDrawable = typedArray.getDrawable(0)
        if (dividerDrawable == null) {
            Log.w("InsetDivider", "@android:attr/listDivider was not set in the theme used here")
        }
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null || dividerDrawable == null) {
            return
        }

        val left = parent.paddingLeft + insetDividerLeft
        val right = parent.width - parent.paddingRight

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom: Int = top + (dividerDrawable?.intrinsicHeight ?: 0)

            dividerDrawable?.setBounds(left, top, right, bottom)
            dividerDrawable?.draw(canvas)
        }
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) = if (dividerDrawable == null) {
        outRect.set(0, 0, 0, 0)
    } else {
        outRect.set(0, 0, 0, dividerDrawable?.intrinsicHeight ?: 0)
    }

}