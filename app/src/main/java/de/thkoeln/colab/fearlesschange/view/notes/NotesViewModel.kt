package de.thkoeln.colab.fearlesschange.view.notes

import android.app.Application
import de.thkoeln.colab.fearlesschange.core.pattern.BasicPatternViewModel
import de.thkoeln.colab.fearlesschange.persistance.note.Note
import de.thkoeln.colab.fearlesschange.persistance.note.NoteRepository
import de.thkoeln.colab.fearlesschange.persistance.noteLabelJoin.NoteLabelJoinRepository
import de.thkoeln.colab.fearlesschange.persistance.pattern.PatternInfo
import de.thkoeln.colab.fearlesschange.persistance.todos.Todo
import de.thkoeln.colab.fearlesschange.persistance.todos.TodoRepository
import kotlinx.coroutines.runBlocking


//data class PatternNoteData(val note: Note, val labels: List<Label>, val todos: List<Todo>)

class NotesViewModel(application: Application) : BasicPatternViewModel(application) {

    private val noteRepo = NoteRepository(application)
    private val todoRepo = TodoRepository(application)
    private val noteLabelJoinRepo = NoteLabelJoinRepository(application)


    val updateTodo: (todo: Todo, newState: Boolean) -> Unit = { todo, state ->
        runBlocking {
            todoRepo.update(todo.copy(state = state))
        }
    }
    val patternClicked: (pattern: PatternInfo) -> Unit = {
        notifyPatternClicked(it)
        notifyAction(NotesFragmentDirections.actionNavNotesToPatternDetailSwipeFragment(longArrayOf(it.pattern.id), it.pattern.id))

    }

    fun getNoteData(callback: (List<NoteData>) -> Unit) = runBlocking {
        val notes = noteRepo.getAll()
        val result = notes.map {
            val patternNoteData = PatternNoteData(it, noteLabelJoinRepo.getByNote(it.id), todoRepo.getByNote(it.id))
            val pattern = patternRepository.get(it.patternId)
            NoteData(pattern, patternNoteData)
        }
        callback(result)
    }

    fun deleteNote(note: Note) {
        runBlocking {
            noteRepo.delete(note)
        }
    }


    fun addNote(note: Note) {
        runBlocking {
            noteRepo.insert(note)
        }
    }


}