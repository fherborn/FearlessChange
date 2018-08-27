package de.thkoeln.fherborn.fearlesschange.v2.ui.settings

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import de.thkoeln.fherborn.fearlesschange.R
import de.thkoeln.fherborn.fearlesschange.v2.data.viewmodel.SettingsViewModel
import de.thkoeln.fherborn.fearlesschange.v2.helper.extensions.nonNullObserve
import de.thkoeln.fherborn.fearlesschange.v2.ui.AppActivity
import de.thkoeln.fherborn.fearlesschange.v2.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.action_bar.*


class SettingsActivity : AppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(action_bar as Toolbar)
        val viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)

        viewModel.sendSnackBarMessageEvent.nonNullObserve(this) {
            Snackbar.make(activity_wrapper, it.message, it.duration).show()
        }

        viewModel.requestConfirmationEvent.nonNullObserve(this) {
            AlertDialog.Builder(this)
                    .setTitle(it.title)
                    .setMessage(it.text)
                    .setPositiveButton(it.positiveButtonText) { _, _ -> viewModel.onConfirmRequest(it)}
                    .setNegativeButton(it.negativeButtonText) { _, _ -> viewModel.onCancelRequest(it)}
                    .create().show()
        }

        reset_most_clicked_item.setOnClickListener{viewModel.resetMostClickedPatternClicked()}
        reset_favorites_item.setOnClickListener{viewModel.resetFavoritesClicked()}
        reset_to_factory_item.setOnClickListener{viewModel.resetToFactorySettingsClicked()}

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_search -> {
            startActivity(Intent(this, SearchActivity::class.java))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}