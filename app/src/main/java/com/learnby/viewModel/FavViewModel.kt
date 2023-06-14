package com.learnby.viewModel

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.learnby.model.Course
import com.learnby.model.FavCourses

class FavViewModel {
    private val _FavCursesList = MutableLiveData<List<FavCourses>>()
    val favCursesList: LiveData<List<FavCourses>> = _FavCursesList

    private val db = FirebaseFirestore.getInstance()

    private val nombre_coleccion = "favCourses"

    @Composable
    fun viewAll(){
        val curses: MutableList<FavCourses> = mutableListOf<FavCourses>()
        db.collection(nombre_coleccion)
            .get()
            .addOnSuccessListener {
                for (listaCurse in it) {
                    //datosJugadores += "${document.id}: ${document.data}\n\n"
                    val auxLista = FavCourses(
                        listaCurse.get("id") as String,
                        listaCurse.get("iconImage") as String,
                        listaCurse.get("nameCourse") as String,
                        listaCurse.get("dificulty") as String,
                    )
                    curses.add(auxLista)
                }
                _FavCursesList.value = curses
                Log.d("value", _FavCursesList.value.toString())
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }

    fun deleteFav(id: String) {
        db.collection(nombre_coleccion)
            .document(id)
            .delete()
            .addOnSuccessListener {
                Log.d("mensaje","Datos borrados correctamente")
            }
            .addOnFailureListener {
                Log.d("mensaje", "No se ha podido borrar")

            }
    }
}