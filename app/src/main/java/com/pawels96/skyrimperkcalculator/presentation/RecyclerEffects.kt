package com.pawels96.skyrimperkcalculator.presentation

import android.os.Build
import android.view.View
import android.widget.EdgeEffect
import androidx.core.content.ContextCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.recyclerview.widget.RecyclerView
import com.pawels96.skyrimperkcalculator.R

/** The magnitude of translation distance while the list is over-scrolled. */
const val OVERSCROLL_TRANSLATION_MAGNITUDE = 0.1f

/** The magnitude of translation distance when the list reaches the edge on fling. */
const val FLING_TRANSLATION_MAGNITUDE = 0.1f

fun RecyclerView.configureEffects(
    overscrollMagnitude: Float = OVERSCROLL_TRANSLATION_MAGNITUDE,
    flingMagnitude: Float = FLING_TRANSLATION_MAGNITUDE,
    onTouch: (isTouching: Boolean) -> Unit
) {
    edgeEffectFactory = object : RecyclerView.EdgeEffectFactory() {
        override fun createEdgeEffect(recyclerView: RecyclerView, direction: Int): EdgeEffect {
            return object : EdgeEffect(recyclerView.context) {

                override fun onPull(deltaDistance: Float) {
                    super.onPull(deltaDistance)
                    onTouch(true)
                    handlePull(deltaDistance)
                }

                override fun onPull(deltaDistance: Float, displacement: Float) {
                    super.onPull(deltaDistance, displacement)
                    onTouch(true)
                    handlePull(deltaDistance)
                }

                private fun handlePull(deltaDistance: Float) {
                    val sign = if (direction == DIRECTION_BOTTOM) -1 else 1
                    val translationYDelta =
                        sign * recyclerView.width * deltaDistance * overscrollMagnitude
                    recyclerView.forEachVisibleHolder { holder: AnimatedHolder ->
                        holder.rotation.cancel()
                        holder.translationY.cancel()
                        holder.view.translationY += translationYDelta
                    }
                }

                override fun onRelease() {
                    super.onRelease()

                    recyclerView.forEachVisibleHolder { holder: AnimatedHolder ->
                        holder.rotation.start()
                        holder.translationY.start()
                    }

                    onTouch(false)
                }

                override fun onAbsorb(velocity: Int) {
                    super.onAbsorb(velocity)

                    val sign = if (direction == DIRECTION_BOTTOM) -1 else 1
                    val translationVelocity = sign * velocity * flingMagnitude
                    recyclerView.forEachVisibleHolder { holder: AnimatedHolder ->
                        holder.translationY
                            .setStartVelocity(translationVelocity)
                            .start()
                    }
                }
            }.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    color = ContextCompat.getColor(context, R.color.colorOverscroll)
                }
            }
        }
    }
}

inline fun <reified T : RecyclerView.ViewHolder> RecyclerView.forEachVisibleHolder(
    action: (T) -> Unit
) {
    for (i in 0 until childCount) {
        action(getChildViewHolder(getChildAt(i)) as T)
    }
}

interface AnimatedHolder {

    var currentVelocity: Float

    val rotation: SpringAnimation

    val translationY: SpringAnimation

    val view: View
}

class Bounceable(override val view: View) : AnimatedHolder {

    override var currentVelocity = 0f

    override val rotation: SpringAnimation = SpringAnimation(view, SpringAnimation.ROTATION)
        .setSpring(
            SpringForce()
                .setFinalPosition(0f)
                .setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
                .setStiffness(SpringForce.STIFFNESS_LOW)
        )
        .addUpdateListener { _, _, velocity ->
            currentVelocity = velocity
        }

    override val translationY: SpringAnimation =
        SpringAnimation(view, SpringAnimation.TRANSLATION_Y)
            .setSpring(
                SpringForce()
                    .setFinalPosition(0f)
                    .setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY)
                    .setStiffness(SpringForce.STIFFNESS_LOW)
            )
}
