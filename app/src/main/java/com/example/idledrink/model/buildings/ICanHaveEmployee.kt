package com.example.idledrink.model.buildings

interface ICanHaveEmployee {
    val employee: Int
    val maxEmployees: Int
    fun addEmployee(numbertoAdd: Int)
}