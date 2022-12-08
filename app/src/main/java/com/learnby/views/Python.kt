package com.learnby.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learnby.R


data class Python(
    @DrawableRes val imageResource: Int,
    val title: String,
    val description: List<String>
)

val pythonList = listOf(
    Python(
        R.drawable.uno,
        title = "Tipos de Datos Primitivos Simples",
        listOf("- Números (numbers): Secuencia de dígitos (pueden incluir el - para negativos y el . para decimales) que representan números.\n" +
                "Ejemplo. 0, -1, 3.1415.\n" +
                "- Cadenas (strings): Secuencia de caracteres alfanuméricos que representan texto. Se escriben entre comillas simples o dobles.\n" +
                "Ejemplo. ‘Hola’, “Adiós”.\n" +
                "- Booleanos (boolean): Contiene únicamente dos elementos True y False que representan los valores lógicos verdadero y falso respectivamente."
        )
),
    Python(
        R.drawable.uno,
        title = "Tipos de datos primitivos compuestos",
        listOf("- Listas (lists): Colecciones de objetos que representan secuencias ordenadas de objetos de distintos tipos. Se representan con corchetes y los elementos se separan por comas.\n" +
            "- Tuplas (tuples). Colecciones de objetos que representan secuencias ordenadas de objetos de distintos tipos. A diferencia de las listas son inmutables, es decir, que no cambian durante la ejecución.\n"+
            "- Diccionarios (dictionaries): Colecciones de objetos con una clave asociada. Se representan con llaves, los pares separados por comas y cada par contiene una clave y un objeto asociado separados por dos puntos.")
    ),
    Python(
        R.drawable.aritmeticos,
        title = "Operadores aritméticos",
        listOf("+ (suma), - (resta), * (producto), / (cociente), // (cociente división entera), % (resto división entera), ** (potencia).",
            )
    ),
    Python(
        R.drawable.logicos,
        title = "Operadores lógicos",
        listOf("== (igual que), > (mayor que), < (menor que), >= (mayor o igual que), <= (menor o igual que), != (distinto de).",
        )
    ),
    Python(
        R.drawable.subcadenas,
        title = "Subcadenas",
        listOf("c[i:j:k] : Devuelve la subcadena de c desde el carácter con el índice i hasta el carácter anterior al índice j, tomando caracteres cada k.",
        )
    ),
    Python(
        R.drawable.funcadenas,
        title = "Funciones de cadenas",
        listOf("- len(c) : Devuelve el número de caracteres de la cadena c.\n" +
                "- min(c) : Devuelve el carácter menor de la cadena c.\n" +
                "- max(c) : Devuelve el carácter mayor de la cadena c.\n" +
                "- c.upper() : Devuelve la cadena con los mismos caracteres que la cadena c pero en mayúsculas.\n" +
                "- c.lower() : Devuelve la cadena con los mismos caracteres que la cadena c pero en minúsculas.\n" +
                "- c.title() : Devuelve la cadena con los mismos caracteres que la cadena c con el primer carácter en mayúsculas y el resto en minúsculas.\n" +
                "- c.split(delimitador) : Devuelve la lista formada por las subcadenas que resultan de partir la cadena c usando como delimitador la cadena delimitador. Si no se especifica el delimitador utiliza por defecto el espacio en blanco.",
        )
    )
)




@Composable
fun Documentacion(python: Python){

    val image =  painterResource(python.imageResource)

    Surface(
        modifier = Modifier
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        color = Color(0xFF373960)
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            val imageModifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))

            Image(
                painter = image,
                contentDescription = null,
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = python.title,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )

            for (description in python.description){
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    color = Color.White
                )
            }

        }
    }
}
@Composable
fun CursoPython(){
    Column {
        PythonList(pythonList = pythonList)
    }
}
@Composable
fun PythonList(pythonList: List<Python>){
    LazyColumn(modifier = Modifier
        .background(Color(0xFF212338))
    ){
        items(pythonList){pythonList ->
            Documentacion(pythonList)
        }
    }
}

@Preview
@Composable
fun PreviewVistaPython(){
    PythonList(pythonList = pythonList)
}


