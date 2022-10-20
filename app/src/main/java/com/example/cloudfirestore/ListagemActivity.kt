package com.example.cloudfirestore

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListagemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)

        // Access a Cloud Firestore instance from your Activity
        val db = Firebase.firestore
        val docRef = db.collection("users")
        val display = findViewById<TextView>(R.id.display)
        docRef.get().addOnSuccessListener { documents ->
            for (document in documents) {
                val data = document.data
                Log.d(TAG, "${document.id} => ${document.data}")
                display.append("Nome: ${data["Nome"]}\n")
                display.append("Telefone: ${data["Telefone"]}\n")
                display.append("Sexo: ${data["Sexo"]}\n\n")


            }



        }


    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}