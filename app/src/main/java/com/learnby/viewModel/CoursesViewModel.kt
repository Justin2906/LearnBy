package com.learnby.viewModel

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.learnby.model.Course
import com.learnby.model.FavCourses
import com.learnby.views.FavsCourses

class CoursesViewModel {
    private val _CursesList = MutableLiveData<List<Course>>()
    val cursesList: LiveData<List<Course>> = _CursesList

    private val _FavCursesList = MutableLiveData<List<FavCourses>>()
    val favCursesList: LiveData<List<FavCourses>> = _FavCursesList

    private val _Dato = MutableLiveData<HashMap<String,FavCourses>>()
    val dato: LiveData<HashMap<String,FavCourses>> = _Dato

    private val _icon =  MutableLiveData<Boolean>()
    val icon : LiveData<Boolean> = _icon

    private val db = FirebaseFirestore.getInstance()

    private val nombre_coleccion1 = "Courses"

    private val nombre_coleccion2 = "favCourses"

    @Composable
    fun viewAll(){
        val curses: MutableList<Course> = mutableListOf<Course>()
        db.collection(nombre_coleccion1)
            .get()
            .addOnSuccessListener {
                for (listaCurse in it) {
                    //datosJugadores += "${document.id}: ${document.data}\n\n"
                    val auxLista = Course(
                        listaCurse.get("id") as String,
                        listaCurse.get("iconImage") as String,
                        listaCurse.get("image") as String,
                        listaCurse.get("nameCourse") as String,
                        listaCurse.get("dificulty") as String,
                        listaCurse.get("description") as String,
                    )
                    curses.add(auxLista)
                }
                _CursesList.value = curses
                Log.d("value", _CursesList.value.toString())
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }

    fun searchFav( id: String ){
        val coursesFav: MutableList<FavCourses> = mutableListOf<FavCourses>()

        db.collection(nombre_coleccion1)
            .whereEqualTo("id", id)
            .get()
            .addOnSuccessListener { encontrado ->
                for (encontrados in encontrado){
                    //datosJugadores += "${document.id}: ${document.data}\n\n"
                    val auxLista = FavCourses(
                        encontrados.get("id") as String,
                        encontrados.get("iconImage") as String,
                        encontrados.get("nameCourse") as String,
                        encontrados.get("dificulty") as String,
                    )
                    coursesFav.add(auxLista)

                    val dato = hashMapOf(
                        "id" to encontrados.get("id") as String,
                        "iconImage" to encontrados.get("iconImage") as String,
                        "nameCourse" to encontrados.get("nameCourse") as String,
                        "dificulty" to encontrados.get("dificulty") as String
                    )

                    saveFav(id,dato)
                }
                _FavCursesList.value = coursesFav
                Log.d("cursoFav", _FavCursesList.value.toString())

            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }


    }

    fun saveFav(id: String, dato: HashMap<String, String>){

        db.collection(nombre_coleccion2)
            .document(id)
            .set(dato)
            .addOnSuccessListener {
                Log.d("mensaje", "guardado correctamente en favs")
            }
    }


}