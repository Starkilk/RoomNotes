package com.pasha.roomsqlworkbeatch.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pasha.roomsqlworkbeatch.dao.NoteDao
import com.pasha.roomsqlworkbeatch.model.NoteModel

          //сущность, под какие поля нужно создать таблицуБД
@Database(entities = [NoteModel::class], version = 1)//нужно менять версию, когда вноошу изменения в этот класс
abstract class NoteDatabase:RoomDatabase() {
    abstract fun getNoteDao():NoteDao//функция для получения заметки из БД

    companion object{
        private var database: NoteDatabase ?= null//переменная для нашей БД

        @Synchronized
        //функция, в которой создаётся БД
        fun getInstance(context: Context):NoteDatabase{

            return if (database == null){//если БД ещё нет
                database = Room.databaseBuilder(context, NoteDatabase::class.java,"db").build()//создаём её
                database as NoteDatabase//и возвращаем

            }else{
                database as NoteDatabase//иначе, если БД уже создана, то мы её и возвращаем
            }
        }
    }
}