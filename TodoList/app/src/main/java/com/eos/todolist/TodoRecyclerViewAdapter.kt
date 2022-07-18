package com.eos.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eos.todolist.databinding.ItemTodoBinding
import com.eos.todolist.db.TodoEntity

class TodoRecyclerViewAdapter(private val todoList: ArrayList<TodoEntity>, private val listener: OnItemLongClickListener)
    : RecyclerView.Adapter<TodoRecyclerViewAdapter.MyViewHolder>() {

        inner class MyViewHolder(binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
            val tv_importance = binding.tvImportance
            val tv_title = binding.tvTitle

            // view binding 에서 기본적으로 제공하는 root 변수는 레이아웃의
            // root layout 을 의미합니다.
            val root = binding.root
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // item_todo.xml 바인딩 객체 생성
        val binding: ItemTodoBinding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val todoData = todoList[position]
        // 중요도에 따라 색상 변경
        when (todoData.importance) {
            1 -> {
               holder.tv_importance.setBackgroundResource(R.color.red)
            }

            2 -> {
                holder.tv_importance.setBackgroundResource(R.color.yellow)
            }

            3 -> {
                holder.tv_importance.setBackgroundResource(R.color.green)
            }
        }
        // 중요도에 따라 중요도 변경
        holder.tv_importance.text = todoData.importance.toString()
        // 할 일 제목 변경
        holder.tv_title.text = todoData.title
        // 할 일이 길게 클릭되었을 때 listener 함수 샐행
        holder.root.setOnLongClickListener {
            listener.onLongClick(position)
            false
        }
    }

    override fun getItemCount(): Int {
        // recyclerView Item 개수 == 할 일 리스트의 크기
        return todoList.size
    }
}