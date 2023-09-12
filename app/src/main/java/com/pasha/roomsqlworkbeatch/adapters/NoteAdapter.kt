package com.pasha.roomsqlworkbeatch.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pasha.roomsqlworkbeatch.R
import com.pasha.roomsqlworkbeatch.databinding.ItemLayoutBinding
import com.pasha.roomsqlworkbeatch.fragments.detail.DetailFragment
import com.pasha.roomsqlworkbeatch.fragments.start.StartFragment
import com.pasha.roomsqlworkbeatch.model.NoteModel

class NoteAdapter:RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var listNote = emptyList<NoteModel>()//сюда придут данные, когда они появятся

    class NoteViewHolder(view: View):RecyclerView.ViewHolder(view) {
        private val binding = ItemLayoutBinding.bind(view)

        fun setNote(note: NoteModel) = with(binding){
            tvTitle.text = note.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {//надули разметку
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {//заплнили разметку данными поэлементно из списка
        holder.setNote(listNote[position])

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<NoteModel>){//функция для получения листа
        listNote = list
        notifyDataSetChanged()//автоматически говорит адаптеру об изменениях в данных(удаление/добавление)
    }

    //эта функция нужна чтобы привязать нужные действия при нажатии на элемент списка
    override fun onViewAttachedToWindow(holder: NoteViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {//листенер для нажатого элемента
            //в СтартФрагменте вызывается метод, которому передаём данные о заметке, на которую нажали
            StartFragment.clickNote(listNote[holder.adapterPosition])//на фрагмент мередаётся NoteModel элемент
        }
    }

    //при откреплении ViewHolder от окна, его элемент View больше не будет реагировать на нажатия пользователей
    //функция очищает обработчик нажатий, чтобы предотвратить утечку памяти или некорректные действия
    override fun onViewDetachedFromWindow(holder: NoteViewHolder) {
        holder.itemView.setOnClickListener(null)//отключаем листенер после нажатия на заметку и перехода на другой фрагмент
    }

}