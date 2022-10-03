package com.example.cloudfirestore

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListagemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)

        // Access a Cloud Firestore instance from your Activity
        val db = Firebase.firestore
        val tobaoCadastro = findViewById<Button>(R.id.tobao)
        val tobaoLista = findViewById<Button>(R.id.lista)
        var name = ""

        val extras = intent.extras
        if (extras != null) {
            name = extras.getString("nome").toString()
        }
        Toast.makeText(baseContext, name, Toast.LENGTH_SHORT).show()

    }
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}