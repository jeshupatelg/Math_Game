package com.jeshupatelg3774.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.random.Random

class AdditionActivity : AppCompatActivity() {

    private lateinit var check : Button
    lateinit var next : Button
    lateinit var life : TextView
    private lateinit var score : TextView
    private lateinit var time : TextView
    lateinit var question : TextView
    private lateinit var answer : EditText
    private var rightAnswer : Int = 0
    private var userScore : Int = 0
    var curLife : Int = 3
    private lateinit var timer : CountDownTimer  //CountDownTimer inbuilt kotlin abstract class-> reference created here.. Object on initialization
    var timerStart : Long  = 60000L
    var timeLeft : Long = timerStart
    private var checked : Boolean = false
    private var operation : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition)
        supportActionBar!!.title = "Addition"
        operation = intent.getIntExtra("operation",1)


        check = findViewById(R.id.btnCheck)
        next = findViewById(R.id.btnNext)
        life = findViewById(R.id.txtLife)
        score = findViewById(R.id.txtScore)
        time = findViewById(R.id.txtTime)
        question = findViewById(R.id.txtQ)
        answer = findViewById(R.id.edtTxtAns)


        nextQ()

        check.setOnClickListener {

            val ans = answer.text.toString()
            if(ans == ""){
                Toast.makeText(applicationContext,"Write an Answer or Click Next",Toast.LENGTH_LONG).show()
            }
            else{
                pauseTimer()

                val userAns = ans.toInt()

                if(userAns == rightAnswer){
                    question.text = getString(R.string.correct)
                    userScore+=10
                    score.text = userScore.toString()
                }
                else{
                    curLife-=1
                    question.text = getString(R.string.wrong)
                    life.text = curLife.toString()
                }
                checked = true

            }

        }

        next.setOnClickListener {
            if(!checked){
                Toast.makeText(applicationContext,"Check the Answer first",Toast.LENGTH_LONG).show()
            }else{
                pauseTimer()
                resetTimer()

                answer.setText("")
                if(curLife ==0){
                    val intent = Intent(this@AdditionActivity,EndActivity::class.java)
                    intent.putExtra("Score",userScore)  //sends score to intent
                    startActivity(intent)
                    finish()
                }
                else{
                    nextQ()
                }
            }
        }

    }

    private fun nextQ(){
        checked = false
        val num1 = Random.nextInt(1,100)
        val num2 = Random.nextInt(1,100)


        when(operation){
            1->{ rightAnswer = num1+num2

                question.text = getString(R.string.operation,num1,"+",num2) }
            2->{ rightAnswer = num1-num2

                question.text = getString(R.string.operation,num1,"-",num2) }
            3->{ rightAnswer = num1*num2

                question.text = getString(R.string.operation,num1,"x",num2) }
            4->{ rightAnswer = num1*num2

                question.text = getString(R.string.operation,rightAnswer,"/",num1)

                rightAnswer = num2 }

        }

        timerStart()
    }

    private fun timerStart(){
        timer = object : CountDownTimer(timerStart,1000L){
            override fun onTick(timeLeft: Long) {
                this@AdditionActivity.timeLeft = timeLeft
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()

                curLife-=1
                question.text = getString(R.string.time_over)
                life.text = curLife.toString()
            }

        }.start()
    }

    fun updateText(){
        val timeRem : Int = (timeLeft/1000).toInt()
        time.text = String.format(Locale.getDefault(),"%02d",timeRem)
    }

    fun pauseTimer(){
        timer.cancel()
    }

    fun resetTimer(){
        timeLeft = timerStart
        updateText()
    }

}