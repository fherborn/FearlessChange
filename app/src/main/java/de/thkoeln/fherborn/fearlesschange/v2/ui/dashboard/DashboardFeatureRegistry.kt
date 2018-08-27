package de.thkoeln.fherborn.fearlesschange.v2.ui.dashboard

import android.support.v4.app.Fragment
import de.thkoeln.fherborn.fearlesschange.v2.ui.dashboard.features.PatternOfTheDayFeature
import de.thkoeln.fherborn.fearlesschange.v2.ui.dashboard.features.MostClickedCardFeature
import de.thkoeln.fherborn.fearlesschange.v2.ui.dashboard.features.NavigationFeature
import de.thkoeln.fherborn.fearlesschange.v2.ui.dashboard.features.RandomPatternFeature

/**
 * Created by florianherborn on 22.08.18.
 */
object DashboardFeatureRegistry {
    val dashboardFeatures: List<Fragment> by lazy {
        listOf(
                NavigationFeature(),
                PatternOfTheDayFeature(),
                RandomPatternFeature(),
                MostClickedCardFeature()
        )
    }
}