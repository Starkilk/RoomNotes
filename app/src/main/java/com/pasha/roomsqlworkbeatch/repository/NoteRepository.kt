package com.pasha.roomsqlworkbeatch.repository

import androidx.lifecycle.LiveData
import com.pasha.roomsqlworkbeatch.model.NoteModel

//интерфейс для НАС
//интерфейс, который будет содержать функции
interface NoteRepository {

    val allNotes: LiveData<List<NoteModel>>//предоставляет наблюдаемую модель данных, которая автоматически обновляется, когда данные изменяются

    //функция для добавления заметки.Unit тоже что и Void
    suspend fun insertNote(noteModel: NoteModel,onSuccess:() -> Unit)// suspend пишем для того, чтобы функцию можно было использовать в карутинах

    //функция для удаленияя заметки
    suspend fun deleteNote(noteModel: NoteModel,onSuccess:() -> Unit)
    //функцияяя для редактирования заметки
    suspend fun redactNote(noteModel: NoteModel, onSuccess: () -> Unit)
}