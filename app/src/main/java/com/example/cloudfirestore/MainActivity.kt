package com.example.cloudfirestore

import android.app.ProgressDialog.show
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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Access a Cloud Firestore instance from your Activity
        val db = Firebase.firestore
        val tobaoCadastro = findViewById<Button>(R.id.tobao)
        val tobaoLista = findViewById<Button>(R.id.lista)

        val nomi = findViewById<EditText>(R.id.edtNome)
        val telefone = findViewById<EditText>(R.id.edtTelefone)
        val sexo = findViewById<EditText>(R.id.edtSexo)

        tobaoCadastro.setOnClickListener {
            tobaoCadastro.isEnabled = false
            tobaoLista.isEnabled = false

            val txtNome = nomi.text.toString()
            val txtTelefone = telefone.text.toString()
            val txtSexo = sexo.text.toString()

            // Create a new user with a first and last name
            val user = hashMapOf(
                "Nome: " to txtNome,
                "Telefone: " to txtTelefone,
                "Sexo: " to txtSexo
            )

            // Add a new document with a generated ID
            if (txtNome.isEmpty() || txtTelefone.isEmpty() || txtSexo.isEmpty()) {

            }
            else {
                db.collection("users")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                        Toast.makeText(baseContext, "Sucesso!", Toast.LENGTH_SHORT).show()

                        nomi.setText("")
                        telefone.setText("")
                        sexo.setText("")
                        tobaoCadastro.isEnabled = true
                        tobaoLista.isEnabled = true
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                        tobaoCadastro.isEnabled = true
                        tobaoLista.isEnabled = true
                    }

            }
        }

        tobaoLista.setOnClickListener {
            val intent = Intent(this, ListagemActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}