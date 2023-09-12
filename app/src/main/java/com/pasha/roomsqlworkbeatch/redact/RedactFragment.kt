package com.pasha.roomsqlworkbeatch.redact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.pasha.roomsqlworkbeatch.APP
import com.pasha.roomsqlworkbeatch.R
import com.pasha.roomsqlworkbeatch.databinding.FragmentRedactBinding
import com.pasha.roomsqlworkbeatch.model.NoteModel


class RedactFragment : Fragment() {
   private lateinit var binding: FragmentRedactBinding
   lateinit var currentNoteRedact: NoteModel
    private lateinit var viewModel : RedactViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRedactBinding.inflate(inflater, container, false)

        currentNoteRedact = arguments?.getSerializable("noteRedact") as NoteModel//присваиваем данные об открытой заметки

        binding.etRedactTitle.setText(currentNoteRedact.title)//вставляем в эдит текст данные, которые получили
        binding.etRedactDescript.setText(currentNoteRedact.description)//вставляем в эдит текст данные, которые получили
        
        viewModel = ViewModelProvider(this).get(RedactViewModel::class.java)//доступ к ViewModel

        binding.bSaveUpdate.setOnClickListener {//кнопка (Сохранить изменения)
            init()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bBackDet.setOnClickListener {//кноопка(Назад)
            APP.navController.navigate(R.id.action_redactFragment_to_startFragment)
        }
    }
    //проверка заполненности полей
    fun isFieldEmty(): Boolean{//функция проверяет поля editText на заполненность

        binding.apply {//могу напрямую обращаться ко всем элементам, находящимся в binding

            if(etRedactTitle.text.isNullOrEmpty()){//если пусто, то:
                etRedactTitle.error = "Поле должно быть заполненно"
            }
            if(etRedactDescript.text.isNullOrEmpty()){
                etRedactDescript.error = "Поле должно быть заполненно"
            }

            return etRedactTitle.text.isNullOrEmpty() || etRedactDescript.text.isNullOrEmpty()
        }
    }
    //функция сохранения изменений
    private fun init() {
        //присваиваем переменным новые введённые данны
        val title = binding.etRedactTitle.text.toString()
        val description = binding.etRedactDescript.text.toString()
        //если поля заполнены, то вызываем метод редактирования БД и открываем StartFragment
        if(!isFieldEmty()){
            viewModel.redact(NoteModel(currentNoteRedact.id,title = title, description = description)){}
            APP.navController.navigate(R.id.action_redactFragment_to_startFragment)
        }

    }


}