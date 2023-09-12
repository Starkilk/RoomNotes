package com.pasha.roomsqlworkbeatch.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pasha.roomsqlworkbeatch.model.NoteModel
//интерфейс для ROOM
//Dao - это объект, который предоставляет абстрактный интерфейс для взаимодействия с базой данных

//сдесь мы объявили функции для Room
//ТО-ЕСТЬ это интерфейс, который работает именно с таблицей (БД), а не с каким-либо массивом.Зависомости дают понять room что нужно делать
//с этеметами таблицы при вызове этих функций в коде(вызов этих функций связан с фукцияями в NoteRepository в классе: Note Realozation)
@Dao
interface NoteDao {
            //если в момент дбавления заметки что-то пойдёт не так, то проигнорит
    @Insert(onConflict = OnConflictStrategy.IGNORE)//указали room, что эта функция для добавления элементов в БД
    suspend fun insert(noteModel: NoteModel)

    @Delete//указали room, что эта функция для удаления элементов из БД
    suspend fun delete(noteModel: NoteModel)

    @Query("SELECT * from note_table")//указали room, что эта функция получения всех данных из БД
    fun getAllNote():LiveData<List<NoteModel>>

    @Update
    suspend fun redact(noteModel: NoteModel)
}