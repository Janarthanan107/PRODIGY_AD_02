package com.example.todolist

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{


            val TaskDatabase by lazy { TaskDatabase.getDatabase(this) }
            val TaskDao by lazy { TaskDatabase.taskDao() }
            var d by remember { mutableStateOf("") }

            Column (
                modifier = Modifier
                    .fillMaxHeight()){

                TopAppBar(

                    title = {Row() {

                        IconButton(
                            onClick = {
                                finish()
                            },
                            content = {
                                Icon(Icons.Filled.ArrowBack, contentDescription = "Sign Out")
                            },

                        )}}
                )
                 Column (modifier = Modifier.padding(top=20.dp, start = 30.dp)){


                     Text(text = "Add Task", fontSize = 30.sp,modifier = Modifier.padding( bottom = 30.dp))
                     TextField(value = d, onValueChange = {value-> d=value}, modifier = Modifier.clip(
                         RoundedCornerShape(20.dp)
                     ))
                     Button(onClick = {
                         lifecycleScope.launch {
                             withContext(IO) {
                                 if (d!= null) {
                                     TaskDao.saveTask(Task(id = UUID.randomUUID().toString(), name = d,false))
                                     finish()
                                 }
                             }}
                     },modifier = Modifier.padding( top= 30.dp)) {
                         Text(text = "Add")
                     }
                 }

            }
           
        }
    }
}