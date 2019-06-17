package de.thkoeln.colab.fearlesschange.ui.notes

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import de.thkoeln.colab.fearlesschange.R
import kotlinx.android.synthetic.main.create_note_dialog.*

class CreateNoteDialog(context: Context) : Dialog(context) {
    var onConfirmListener: (String, String) -> Unit = { _, _ -> }
    private var dismissOnConfirm = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.create_note_dialog)
        create_note_confirm.setOnClickListener {
            if (dismissOnConfirm) dismiss()
            onConfirmListener(create_note_note_title.text.toString(), create_note_note_text.text.toString())
        }
    }
}