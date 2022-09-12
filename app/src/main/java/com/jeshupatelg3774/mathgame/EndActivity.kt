package com.jeshupatelg3774.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class EndActivity : AppCompatActivity() {

    lateinit var exit : Button
    lateinit var again : Button
    lateinit var res : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)

        exit = findViewById(R.id.btnExit)
        again = findViewById(R.id.btnPlay)
        res = findViewById(R.id.txtResult)

        val score = intent.getIntExtra("Score",0)
        res.text = "Score : $score"

        again.setOnClickListener {
            val intent = Intent(this@EndActivity,MainActivity::class.java)
            startActivity(intent)
            finish()  //will close this intent so back will not track back to this page after getting back to start page
        }

        exit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)       // Standard codes
            intent.addCategory(Intent.CATEGORY_HOME)      // for closing
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK  // an android
            startActivity(intent)                         // application
            //finish() will only close this intent and not entire app
        }

    }
}