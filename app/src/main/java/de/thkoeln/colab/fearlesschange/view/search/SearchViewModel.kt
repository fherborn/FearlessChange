package de.thkoeln.colab.fearlesschange.view.search

import android.app.Application
import de.thkoeln.colab.fearlesschange.core.asLiveData
import de.thkoeln.colab.fearlesschange.core.events.DynamicLiveData
import de.thkoeln.colab.fearlesschange.core.map
import de.thkoeln.colab.fearlesschange.core.pattern.BasicPatternViewModel
import de.thkoeln.colab.fearlesschange.persistance.keyword.Keyword
import de.thkoeln.colab.fearlesschange.persistance.keyword.KeywordRepository
import de.thkoeln.colab.fearlesschange.persistance.pattern.PatternInfo

class SearchViewModel(application: Application) : BasicPatternViewModel(application) {

    private val keywordRepository = KeywordRepository(application)

    private val patternDynamic = DynamicLiveData<List<PatternInfo>>()
    val pattern = patternDynamic.asLiveData()

    private val unselectedKeywordsDynamic = DynamicLiveData<List<Keyword>>()
    val unselectedKeywords = unselectedKeywordsDynamic.asLiveData()

    private val selectedKeywordsDynamic = DynamicLiveData<List<Keyword>>()
    val selectedKeywords = selectedKeywordsDynamic.asLiveData()

    private val keywords = mutableListOf<Keyword>()

    val keywordAddedListener: (Keyword) -> Unit = { keyword ->
        keywords.add(keyword)
        refreshKeywords()
        refreshPattern()
    }

    val onKeywordDeleted: (keyword: Keyword) -> Unit = { keyword ->
        keywords.remove(keyword)
        refreshKeywords()
        refreshPattern()
    }


    val onKeywordRestored: (keyword: Keyword) -> Unit = { keyword ->
        keywordAddedListener(keyword)
    }


    val patternCardClicked: (PatternInfo?) -> Unit = { patternInfo ->
        patternInfo?.let {
            notifyPatternClicked(patternInfo)
            notifyAction(SearchFragmentDirections.actionNavSearchToPatternDetailSwipeFragment(longArrayOf(it.pattern.id), it.pattern.id))
        }
    }

    init {
        refreshKeywords()
        refreshPattern()
    }


    private fun refreshPattern() {
        patternDynamic.newSource(
                if (keywords.isEmpty()) patternRepository.getAllInfo()
                else patternRepository.getByKeywordIds(keywords.map { it.id }))
    }

    private fun refreshKeywords() {
        selectedKeywordsDynamic.newSource(getFilteredKeywords { this.keywords.contains(it) })
        unselectedKeywordsDynamic.newSource(getFilteredKeywords { !this.keywords.contains(it) })
    }

    private fun getFilteredKeywords(filter: (Keyword) -> Boolean) = keywordRepository.getAllKeywords().map { keywords -> keywords.filter(filter) }

}