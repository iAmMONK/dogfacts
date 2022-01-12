package com.dev.monk.dogfacts.view.main.adapters

import android.graphics.*
import android.util.TypedValue
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dev.monk.dogfacts.R
import com.dev.monk.dogfacts.usecase.repositories.local.entities.FactEntity
import com.dev.monk.dogfacts.utils.ext.getString
import kotlin.math.abs
import kotlin.math.min

class SwipeCallback(
    private val onDelete: (FactEntity) -> Unit
) : ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
) {

    private val textPaint = Paint().apply {
        isAntiAlias = true
        color = Color.WHITE
        textSize = 30f
    }

    private val backGroundPaint = Paint().apply {
        color = Color.RED
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return when (viewHolder) {
            is SavedFactsAdapter.EmptyViewHolder -> false
            else -> true
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val itemView = viewHolder.itemView
        val text = recyclerView.getString(R.string.saved_facts_delete)

        val absolutedX = abs(dX)
        val alpha = min(absolutedX, 255f).toInt()
        val displayMetrics = recyclerView.context.resources.displayMetrics
        val fullWidth = displayMetrics.widthPixels

        textPaint.apply {
            this.alpha = alpha
            textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, displayMetrics)
        }
        backGroundPaint.alpha = alpha

        val rectF = RectF(
            0f,
            itemView.top.toFloat(),
            fullWidth.toFloat(),
            itemView.bottom.toFloat()
        )

        val textBounds = Rect()
        textPaint.getTextBounds(text, 0, text.length - 1, textBounds)

        val textX =
            if (dX < 0) fullWidth - (absolutedX / 2) - textBounds.width() / 2 - itemView.marginEnd / 2
            else absolutedX / 2

        c.drawRect(rectF, backGroundPaint)
        c.drawText(
            text,
            textX,
            itemView.bottom - itemView.height / 2f + textBounds.height() / 2,
            textPaint
        )
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (viewHolder is SavedFactsAdapter.SavedFactViewHolder) {
            viewHolder.fact?.let { onDelete(it) }
        }
    }
}
