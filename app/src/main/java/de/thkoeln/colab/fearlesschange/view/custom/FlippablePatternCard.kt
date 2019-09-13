@file:Suppress("LeakingThis")

package de.thkoeln.colab.fearlesschange.view.custom

import android.content.Context
import android.util.AttributeSet
import de.thkoeln.colab.fearlesschange.R
import de.thkoeln.colab.fearlesschange.core.animation.FlipAnimationManager
import de.thkoeln.colab.fearlesschange.persistance.pattern.Pattern
import kotlinx.android.synthetic.main.flippable_pattern_card.view.*

class FlippablePatternCard @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : PatternCard(context, attrs, defStyleAttr) {

    override fun inflateParentView(context: Context) {
        inflate(context, R.layout.flippable_pattern_card, this)
        applyFlipAnimationHelper()
    }

    private fun applyFlipAnimationHelper() {
        FlipAnimationManager(flippable_pattern_card_front, flippable_pattern_card_back).apply {
            flippable_pattern_card_front.setOnClickListener { flipToBack() }
            flippable_pattern_card_back.setOnClickListener { flipToFront() }
        }
    }

    override fun applyAttributes(pattern: Pattern?) {
        flippable_pattern_card_back.pattern = pattern
        flippable_pattern_card_front.pattern = pattern
    }

}