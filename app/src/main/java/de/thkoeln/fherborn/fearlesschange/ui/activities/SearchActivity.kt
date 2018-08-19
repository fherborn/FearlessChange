package de.thkoeln.fherborn.fearlesschange.ui.activities

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import de.thkoeln.fherborn.fearlesschange.R
import de.thkoeln.fherborn.fearlesschange.persistance.models.Keyword
import de.thkoeln.fherborn.fearlesschange.persistance.repositories.KeywordRepository
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.layout_default_app_bar.*

class SearchActivity : AppCompatActivity() {
    private lateinit var keywordRepository: KeywordRepository
    private lateinit var textView: AutoCompleteTextView
    private lateinit var selectedKeywords: MutableList<Keyword>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setupActionBar()
        selectedKeywords = mutableListOf()

        keywordRepository = KeywordRepository(application)

        this.textView = findViewById(R.id.search_keyword)

        keywordRepository.getAllKeywords().observe(this, Observer { keywords ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, keywords)
            textView.setAdapter(adapter)
        })

        add_keyword.setOnClickListener{
            addKeyword()
        }

    }

    /**
     * Set up the [android.app.ActionBar], if the API is available.
     */
    private fun setupActionBar() {
        setSupportActionBar(action_bar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun addKeyword() {
        val keyword: String = this.textView.text.toString()
        keywordRepository.getKeywordByKeyword(keyword).observe(this, Observer { selectedKeyword ->
            selectedKeyword?.let {
                // TODO: Prüfen ob Element schon vorhanden und entsprechende Fälle behandeln
                this.selectedKeywords.add(selectedKeyword)
            } ?: run {
                // TODO: Fehlermeldung ausgeben, dass das Keyword nicht gefunden wurde
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_activity_actions, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_refresh -> {

            true
        }
        R.id.action_settings -> {
            startActivity(Intent(this, SettingsActivity::class.java))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
