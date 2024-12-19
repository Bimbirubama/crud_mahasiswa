package com.bimbirubama.crudmahasiswa.screen

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bimbirubama.crudmahasiswa.databinding.ActivityUpdateNoteBinding
import com.bimbirubama.crudmahasiswa.helper.NoteDatabaseHelper
import com.bimbirubama.crudmahasiswa.model.Note

class UpdateNoteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUpdateNoteBinding
    private lateinit var db : NoteDatabaseHelper

    private var noteId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1){
            finish()
            return
        }
        val note = db.getNoteByID(noteId)
        binding.etEditNama.setText(note.nama)
        binding.etEditNim.setText(note.nim)
        binding.etEditJurusan.setText(note.jurusan)

        binding.btnUpdateNote.setOnClickListener(){
            val newNama = binding.etEditNama.text.toString() //setelah apa yg didapatkan
            val newNim = binding.etEditNim.text.toString()
            val newJurusan = binding.etEditJurusan.text.toString()

            val updateNote = Note(noteId, newNama, newNim, newJurusan)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this,"Update Berhasil", Toast.LENGTH_SHORT).show()
        }
    }
}