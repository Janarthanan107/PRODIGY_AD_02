package com.example.todolist

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            var d=""
            Column (
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight().padding(top=20.dp, start = 30.dp)){

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

                Text(text = "Add Task", fontSize = 30.sp)
                TextField(value = d, onValueChange = {})

            }
           
        }
    }
}