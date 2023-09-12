package com.pasha.roomsqlworkbeatch.fragments.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pasha.roomsqlworkbeatch.REPOSITORY
import com.pasha.roomsqlworkbeatch.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {//реализация функций, которые будут вызываться в классе фрагмента

    fun delete(noteModel: NoteModel, onSuccess:() -> Unit) = //реализация удаления заметки
        viewModelScope.launch(Dispatchers.IO){//КАРУТИНА, запустили отдельный поток для удаления заметки
            REPOSITORY.deleteNote(noteModel){
                onSuccess()//успешно завершили
            }
        }
}