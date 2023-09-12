package com.pasha.roomsqlworkbeatch.repository

import androidx.lifecycle.LiveData
import com.pasha.roomsqlworkbeatch.dao.NoteDao
import com.pasha.roomsqlworkbeatch.model.NoteModel

//сдесь будет OБЪЕДИНЕНИЕ дух интерфейсов: NoteRepository(Методы работующие с данными в коде) и NoteDao(Методы работующие с данными в таблицеБД)

class NoteRealization(private val noteDao: NoteDao ):NoteRepository {
    //LiveData следит за элементами в листе, которые приходят из БД, чтобы перерисовывался Recycler с заметками
    override val allNotes: LiveData<List<NoteModel>>//код возвратит LiveData объект, содержащий список всех заметок из базы данных
        get() = noteDao.getAllNote()

    override suspend fun insertNote(noteModel: NoteModel, onSuccess: () -> Unit) {
        noteDao.insert(noteModel)//связываем методы с методами в noteDao
        onSuccess()//это функция, которая выполняется при успешном выполнении определенного действия или операции
    }

    override suspend fun deleteNote(noteModel: NoteModel, onSuccess: () -> Unit) {//при запуске функции в коде->
        noteDao.delete(noteModel)//у элемента Dao(то-есть, у элемента таблицы) вызывается метод delete/insert.По данным из Модели room понимает что удалить/добавить
        onSuccess()
    }

    override suspend fun redactNote(noteModel: NoteModel, onSuccess: () -> Unit) {//редактирование заметки
        noteDao.redact(noteModel)
        onSuccess()
    }

}