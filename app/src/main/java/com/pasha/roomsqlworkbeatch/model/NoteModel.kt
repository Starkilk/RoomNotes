package com.pasha.roomsqlworkbeatch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//анатация, чтобы room понимал, что из этого класса мы будем делать таблицу
@Entity(tableName = "note_table")

//класс описывающий заметку
class NoteModel(
    @PrimaryKey(autoGenerate = true)//автоматическая генерация id, для поля id
    var id:Int = 0,

    @ColumnInfo//анатация делает так, что колонки в нашей таблице будут называться так, как и поле класса
    var title:String = "",

    @ColumnInfo//анатация делает так, что колонки в нашей таблице будут называться так, как и поле класса
    var description: String = ""

//также, этими анатациями дали поонять room, что у нас будет ТРИ колонки
//можно не указывать @ColumnInfo, он сам найдёт эти поля, но для наглядности можно)

) : Serializable//нужно чтобы появылась возможность передавать объекты между фрагментами и не только