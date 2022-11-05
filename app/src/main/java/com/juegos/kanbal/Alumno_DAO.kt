package com.juegos.kanbal

data class Alumno_DAO (
    var id_alumno: Int = 0,
    var nombre: String = "",
    var apellido: String = "",
    var correo: String = "",
    var contraseña:	String = "",
    var id_colegio:	Int = 0,
    var grado: Int = 0
)
