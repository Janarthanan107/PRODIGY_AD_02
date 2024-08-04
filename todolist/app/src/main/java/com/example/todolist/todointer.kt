package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

@Entity
data class Task(
    @PrimaryKey val id: String,
    val name: String,
    var status:Boolean
)

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAllTasks(): LiveData<List<Task>>

    @Insert
    suspend fun saveTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)
}
