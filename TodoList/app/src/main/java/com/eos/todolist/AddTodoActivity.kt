package com.eos.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.eos.todolist.databinding.ActivityAddTodoBinding
import com.eos.todolist.databinding.ActivityMainBinding
import com.eos.todolist.db.AppDatabase
import com.eos.todolist.db.ToDoDao
import com.eos.todolist.db.TodoEntity

class AddTodoActivity : AppCompatActivity() {

    lateinit var binding : ActivityAddTodoBinding
    lateinit var db : AppDatabase
    lateinit var toDoDao: ToDoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        toDoDao = db.getTodoDao()

        binding.btnCompletion.setOnClickListener {
            insertTodo()
        }
    }

    private fun insertTodo() {

        val todoTitle = binding.editTitle.text.toString()
        var todoImportance = binding.radioGroup.checkedRadioButtonId

        when (todoImportance) {
            R.id.btn_high -> {
                todoImportance = 1
            }
            R.id.btn_middle -> {
                todoImportance = 2
            }
            R.id.btn_low -> {
                todoImportance = 3
            }

            else -> {
                todoImportance = -1
            }
        }

        if (todoImportance == -1 || todoTitle.isBlank()) {
            Toast.makeText(this, "모든 항목을 채워주세요", Toast.LENGTH_SHORT).show()
        } else {
            Thread {
                toDoDao.insertTodo(TodoEntity(null, todoTitle, todoImportance))
                runOnUiThread {
                    Toast.makeText(this, "추가 되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.start()
        }
    }
}