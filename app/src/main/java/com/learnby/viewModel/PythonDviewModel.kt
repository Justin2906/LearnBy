package com.learnby.viewModel

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.learnby.R
import com.google.firebase.firestore.FirebaseFirestore
import com.learnby.model.Course
import com.learnby.model.Courses.Python.Python
import com.learnby.navigation.Routes
import com.learnby.views.PythonView

class PythonDviewModel {
    private val _documentationList = MutableLiveData<List<Python>>()
    val documentationList: LiveData<List<Python>> = _documentationList

    private val db = FirebaseFirestore.getInstance()

    private val nombre_coleccion = "DocumentationPython"

    @Composable
    fun getDocumentation(){
        val docu: MutableList<Python> = mutableListOf<Python>()

        db.collection(nombre_coleccion)
            .get()
            .addOnSuccessListener {
                for (documentation in it) {
                    //datosJugadores += "${document.id}: ${document.data}\n\n"
                    val auxLista = Python(
                        documentation.get("img") as String,
                        documentation.get("description") as String,
                        documentation.get("info") as String,
                    )
                    docu.add(auxLista)
                }
                _documentationList.value = docu
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }
}