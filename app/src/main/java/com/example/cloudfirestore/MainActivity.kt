package com.example.cloudfirestore

import android.content.ContentValues.TAG
import android.content.Intent
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
        val tobaoCadastro = findViewById<Button>(R.id.tobao)
        val tobaoLista = findViewById<Button>(R.id.lista)


        tobaoCadastro.setOnClickListener {
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

        tobaoLista.setOnClickListener{
            val nome = findViewById<EditText>(R.id.buscaNome).text.toString()
            val intent = Intent(this, ListagemActivity::class.java)
            intent.putExtra("nome", nome)
            startActivity(intent)
            finish()
        }

    }
    /*override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }*/
}