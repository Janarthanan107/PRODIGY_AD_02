package com.example.todolist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
fun add() {
    val intent = Intent(this, AddActivity::class.java)
    startActivity(intent)
}
            fun edit(id:String,status:String){
                val intent=Intent(this,EditActivity::class.java)
                intent.putExtra("id",id)
                intent.putExtra("status",status)
                startActivity(intent)
            }
             val TaskDatabase by lazy { TaskDatabase.getDatabase(this) }
            val TaskDao by lazy { TaskDatabase.taskDao() }

            val u by TaskDatabase.taskDao().getAllTasks().observeAsState(emptyList())




            Column(modifier = Modifier.padding(top=20.dp)) {
            Text(text = "Todolist", fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Text(text = "Tasks", fontSize =35.sp)

                LazyColumn(modifier = Modifier.weight(1f)) {
                items(u){ txt->
                    Row {
                       var stat by remember {
                           mutableStateOf(txt.status)
                       }
                        Checkbox(checked =stat , onCheckedChange ={c->stat=c} )
                        if (stat){
                            Text(
                                text = "${txt.name}",
                                fontSize = 30.sp,
                                modifier = Modifier.padding(top = 6.dp),
                                color = Color.LightGray,
                                textDecoration = TextDecoration.LineThrough
                            )
                        }
                        else {
                            Text(
                                text = "${txt.name}",
                                fontSize = 30.sp,
                                modifier = Modifier.padding(top = 6.dp)
                            )
                        }
                        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                            IconButton(
                                onClick = {
                                    edit(txt.id,txt.status.toString())
                                },
                                content = {
                                    Icon(Icons.Filled.Create, contentDescription = "Sign Out")
                                },

                                )
                            IconButton(
                                onClick = {
                                    lifecycleScope.launch {
                                        withContext(IO) {
                                    TaskDao.deleteTask(Task(txt.id,txt.name,txt.status))
                                }}
                                          },
                                content = {
                                    Icon(Icons.Filled.Delete, contentDescription = "Sign Out")
                                },

                                )
                        }
                    }}

            }
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp, bottom = 20.dp), horizontalArrangement = Arrangement.End){
            Button(onClick = {add()

            },modifier = Modifier
                .height(70.dp)
                .clip(RoundedCornerShape(100))) {
                Text(text = "+", fontSize = 40.sp)
            }

            }
            }

        }

        }
    }
