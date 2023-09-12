package com.pasha.roomsqlworkbeatch.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.pasha.roomsqlworkbeatch.APP
import com.pasha.roomsqlworkbeatch.R
import com.pasha.roomsqlworkbeatch.databinding.FragmentDetailBinding
import com.pasha.roomsqlworkbeatch.model.NoteModel
import com.pasha.roomsqlworkbeatch.redact.RedactFragment


class DetailFragment : Fragment() {
private lateinit var binding: FragmentDetailBinding
lateinit var currentNote: NoteModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        //получили объект из bundle
        currentNote = arguments?.getSerializable("note") as NoteModel//присвоение данных их нажатой заметки(из StartFragment)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }



    private fun init() {//реализация фрагмента просмотра заметки
        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding.tvTitleDetail.text = currentNote.title//заполнеие View
        binding.tvDescription.text = currentNote.description//заполнеие View

        binding.bDelete.setOnClickListener {//удалить заметку
            viewModel.delete(currentNote){}//вызов метода(в отдельном потоке удаляется элемент)
            APP.navController.navigate(R.id.action_detailFragment_to_startFragment)//возврат на СтартФрагмент

        }

        binding.bBack.setOnClickListener {//вернуться на StartFragment
            APP.navController.navigate(R.id.action_detailFragment_to_startFragment)
        }

        binding.bRedact.setOnClickListener {
            val bundleRedact = Bundle()//позволяет передавать целый объект, а не поля отдельно
            bundleRedact.putSerializable("noteRedact", currentNote)//кладём в бандел нашу модель
            //открываем RedactFragment и отправляем на него Bundle
            APP.navController.navigate(R.id.action_detailFragment_to_redactFragment,bundleRedact)
        }
    }




}