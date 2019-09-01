package de.thkoeln.colab.fearlesschange.view.editor

import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.widget.EditText
import kotlin.math.max
import kotlin.math.min
import kotlin.reflect.KClass

class SpanManager private constructor(private val editor: EditText) : TextWatcher {

    private val selectionStart
        get() = min(editor.selectionStart, editor.selectionEnd)

    private val selectionEnd
        get() = max(editor.selectionStart, editor.selectionEnd)

    private val hasNoFocus
        get() = editor.selectionStart == -1

    private val hasNoSelection
        get() = editor.selectionStart == editor.selectionEnd

    private val text
        get() = editor.text

    private val spans = mutableListOf<SpanHolder>()
    private val clicked = mutableListOf<Span>()

    fun <T : Span> setSpan(span: T) {
        when {
            hasNoFocus -> { /* no focus */
            }
            hasNoSelection && span is InlineSpan -> addInlineSpan(span)
            hasNoSelection && span is InPlaceSpan -> addInPlaceSpan(selectionStart, span)
            span is InlineSpan -> setSpan(selectionStart, selectionEnd, span)
            span is InPlaceSpan -> setSpan(selectionStart, selectionEnd, span) // Todo delete text before set span
        }
    }

    private fun addInlineSpan(span: InlineSpan) {
        if (!clicked.containsType(spans)) {
            clicked.add(span)
        } else {
            clicked.remove(span)
        }
    }

    private fun addInPlaceSpan(index: Int, span: InPlaceSpan) {
        editor.text = text.insert(index, PLACEHOLDER)
        setSpan(index, index + PLACEHOLDER.length, span)
        editor.setSelection(index + PLACEHOLDER.length)
    }

    private fun setSpan(start: Int, end: Int, span: Span) {
        val holder = SpanHolder(start, end, span)
        val existing = spans.getLike(holder)
        if (existing.isEmpty()) attachSpan(holder)
        else updateSpan(existing, holder)
    }

    private fun updateSpan(existing: List<SpanHolder>, newSpan: SpanHolder) {
        val first = existing.minBy { it.start } ?: newSpan
        val last = existing.maxBy { it.end } ?: newSpan


        if (first.start == newSpan.end) {
            first.start = newSpan.start
            updateText()
            return
        }

        if (last.end == newSpan.start) {
            last.end = newSpan.end
            updateText()
            return
        }

        removeSpans(existing)


        if (first.end == newSpan.start && last.start == newSpan.end && last.span != first.span) {
            attachSpan(newSpan.copy(start = first.start, end = last.end, span = newSpan.span.copy()))
            updateText()
            return
        }

        if (first.start < newSpan.start) {
            attachSpan(newSpan.copy(start = first.start, end = newSpan.start, span = newSpan.span.copy()))
        }

        if (last.end > newSpan.end) {
            attachSpan(newSpan.copy(start = newSpan.end, end = last.end, span = newSpan.span.copy()))
        }
    }


    private fun attachSpan(newSpan: SpanHolder) {
        spans.add(newSpan)
        updateText()
    }

    private fun removeSpans(spans: List<SpanHolder>) {
        this.spans.removeAll(spans)
        updateText()
    }

    override fun afterTextChanged(spannable: Editable) {
    }

    override fun beforeTextChanged(t: CharSequence?, changeStart: Int, before: Int, count: Int) {
    }

    override fun onTextChanged(t: CharSequence?, start: Int, before: Int, count: Int) {
        val added = addedCharacters(count, before)
        val changeStart = start + before
        val changeEnd = changeStart + added
        updateExisting(changeStart, added)
        addNewStyles(changeStart, changeEnd)
    }

    private fun addNewStyles(changeStart: Int, changeEnd: Int) {
        clicked.forEach { setSpan(changeStart, changeEnd, it) }
        clicked.clear()
    }

    private fun updateExisting(start: Int, added: Int) {
        // start after -> start & end + added
        // end after -> end + added
        val toRemove = spans.mapNotNull {


            if (it.span is InPlaceSpan) {
                if (it.start >= start) {
                    it.start += added
                    it.end += added
                }
                if (added < 0 && start + added < it.end && start >= it.end) {
                    return@mapNotNull it
                }
                return@mapNotNull null
            }

            if (it.start >= start) {
                it.start += added
            }

            if (it.end >= start) {
                it.end += added
            }

            if (it.end <= it.start) {
                return@mapNotNull it
            }
            null
        }
        spans.removeAll(toRemove)

        updateText()
    }

    private fun updateText() {
        val invalidSpans = spans.filter { it.start >= text.length }
        spans.removeAll(invalidSpans)
        text.getSpans(0, text.length, Span::class.java)
                .filter { tSpan -> spans.find { it.span == tSpan } == null }
                .forEach { text.removeSpan(it) }
        spans.forEach { text.setSpan(it.span, it.start, it.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }
    }

    private fun addedCharacters(count: Int, before: Int) = count - before


    companion object {
        private const val PLACEHOLDER = "\u2610"
        fun forEditor(editor: EditText) = SpanManager(editor).also {
            editor.addTextChangedListener(it)
        }
    }

}

private fun List<Any>?.containsType(obj: Any) = this != null && find { it::class == obj::class } != null

private data class SpanHolder(var start: Int, var end: Int, val span: Span)

private fun List<SpanHolder>.getSpans() = map { it.span }

private fun List<SpanHolder>.filterInRange(start: Int, end: Int) = filter {
    it.start in start..end
            || it.end in start..end
            || start in it.start..it.end
            || end in it.start..it.end
}


private fun <T : Span> List<SpanHolder>.filterType(spanClass: KClass<T>) = filter { it.span::class == spanClass }
private fun <T : Span> List<SpanHolder>.get(start: Int, end: Int, spanClass: KClass<T>) = filterType(spanClass).filterInRange(start, end)
private fun List<SpanHolder>.getLike(start: Int, end: Int, span: Span) = get(start, end, span::class)
private fun List<SpanHolder>.getLike(holder: SpanHolder) = getLike(holder.start, holder.end, holder.span)