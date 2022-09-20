package com.example.cloudfirestore

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Access a Cloud Firestore instance from your Activity
        val db = Firebase.firestore
        val tobao = findViewById<Button>(R.id.tobao)

        tobao.setOnClickListener {
        val nomi = findViewById<EditText>(R.id.edtNome).text.toString()
        val endereco = findViewById<EditText>(R.id.edtEndereco).text.toString()
        val bairro = findViewById<EditText>(R.id.edtBairro).text.toString()
        val cep = findViewById<EditText>(R.id.edtCep).text.toString()



            // Create a new user with a first and last name
            val user = hashMapOf(
                "Nome" to nomi,
                "Endereço" to endereco,
                "Bairro" to bairro,
                "CEP" to cep
            )

// Add a new document with a generated ID
            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }

    }
}