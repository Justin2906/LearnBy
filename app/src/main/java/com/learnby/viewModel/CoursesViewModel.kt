package com.learnby.viewModel

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.learnby.model.Course

class CoursesViewModel {
    private val _CursesList = MutableLiveData<List<Course>>()
    val cursesList: LiveData<List<Course>> = _CursesList

    private val db = FirebaseFirestore.getInstance()

    private val nombre_coleccion = "Curses"

    @Composable
    fun viewAll(){
        val curses: MutableList<Course> = mutableListOf<Course>()
        db.collection(nombre_coleccion)
            .get()
            .addOnSuccessListener {
                for (listaCurse in it) {
                    //datosJugadores += "${document.id}: ${document.data}\n\n"
                    val auxLista = Course(
                        listaCurse.get("img") as String,
                        listaCurse.get("nameCurse") as String,
                        listaCurse.get("dificulty") as String,
                        listaCurse.get("description") as String,
                    )
                    curses.add(auxLista)
                }
                _CursesList.value = curses
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }

}