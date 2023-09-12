package com.pasha.roomsqlworkbeatch.fragments.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.pasha.roomsqlworkbeatch.APP
import com.pasha.roomsqlworkbeatch.MainActivity
import com.pasha.roomsqlworkbeatch.R
import com.pasha.roomsqlworkbeatch.adapters.NoteAdapter
import com.pasha.roomsqlworkbeatch.databinding.FragmentStartBinding
import com.pasha.roomsqlworkbeatch.model.NoteModel
import com.pasha.roomsqlworkbeatch.redact.RedactFragment


class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter:NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    //эта функция вызывается каждый раз, когда открывается данный фрагмет и заного отрисовавыет список заметок(Убирает удалённые/Добавляет добавленные)
    private fun init() {
        val viewModel = ViewModelProvider(this).get(StartViewModel::class.java)//доступ к StaretviewModel
        viewModel.initDatabase()//инициализируем БД
        recyclerView = binding.rvNotes//инициализировали RecyclerView
        myAdapter = NoteAdapter()//инициализировали адаптер
        recyclerView.adapter = myAdapter//присваиваем rcView наш адаптер

        //адаптер отрисовывает все заметки
        viewModel.getAllNotes().observe(viewLifecycleOwner) { listNote ->//считали все данные из таблицы в переменную listNote
            //у адаптера вызываем метод, который присваивает полученый список листу в адаптере
            myAdapter.setList(listNote.asReversed()) //ревёрс списка, чтобы новые заметки появлялись сверху
        }

        //кнопка добавления заметки
        binding.bAddNote.setOnClickListener{
            APP.navController.navigate(R.id.action_startFragment_to_addNoteFragment)//при нажатии переносит нас на врашмент с заполнением данных
        }
    }

    companion object{
        fun clickNote(noteModel: NoteModel){
            val bundle = Bundle()//позволяет передавать целый объект, а не поля отдельно
            bundle.putSerializable("note", noteModel)//кладём в бандел нашу модель

            APP.navController.navigate(R.id.action_startFragment_to_detailFragment, bundle)//передаём объект на детэйл фрагмент

        }

    }


}