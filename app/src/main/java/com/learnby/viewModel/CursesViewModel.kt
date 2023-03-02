package com.learnby.viewModel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.learnby.R
import com.google.firebase.firestore.FirebaseFirestore
import com.learnby.model.Cursos

class CursesViewModel {

    private val _tittle = MutableLiveData<String>()
    val tittle : LiveData<String> = _tittle

    private val _dificulty = MutableLiveData<String>()
    val dificulty : LiveData<String> = _dificulty

    private val _description = MutableLiveData<String>()
    val description : LiveData<String> = _description

    private val _CursesList = MutableLiveData<ArrayList<Cursos>>()
    val cursesList: LiveData<ArrayList<Cursos>> = _CursesList

    private val _datosCurso = MutableLiveData<String>()
    val datosCurso: LiveData<String> = _datosCurso

}