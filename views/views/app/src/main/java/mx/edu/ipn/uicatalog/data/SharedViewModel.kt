package mx.edu.ipn.uicatalog.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel compartido a nivel de Activity.
 *
 * Implementa la CONEXIÓN ENTRE SECCIONES requerida por la práctica:
 * el campo de texto "Agregar a mis favoritos" de la Sección 1 (Entrada de
 * texto) escribe en [favoritos], y la lista vertical de la Sección 4
 * (Listas y colecciones) observa esos mismos datos y los muestra como un
 * encabezado especial al inicio de la lista.
 */
class SharedViewModel : ViewModel() {

    private val _favoritos = MutableLiveData<List<String>>(emptyList())
    val favoritos: LiveData<List<String>> = _favoritos

    fun agregarFavorito(texto: String) {
        val actual = _favoritos.value.orEmpty().toMutableList()
        actual.add(0, texto)
        _favoritos.value = actual
    }
}
