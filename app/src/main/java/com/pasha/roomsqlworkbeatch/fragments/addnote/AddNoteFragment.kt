package com.pasha.roomsqlworkbeatch.fragments.addnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.pasha.roomsqlworkbeatch.APP
import com.pasha.roomsqlworkbeatch.R
import com.pasha.roomsqlworkbeatch.databinding.FragmentAddNoteBinding
import com.pasha.roomsqlworkbeatch.model.NoteModel


class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun isFieldEmty(): Boolean{//функция проверяет поля editText на заполненность

        binding.apply {//могу напрямую обращаться ко всем элементам, находящимся в binding

            if(etAddTitle.text.isNullOrEmpty()){//если пусто, то:
                etAddTitle.error = "Поле должно быть заполненно"
            }
            if(etAddDescript.text.isNullOrEmpty()){
                etAddDescript.error = "Поле должно быть заполненно"
            }

            return etAddTitle.text.isNullOrEmpty() || etAddDescript.text.isNullOrEmpty()
        }
    }


    private fun init() {
        //получили доступ к методам прописанным а классе AddNoteViewModel
        val viewModel = ViewModelProvider(this)[AddNoteViewModel::class.java]


        binding.bAddNote.setOnClickListener {
            //взяли данные из полей ввода
            val fieldTitle = binding.etAddTitle.text.toString()
            val fieldDescription = binding.etAddDescript.text.toString()
            if(!isFieldEmty()) {//если поля заполнены, то создаём заметку
                viewModel.insert(//в отдельном потоке вызывается метод insert
                    NoteModel(//в него передаётся модель, которая заполняет поля введёнными данными
                        title = fieldTitle,
                        description = fieldDescription
                    )
                ) {}//эта заметка сразу появляется в БД
                    //при переходе на СтартФрагметн заметка уже отрисуется там
                APP.navController.navigate(R.id.action_addNoteFragment_to_startFragment)
            }
        }

        //кнопка назад
        binding.bBack.setOnClickListener{
            APP.navController.navigate(R.id.action_addNoteFragment_to_startFragment)
        }
        binding
    }

}