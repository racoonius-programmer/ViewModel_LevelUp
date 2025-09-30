package com.example.viewmodela.viewmodel
import androidx.lifecycle.ViewModel
import com.example.viewmodela.model.UsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.example.viewmodela.model.UsuarioErrores


class UsuarioViewModel: ViewModel() {

// Estado interno mutable
    private val _estado = MutableStateFlow(UsuarioUiState())

    // Estado expuesto para la UI
    val estado: StateFlow<UsuarioUiState> = _estado

    // Actualiza el campo nombre y limpia su error
    fun onNombreChange (valor: String) {
    _estado.update {it.copy(nombre = valor, errores = it.errores.copy(nombre = null)) }
    }
    // Actualiza el campo correo
    fun onCorreoChange (valor: String) {
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }

    // Actualiza el campo clave
    fun onClaveChange (valor: String) {
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }
    // Actualiza el campo dirección
    fun onDireccionChange(valor: String) {
        _estado.update { it.copy(direccion = valor, errores = it.errores.copy(direccion = null)) }
    }
    // Actualiza checkbox de aceptación
    fun onAceptarTerminosChange (valor: Boolean) {
        _estado.update { it.copy(aceptaTerminos = valor) }
    }

    // Validación global de los campos del formulario
    fun validarFormulario() : Boolean {
        val estadoActual = _estado.value
        val errores = UsuarioErrores(
            nombre = if (estadoActual.nombre.isBlank()) "Campo obligatorio" else null,
            correo = if (!estadoActual.correo.contains("@")) "El correo es requerido" else null,
            clave = if (estadoActual.clave.length < 6) "La clave debe tener al menos 6 caracteres" else null,
            direccion = if (estadoActual.direccion.isBlank()) "Campo obligatorio" else null,
        )

        val hayErrores = listOfNotNull(
            errores.nombre,
            errores.correo,
            errores.clave,
            errores.direccion,
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores) }

        return !hayErrores
    }
}