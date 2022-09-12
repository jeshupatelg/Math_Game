package com.jeshupatelg3774.mathgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var add : Button
    private lateinit var sub : Button
    private lateinit var mul : Button
    private lateinit var div : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add = findViewById(R.id.btnAdd)
        sub = findViewById(R.id.btnSub)
        mul = findViewById(R.id.btnMul)
        div = findViewById(R.id.btnDiv)

        add.setOnClickListener {
            val intent = Intent(this@MainActivity,AdditionActivity::class.java)
            intent.putExtra("operation",1)
            startActivity(intent)
            //finish()  ---  closing this intent will make it so that back button will not track ack to this page
        }

        sub.setOnClickListener {
            val intent = Intent(this@MainActivity,AdditionActivity::class.java)
            intent.putExtra("operation",2)
            startActivity(intent)
            //finish()
        }

        mul.setOnClickListener {
            val intent = Intent(this@MainActivity,AdditionActivity::class.java)
            intent.putExtra("operation",3)
            startActivity(intent)
            //finish()
        }

        div.setOnClickListener {
            val intent = Intent(this@MainActivity,AdditionActivity::class.java)
            intent.putExtra("operation",4)
            startActivity(intent)
            //finish()
        }


    }
}