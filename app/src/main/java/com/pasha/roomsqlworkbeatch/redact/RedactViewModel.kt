package com.pasha.roomsqlworkbeatch.redact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pasha.roomsqlworkbeatch.REPOSITORY
import com.pasha.roomsqlworkbeatch.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RedactViewModel: ViewModel() {
    fun redact(noteModel: NoteModel, onSuccess:() -> Unit) = //реализация удаления заметки
        viewModelScope.launch(Dispatchers.IO){//КАРУТИНА, запустили отдельный поток для редактирования заметки
            REPOSITORY.redactNote(noteModel){
                onSuccess()//успешно завершили
            }
        }
}