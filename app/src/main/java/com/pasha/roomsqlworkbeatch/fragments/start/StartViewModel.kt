package com.pasha.roomsqlworkbeatch.fragments.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pasha.roomsqlworkbeatch.REPOSITORY
import com.pasha.roomsqlworkbeatch.db.NoteDatabase
import com.pasha.roomsqlworkbeatch.model.NoteModel
import com.pasha.roomsqlworkbeatch.repository.NoteRealization


class StartViewModel(application: Application): AndroidViewModel(application) {//реализация функций, которые будут вызываться в классе фрагмента
    val context = application

    //инициализация базы данных
    fun initDatabase(){
        //сдесь вызывается создание БД
        val daoNote = NoteDatabase.getInstance(context).getNoteDao()//передаём в переменную БД(она создана или запущена), чтобы потом указать, что для этой БД нужен интерфейс Дао
        REPOSITORY =  NoteRealization(daoNote)//инициализация репозитория(паказали БД как связаны вызываемые нами методы из кода и методы вызываемые room)
    }

    fun  getAllNotes(): LiveData<List<NoteModel>>{//функция, с помощью которой мы получаем все элементы из БД и записываем их в список
        return REPOSITORY.allNotes//вытаскиваем из БД объекты в Лист
    }

}