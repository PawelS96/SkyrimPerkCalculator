package com.pawels96.skyrimperkcalculator.presentation.common.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.domain.ESpecialSkill
import com.pawels96.skyrimperkcalculator.domain.FPoint
import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.Perk
import com.pawels96.skyrimperkcalculator.domain.Skill
import com.pawels96.skyrimperkcalculator.domain.SkillType
import com.pawels96.skyrimperkcalculator.presentation.common.getName

class GraphView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val selectedStealthPaint = Paint()
    private val selectedCombatPaint = Paint()
    private val selectedMagicPaint = Paint()
    private val selectedVampirePaint = Paint()
    private val selectedWerewolfPaint = Paint()
    private val notSelectedPaint = Paint()
    private val textPaint = Paint()

    private var nodeRadius = 20
    private val textSize: Int
    private val textBounds = Rect()

    private var w = 0f
    private var h = 0f
    private var currentX = 0f
    private var currentY = 0f
    private var clickedX = 0f
    private var clickedY = 0f

    private val coordinates: Map<IPerk, FPoint>
        get() = skill?.getCoordinates() ?: emptyMap()

    private var touchedPerk: IPerk? = null

    var listener: OnNodeClickedListener? = null
    var skill: Skill? = null
        private set

    interface OnNodeClickedListener {
        fun onNodeClicked(perk: Perk)
        fun onNodeHolding(perk: Perk)
    }

    fun display(skill: Skill) {
        this.skill = skill
        invalidate()
    }

    init {
        textSize = spToPixels(context, 10)

        selectedStealthPaint.color = Color.GREEN
        selectedStealthPaint.strokeWidth = 5f
        selectedStealthPaint.flags = Paint.ANTI_ALIAS_FLAG

        selectedCombatPaint.color = Color.RED
        selectedCombatPaint.strokeWidth = 5f
        selectedCombatPaint.flags = Paint.ANTI_ALIAS_FLAG

        selectedMagicPaint.color = ContextCompat.getColor(context, R.color.colorMagic)
        selectedMagicPaint.strokeWidth = 5f
        selectedMagicPaint.flags = Paint.ANTI_ALIAS_FLAG

        selectedVampirePaint.color = ContextCompat.getColor(context, R.color.colorVampire)
        selectedVampirePaint.strokeWidth = 5f
        selectedVampirePaint.flags = Paint.ANTI_ALIAS_FLAG

        selectedWerewolfPaint.color = ContextCompat.getColor(context, R.color.colorWerewolf)
        selectedWerewolfPaint.strokeWidth = 5f
        selectedWerewolfPaint.flags = Paint.ANTI_ALIAS_FLAG

        notSelectedPaint.color = ContextCompat.getColor(context, R.color.colorGrayLight)
        notSelectedPaint.strokeWidth = 5f
        notSelectedPaint.flags = Paint.ANTI_ALIAS_FLAG

        textPaint.color = Color.WHITE
        textPaint.textSize = textSize.toFloat()
        textPaint.flags = Paint.ANTI_ALIAS_FLAG
    }

    private fun getNodeRadius(width: Int): Int {
        val baseSize = 15
        val ratio = width.toFloat() / 720f
        val size = (baseSize * ratio).toInt()
        return size.coerceAtMost(30)
    }

    private fun drawEdge(canvas: Canvas, start: Perk, end: Perk, selectedPaint: Paint) {
        val startPoint = coordinates[start.perk] ?: return
        val endPoint = coordinates[end.perk] ?: return
        val paint = if (start.isSelected && end.isSelected) selectedPaint else notSelectedPaint
        canvas.drawLine(
            startPoint.x * w,
            startPoint.y * h,
            endPoint.x * w,
            endPoint.y * h,
            paint
        )
    }

    private fun getSelectedPerkPaint(): Paint {
        val currentSkill = skill ?: return selectedCombatPaint
        return when (currentSkill.type) {
            SkillType.STEALTH -> selectedStealthPaint
            SkillType.COMBAT -> selectedCombatPaint
            SkillType.MAGIC -> selectedMagicPaint
            SkillType.SPECIAL -> when (currentSkill.iskill) {
                ESpecialSkill.SKILL_VAMPIRISM -> selectedVampirePaint
                ESpecialSkill.SKILL_LYCANTHROPY -> selectedWerewolfPaint
                else -> selectedCombatPaint
            }
        }
    }

    private fun drawConnections(canvas: Canvas, perks: List<Perk>) {
        val selectedPaint = getSelectedPerkPaint()
        for (parent in perks.filter { it.hasChildren }) {
            for (child in parent.children) {
                drawEdge(canvas, parent, child, selectedPaint)
            }
            drawConnections(canvas, parent.children)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        nodeRadius = getNodeRadius(width)
        canvas.drawColor(ContextCompat.getColor(context, R.color.colorAlmostBlack))
        w = width.toFloat()
        h = height.toFloat()

        skill?.let {
            drawConnections(canvas, it.perkList)
            drawNodes(canvas)
            drawLabels(canvas)
        }
    }

    private fun drawNodes(canvas: Canvas) {
        val selectedPaint = getSelectedPerkPaint()
        for (entry in coordinates.entries) {
            val perk = skill?.get(entry.key)
            if (perk != null) {
                val paint = if (perk.isSelected) selectedPaint else notSelectedPaint
                val point = entry.value
                canvas.drawCircle(point.x * w, point.y * h, nodeRadius.toFloat(), paint)
            }
        }
    }

    private fun drawLabels(canvas: Canvas) {
        for (entry in coordinates.entries) {
            val x = entry.value.x * w
            val y = entry.value.y * h

            val perk = skill?.get(entry.key) ?: continue
            var label = entry.key.getName(context)
            if (perk.isMultiState) {
                label += perk.stateAsString
            }

            textPaint.getTextBounds(label, 0, label.length, textBounds)
            val textWidth = textBounds.width()
            var textX = (x - textWidth / 2).toInt()
            if (textX < 0) {
                textX = 0
            } else if (textX > w - textWidth) {
                textX = w.toInt() - textWidth
            }

            var textY = y + nodeRadius * 2f + textBounds.height() / 5
            if (textY > h || textY < 0) {
                textY = y
            }

            canvas.drawText(label, textX.toFloat(), textY, textPaint)
        }
    }

    private fun getClickedPerk(x: Float, y: Float): IPerk? {
        val clickRadius = nodeRadius * 2
        return coordinates.entries.firstOrNull { entry ->
            val point = entry.value
            val isInVerticalRange = y < point.y * h + clickRadius && y > point.y * h - clickRadius
            val isInHorizontalRange = x < point.x * w + clickRadius && x > point.x * w - clickRadius
            isInVerticalRange && isInHorizontalRange
        }?.key
    }

    private fun isHolding(
        currentX: Float,
        currentY: Float,
        clickedX: Float,
        clickedY: Float
    ): Boolean {
        val radius = nodeRadius * 5
        val isInVerticalRange = currentY < clickedY + radius && currentY > clickedY - radius
        val isInHorizontalRange = currentX < clickedX + radius && currentX > clickedX - radius
        return isInVerticalRange && isInHorizontalRange
    }

    private fun onPerkClicked(perk: IPerk) {
        skill?.get(perk)?.let { listener?.onNodeClicked(it) }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                currentX = event.x
                currentY = event.y
                return true
            }
            MotionEvent.ACTION_DOWN -> {
                clickedX = event.x
                clickedY = event.y
                currentX = event.x
                currentY = event.y
                touchedPerk = getClickedPerk(event.x, event.y)
                handler.postDelayed({
                    val lastTouchedPerk = touchedPerk
                    if (lastTouchedPerk != null) {
                        if (isHolding(currentX, currentY, clickedX, clickedY)) {
                            skill?.get(lastTouchedPerk)?.let {
                                listener?.onNodeHolding(it)
                            }
                        }
                        touchedPerk = null
                    }
                }, 300)
                return true
            }
            MotionEvent.ACTION_UP -> touchedPerk?.let {
                onPerkClicked(it)
                cancelHold()
                touchedPerk = null
            }
        }
        return super.onTouchEvent(event)
    }

    fun cancelHold() {
        handler.removeCallbacksAndMessages(null)
    }

    companion object {
        fun spToPixels(context: Context, sp: Int): Int {
            val scaledDensity = context.resources.displayMetrics.scaledDensity
            return (sp * scaledDensity).toInt()
        }
    }
}