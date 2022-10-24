package com.example.cloudfirestore

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListagemActivity : AppCompatActivity() {
    private lateinit var alert: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)

        // Access a Cloud Firestore instance from your Activity
        val db = Firebase.firestore


        val tobaoEdita = findViewById<Button>(R.id.btEdita)
        val tobaoExclui = findViewById<Button>(R.id.btExclui)

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

        tobaoExclui.setOnClickListener {
            val build = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.dialog_excluir,null)
            build.setView(view)
            val botao = view.findViewById<Button>(R.id.fechaaa)
            val botao2 = view.findViewById<Button>(R.id.btExcluir)
            botao.setOnClickListener {
                alert.dismiss()
            }

            botao2.setOnClickListener {
                val nome = view.findViewById<EditText>(R.id.edtEx).text.toString()
                val doc = db.collection("users").whereEqualTo("Nome", nome).get()
                    .addOnSuccessListener { documents ->
                        if(!documents.isEmpty) {
                            for (document in documents)
                            db.collection("users").document(document.id).delete()
                                .addOnSuccessListener {
                                    Toast.makeText(baseContext, "Document Excluído", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(baseContext, "Erro: Document Não Excluído", Toast.LENGTH_SHORT).show()
                                }
                        }
                        }
                    .addOnFailureListener {
                        Toast.makeText(baseContext, "Erro: Document Não Encontrado", Toast.LENGTH_SHORT).show()
                    }
                //docRef.where()
            }





            alert = build.create()
            alert.show()
            alert.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }


    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}