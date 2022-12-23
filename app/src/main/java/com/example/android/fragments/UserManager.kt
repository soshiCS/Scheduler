package com.example.android.fragments

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.*


class UserManager (val dataStore: DataStore<Preferences>){

    // Create the dataStore and give it a name same as shared preferences

    // Create some keys we will use them to store and retrieve the data
    companion object {
        val USER_AGE_KEY = intPreferencesKey("USER_AGE")
        val USER_NAME_KEY = stringPreferencesKey("USER_NAME")
        val TASK_SIZE = intPreferencesKey("TASK")
    }
    suspend fun storeUser(taskTitle: String,start:String,mill:Long) {
        var task = setOf<String>(taskTitle,start.toString(),mill.toString())
        dataStore.edit {
            if(it[TASK_SIZE]!=null) {
                it[TASK_SIZE] = it[TASK_SIZE]!!.inc()
                var newTask = it[TASK_SIZE].toString()
                println("fffffffffffffffffffffffff")
                println(newTask)
                it[stringSetPreferencesKey("TASK" + newTask)] = task
                var map = it.asMap()
                map.forEach { entry ->
                    print("${entry.key} : ${entry.value}")
                }


            }else{
                it.asMap().size
                it[TASK_SIZE] = 1
                var newTask = it[TASK_SIZE].toString()
                println(newTask)
                println("oooooooooooooooooooo")
                var taskKey = "TASK" +newTask
                println(taskKey)
                it[stringSetPreferencesKey("TASK" + newTask)] = task
            }
        }
    }
    suspend fun getUser(age: Int, name: String): String {
        var name1= ""
        //var task = setOf<String>("taskTitle","priority".toString(),"timeAloc.toString()","start.toString()","due.toString()")

        dataStore.edit {
            println("sssdddddggggggggggggggg")
            var maps = it.asMap()
            maps.forEach { t, u -> println(println(t.toString()) )
                println(u.toString())
            }
        }
        return name1
    }

    // Create an age flow to retrieve age from the preferences
    // flow comes from the kotlin coroutine
    val userAgeFlow: Flow<Int> = dataStore.data.map {
        it[USER_AGE_KEY] ?: 0

    }

    // Create a name flow to retrieve name from the preferences
    val userNameFlow: Flow<String> = dataStore.data.map {
        it[USER_NAME_KEY] ?: ""
    }


}
