package de.thkoeln.fherborn.fearlesschange.v2.ui.dashboard

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import de.thkoeln.fherborn.fearlesschange.R
import de.thkoeln.fherborn.fearlesschange.v2.data.viewmodel.PatternViewModel
import de.thkoeln.fherborn.fearlesschange.v2.helper.extensions.nonNullObserve
import de.thkoeln.fherborn.fearlesschange.v2.ui.AppActivity
import de.thkoeln.fherborn.fearlesschange.v2.ui.patterndetail.PatternDetailDialogFragment
import de.thkoeln.fherborn.fearlesschange.v2.ui.dashboard.DashboardFeatureRegistry.dashboardFeatures
import kotlinx.android.synthetic.main.activity_dashbaord.*
import kotlinx.android.synthetic.main.action_bar.*


class DashboardActivity : AppActivity() {

    private lateinit var viewModel: PatternViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashbaord)
        setSupportActionBar(action_bar as Toolbar)
        inflateFeatureFragments()


        viewModel = ViewModelProviders.of(this).get(PatternViewModel::class.java)
        viewModel.openPatternDetailDialogEvent.nonNullObserve(this) { openCardDetailPopup(it) }
        viewModel.sendSnackBarMessageEvent.nonNullObserve(this) {
            Snackbar.make(activity_wrapper, it.message, it.duration).show()
        }

    }

    private fun inflateFeatureFragments() {

        val features = dashboardFeatures
        val ft = supportFragmentManager.beginTransaction()
        features.forEach {
            ft.add(R.id.dashboard_feature_list, it, null)
        }
        ft.commit()
    }

    private fun openCardDetailPopup(cardId: Long) {
        supportFragmentManager?.let { fm ->
            val cardPopup = PatternDetailDialogFragment.newInstance(cardId = cardId)
            cardPopup.show(fm, null)
        }
    }

}