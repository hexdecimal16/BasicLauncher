package com.basic.android.basiclauncher.widgets

import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.basic.android.basiclauncher.InstalledAppsAdapter
import com.basic.android.basiclauncher.favorite.AddAppsAdapter

class EditableRecyclerView(context: Context) : RecyclerView(context) {

    override fun focusSearch(focused: View, direction: Int): View {
        return if (focused.isSelected) {
            swapItemsIfNeeded(focused, direction)
        } else super.focusSearch(focused, direction)
    }

    override fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("RecyclerView", "KeyCode: $keyCode event: $event");
        return super.onKeyLongPress(keyCode, event)
    }

    public fun swapItemsIfNeeded(focused: View, direction: Int): View {
        val position = getChildAdapterPosition(focused)
        if (arrange) {
            if (canMoveInDirection(position, direction)) {
                when (direction) {
                    FOCUS_LEFT -> moveChannel(position, position - 1)
                    FOCUS_UP -> moveChannel(position, position - NUM_COLUMN)
                    FOCUS_RIGHT -> moveChannel(position, position + 1)
                    FOCUS_DOWN -> moveChannel(position, position + NUM_COLUMN)
                }
            }
        }
        return focused
    }

    private fun canMoveInDirection(position: Int, direction: Int): Boolean {
        when (direction) {
            FOCUS_LEFT -> {
                return position % NUM_COLUMN > 0
            }
            FOCUS_UP -> {
                return position - NUM_COLUMN >= 0
            }
            FOCUS_RIGHT -> {
                return !(position % NUM_COLUMN >= (NUM_COLUMN - 1) ||
                        position >= adapter!!.itemCount - 1)
            }
            FOCUS_DOWN -> {
                return position + NUM_COLUMN <= adapter!!.itemCount - 1
            }
            else -> {
                return false
            }
        }
    }

    private fun moveChannel(fromPosition: Int, toPosition: Int) {
        (adapter as InstalledAppsAdapter).moveChannel(fromPosition, toPosition)
    }

    companion object {

        private const val NUM_COLUMN: Int = 6

    }
}