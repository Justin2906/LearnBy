package com.learnby.model.Courses.Python

import androidx.annotation.DrawableRes
import com.example.learnby.R


data class Python(
    @DrawableRes val imageResource: Int,
    val info: String,
    val description: String
)

/*val pythonList = listOf(
    Python(
        R.drawable.uno,
        title = "Tipos de Datos Primitivos Simples",
        listOf(
            "- Números (numbers): Secuencia de dígitos (pueden incluir el - para negativos y el . para decimales) que representan números.",
            "Ejemplo. 0, -1, 3.1415",
            "- Cadenas (strings): Secuencia de caracteres alfanuméricos que representan texto. Se escriben entre comillas simples o dobles.",
            "Ejemplo. ‘Hola’, “Adiós”",
            "- Booleanos (boolean): Contiene únicamente dos elementos True y False que representan los valores lógicos verdadero y falso respectivamente."
        )
    ),
    Python(
        R.drawable.uno,
        title = "Tipos de datos primitivos compuestos",
        listOf(
            "- Listas (lists): Colecciones de objetos que representan secuencias ordenadas de objetos de distintos tipos. Se representan con corchetes y los elementos se separan por comas.",
            "- Tuplas (tuples). Colecciones de objetos que representan secuencias ordenadas de objetos de distintos tipos. A diferencia de las listas son inmutables, es decir, que no cambian durante la ejecución.",
            "- Diccionarios (dictionaries): Colecciones de objetos con una clave asociada. Se representan con llaves, los pares separados por comas y cada par contiene una clave y un objeto asociado separados por dos puntos."
        )
    ),
    Python(
        R.drawable.aritmeticos,
        title = "Operadores aritméticos",
        listOf("+ (suma), - (resta), * (producto), / (cociente), // (cociente división entera), % (resto división entera), ** (potencia).")
    ),
    Python(
        R.drawable.logicos,
        title = "Operadores lógicos",
        listOf("== (igual que), > (mayor que), < (menor que), >= (mayor o igual que), <= (menor o igual que), != (distinto de).")
    ),
    Python(
        R.drawable.subcadenas,
        title = "Subcadenas",
        listOf("c[i:j:k] : Devuelve la subcadena de c desde el carácter con el índice i hasta el carácter anterior al índice j, tomando caracteres cada k.")
    ),
    Python(
        R.drawable.funcadenas,
        title = "Funciones de cadenas",
        listOf(
            "- len(c) : Devuelve el número de caracteres de la cadena c.",
            "- min(c) : Devuelve el carácter menor de la cadena c.",
            "- max(c) : Devuelve el carácter mayor de la cadena c.",
            "- c.upper() : Devuelve la cadena con los mismos caracteres que la cadena c pero en mayúsculas.",
            "- c.lower() : Devuelve la cadena con los mismos caracteres que la cadena c pero en minúsculas.",
            "- c.title() : Devuelve la cadena con los mismos caracteres que la cadena c con el primer carácter en mayúsculas y el resto en minúsculas.",
            "- c.split(delimitador) : Devuelve la lista formada por las subcadenas que resultan de partir la cadena c usando como delimitador la cadena delimitador. Si no se especifica el delimitador utiliza por defecto el espacio en blanco."
        )
    )
)*/