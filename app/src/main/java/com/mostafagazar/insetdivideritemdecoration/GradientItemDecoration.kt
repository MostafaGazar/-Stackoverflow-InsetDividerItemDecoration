package com.mostafagazar.insetdivideritemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GradientItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private var backgroundDrawable: Drawable? = null

    init {
        backgroundDrawable = context.getDrawable(R.drawable.background_gradient)
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null || backgroundDrawable == null) {
            return
        }

        val left = parent.paddingLeft
        val top = -parent.computeVerticalScrollOffset()
        val right = parent.width - parent.paddingRight

        val bottom = parent.computeVerticalScrollRange() + top
        backgroundDrawable?.setBounds(left, top, right, bottom)
        backgroundDrawable?.draw(canvas)
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) = outRect.set(0, 0, 0, 0)

}