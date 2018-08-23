package de.thkoeln.fherborn.fearlesschange.v2.ui.adapter

import de.thkoeln.fherborn.fearlesschange.v2.data.persistance.pattern.Pattern
import de.thkoeln.fherborn.fearlesschange.v2.ui.customs.card.PatternCardBack

/**
 * Created by florianherborn on 22.08.18.
 */
class PatternCardBackAdapter: SingleViewAdapter<Pattern, PatternCardBack>() {

    var onFavoriteClickedListener: ((Pattern?) -> Unit)? = null

    override fun onDataChange(view: PatternCardBack, data: Pattern?) {
        view.title = data?.title
        view.problem = data?.problem
        view.solution = data?.solution
        view.favorite = data?.favorite
        view.onFavoriteClickedListener = { onFavoriteClickedListener?.invoke(data) }
    }

}