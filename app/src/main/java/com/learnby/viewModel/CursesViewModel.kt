package com.learnby.viewModel

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.learnby.R
import com.google.firebase.firestore.FirebaseFirestore
import com.learnby.model.Cursos

class CursesViewModel {
    private val _CursesList = MutableLiveData<List<Cursos>>()
    val cursesList: LiveData<List<Cursos>> = _CursesList

    private val db = FirebaseFirestore.getInstance()

    private val nombre_coleccion = "Curses"

    @Composable
    fun viewAll(){
        val curses: MutableList<Cursos> = mutableListOf<Cursos>()
        db.collection(nombre_coleccion)
            .get()
            .addOnSuccessListener {
                for (listaCurse in it) {
                    //datosJugadores += "${document.id}: ${document.data}\n\n"
                    val auxLista = Cursos(
                        R.drawable.pythoncurso,
                        listaCurse.get("nameCurse") as String,
                        listaCurse["dificulty"].toString(),
                        listaCurse["description"].toString(),
                    )
                    curses.add(auxLista)
                    //Log.d("Datos", listaCurses.toString())

                }
                _CursesList.value = curses
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }

}