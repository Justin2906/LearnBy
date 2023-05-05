package com.learnby.viewModel

import androidx.lifecycle.LiveData
                import androidx.lifecycle.MutableLiveData
                        import com.google.firebase.firestore.FirebaseFirestore
                        import com.learnby.model.Contador
import kotlin.math.absoluteValue

class UserViewModel {

                    private val db = FirebaseFirestore.getInstance()
                    private val nombre_coleccion = "User"

                    private val _puntuacionUser = MutableLiveData<Int>()
                    val puntuacionUser: LiveData<Int> = _puntuacionUser

                    private val _emailUser = MutableLiveData<String>()
                    val emailUser: LiveData<String> = _emailUser

                    private val datos = MutableLiveData<String>()

                    private var aux: Float = 0f

                    fun getPuntuacionByEmail(email: String){
                        db.collection(nombre_coleccion)
                            .document(email)
                            .get()
                            .addOnSuccessListener {encontrado ->
                                datos.value += " ${encontrado.data}\n"
                                aux = encontrado.get("puntuacion") as Float

                                if (datos.value!!.isEmpty()){
                                    _puntuacionUser.value = 0
                                }

                if (_puntuacionUser.value != null){
                    _puntuacionUser.value = 0
                }

            }
            .addOnFailureListener { resultado ->
                datos.value = "no se ha podido guardar"
            }
    }

}