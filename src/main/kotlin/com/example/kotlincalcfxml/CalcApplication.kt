package com.example.kotlincalcfxml

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class CalcApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(CalcApplication::class.java.getResource("calc-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 400.0, 400.0)
        stage.title = "Calculadora Color!"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(CalcApplication::class.java)
}