package com.example.android.fragments

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.size
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

val Context.dataStore by preferencesDataStore(name = "user_prefs")
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val id = 1
    private val CHANNEL_ID = "MyChannel"

    var pendingIntent: PendingIntent? = null
    lateinit var taskTitle: EditText
    lateinit var priority: EditText
    lateinit var startTimeT: EditText
    lateinit var startDateT: EditText
    var alarmManager: AlarmManager? = null
    lateinit var userManager: UserManager
    var age = 0
    var name = ""

    public override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.textview_layout)
        startTimeT = findViewById(R.id.editTextTime)
        startDateT=findViewById(R.id.editTextDate)

        taskTitle = findViewById(R.id.et_name)

        taskTitle.setError("wrong number")
        // Get reference to our userManager class
        userManager = UserManager(dataStore)
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

     //   buttonSave()
   //     buttonget()

        observeData()
        super.onCreate(savedInstanceState)
        val fragTran = supportFragmentManager.beginTransaction()
        fragTran.add(R.id.article_fragment,MiddleFragment())
        fragTran.commit()

    }

    fun onArticleSelected(position: Int) {
println("ayooooooooooo")
        if(position ==7){
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.article_fragment,MiddleFragment())
            transaction.commitNow()
        }
        if(position==9){

            GlobalScope.launch {dataStore?.edit {
                var map = it.asMap()
                println("khaaaarpaaaaareeee")
                println("hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeee")
                map.forEach { entry ->
                    var row = TableRow(this@MainActivity)
                    print("${entry.key} : ${entry.value}")
                    var txt = TextView(this@MainActivity)
                    var txt2 = TextView(this@MainActivity)
                    var txt3 = TextView(this@MainActivity)
                    var set = setOf( map.get(entry.key) ) as Set<Any?>
                    var elm= set.toList()

                    println(elm.get(0))
                    println("elmmmmmmmmm2bottom")
                  elm.forEach { println(elm.get(0))
                  }
                    if (elm.size>=0)
                        txt.setText("     " +entry.key.toString() +" =>  "+ ((elm.get(0))).toString() )
                    if (elm.size>1)
                        txt3.setText("   "+ set.toSet().toList()[2].toString() )
                    if (elm.size>2){
                        txt3.setText("   "+ set.toSet().toList()[3].toString() )
                   // var lon = (elm.get(0))?[0]  .toLong()
                    }
                    System.currentTimeMillis()
                    if(true){
                    var table= findViewById<TableLayout>(R.id.table)

                    if(entry.key.toString() != "TASK"){

                        // row.setPaddingRelative(20,20,10,5)
                        row.addView(txt , 0)
//           row.addView(txt2,1)
                        table?.layout(5,5,5,5)
                        row.layout(10,10,10,10)

                        println(table?.size)
                        println("you got it mannnnnnnnnnzzzzzzzzzzzzzzzz")

                        this@MainActivity.runOnUiThread(java.lang.Runnable {
                            table?.removeView(row)
                            table?.addView(row)
                        })
                    }
                }
                }

            }
            }

        }
        if(position==2) {
            GlobalScope.launch {
                name =  userManager.getUser(12,"TASK1")
var lay = R.layout.field_and_button_layout

            }
          println(  name.length)
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.article_fragment,BottomFragment())
            transaction.commitNow()

            val articleFrag = supportFragmentManager.findFragmentById(R.id.article_fragment) as BottomFragment
           // articleFrag.updateArticleView(position)
     }


        else if(position==1) {

            startTimeT = findViewById(R.id.editTextTime)
            startDateT=findViewById(R.id.editTextDate)
          //  endTimeT = findViewById(R.id.editTextTime2)
        //    endDateT = findViewById(R.id.editTextTime2)
            taskTitle = findViewById(R.id.et_name)
            //endDateT = findViewById(R.id.editTextDate)
            var task = taskTitle?.text.toString()
            var startDate = startDateT?.text.toString()
            var startTime = startTimeT?.text.toString()
            var stimeH = startTime[0].toString() + startTime[1].toString()
            var stimeM = startTime[3].toString() + startTime[4].toString()
            var sdm = startDate[0] + startDate[1].toString();
            var sdd = startDate[3] + startDate[4].toString();
            var sdy = startDate[6] + startDate[7].toString()+startDate[8] + startDate[9].toString();

            val start = Calendar.getInstance()
            start.set(sdy.toInt(),sdm.toInt() -1,sdd.toInt(),stimeH.toInt(),stimeM.toInt())
            println("startttttt")
            println(stimeH)
            println(stimeM)
            println(sdy)
            println(sdm)
            println(sdd)
            println("Endddddd")
            println("hereeee we goooooooooooo")
            println(start.timeInMillis)
            //println(end.timeInMillis)
            OnToggleClicked(start.timeInMillis)
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
            val dateString = simpleDateFormat.format(start.timeInMillis - start.timeInMillis %60000)
            GlobalScope.launch {
               userManager.storeUser(task,dateString.toString(),start.timeInMillis - start.timeInMillis %60000)
            }
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.article_fragment,MiddleFragment())
            transaction.commitNow()
            val articleFrag = supportFragmentManager.findFragmentById(R.id.article_fragment) as MiddleFragment
            articleFrag.updateArticleView(position)
        }
    }


    fun OnToggleClicked( timer:Long) {
        var time: Long
        if (true/*(view as ToggleButton).isChecked*/) {
            Toast.makeText(this@MainActivity, "ALARM ON", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AlarmReceiver::class.java)
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_MUTABLE)
            time = timer - timer % 60000
            println("Timeeeeeee")
            println(time)
            alarmManager!!.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent)
        } else {
            Toast.makeText(this@MainActivity, "ALARM OFF", Toast.LENGTH_SHORT).show()
            alarmManager!!.cancel(pendingIntent)
            print("yoooooooooooooElse")
        }

    }
    private fun observeData() {
        this.userManager.userAgeFlow.asLiveData().observe(this) {
            age = it as Int
            // tvAge.text = it.toString()
        }
        userManager.userNameFlow.asLiveData().observe(this) {
        }
        println( userManager.dataStore.data.toString())
    }


}