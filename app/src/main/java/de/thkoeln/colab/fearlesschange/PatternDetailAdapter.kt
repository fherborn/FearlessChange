package de.thkoeln.colab.fearlesschange

import android.view.View
import de.thkoeln.colab.fearlesschange.data.persistance.pattern.PatternInfo
import de.thkoeln.colab.fearlesschange.helper.animation.FlipAnimationManager
import de.thkoeln.colab.fearlesschange.ui.LayoutAdapter
import kotlinx.android.synthetic.main.pattern_detail.view.*

class PatternDetailAdapter : LayoutAdapter<PatternInfo>(R.layout.pattern_detail) {
    override fun bind(view: View, value: PatternInfo) {
        with(value.pattern) {
            view.pattern_detail_front_title.text = title
            view.pattern_detail_front_summary.text = summary
            view.pattern_detail_front_image.setImageResource(view.context?.getResourceId(pictureName, "drawable")
                    ?: R.drawable.default_pattern_image)
            view.pattern_detail_back_title.text = title
            view.pattern_detail_back_problem.text = problem
            view.pattern_detail_back_solution.text = solution

            val flipAnimationHelper = FlipAnimationManager(view.pattern_detail_front, view.pattern_detail_back)
            view.pattern_detail_front.setOnClickListener { flipAnimationHelper.flipToBack() }
            view.pattern_detail_back.setOnClickListener { flipAnimationHelper.flipToFront() }
        }
    }
}