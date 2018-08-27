package de.thkoeln.fherborn.fearlesschange.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import de.thkoeln.fherborn.fearlesschange.R
import de.thkoeln.fherborn.fearlesschange.v2.ui.search.SearchKeywordRecyclerAdapter
import de.thkoeln.fherborn.fearlesschange.v2.data.persistance.keyword.KeywordRepository
import de.thkoeln.fherborn.fearlesschange.v2.data.persistance.keyword.Keyword
import de.thkoeln.fherborn.fearlesschange.v2.data.viewmodel.SearchViewModel
import de.thkoeln.fherborn.fearlesschange.v2.helper.extensions.nonNullObserve
import de.thkoeln.fherborn.fearlesschange.v2.ui.AppActivity
import de.thkoeln.fherborn.fearlesschange.v2.ui.adapter.PatternRecyclerGridAdapter
import de.thkoeln.fherborn.fearlesschange.v2.ui.search.SearchKeywordAutocompleteAdapter
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppActivity() {
    private val selectedKeywordsAdapter = SearchKeywordRecyclerAdapter()
    private lateinit var searchKeywordsAdapter: SearchKeywordAutocompleteAdapter
    private val resultsAdapter = PatternRecyclerGridAdapter()
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setupActionBar()
        searchKeywordsAdapter = SearchKeywordAutocompleteAdapter(this)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        viewModel.getNotSelectedKeywords().nonNullObserve(this){
            searchKeywordsAdapter.updateKeywords(it)
        }
        searchKeywordsAdapter.keywordClickedListener = {
            viewModel.addKeyword(it)
        }
        search_keyword.setAdapter(searchKeywordsAdapter)
        viewModel.selectedKeywords.nonNullObserve(this) {
            selectedKeywordsAdapter.updateKeywords(it)
        }
        selected_keywords.adapter = selectedKeywordsAdapter
        search_results.adapter = resultsAdapter

        search_button.setOnClickListener {
            viewModel.onSearchClicked()
        }

        viewModel.getSearchResult().nonNullObserve(this) {patterns ->
            resultsAdapter.updatePatterns(patterns)
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?) = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                when (swipeDir) {
                    ItemTouchHelper.LEFT -> viewModel.removeKeyword(selectedKeywordsAdapter.keywords[viewHolder.adapterPosition])
                }
            }
        })
        itemTouchHelper.attachToRecyclerView(selected_keywords)
    }

    /**
     * Set up the [android.app.ActionBar], if the API is available.
     */
    private fun setupActionBar() {
        setSupportActionBar(action_bar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
