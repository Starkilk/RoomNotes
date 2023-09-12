package com.pasha.roomsqlworkbeatch.fragments.addnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pasha.roomsqlworkbeatch.REPOSITORY
import com.pasha.roomsqlworkbeatch.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteViewModel: ViewModel() {//реализация функций, которые будут вызываться в классе фрагмента

    //функция добавленияя заметки в фрагменте AddNote
    fun insert(noteModel: NoteModel, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO){//карутина, запустили отдельный поток
            REPOSITORY.insertNote(noteModel){//в котором вызывается метод добавления заметки в БД
                onSuccess()
            }
        }



}