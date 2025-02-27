package com.example.kotlincalcfxml

import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.stage.Stage

class CalcController {
    @FXML private lateinit var textField1: TextField
    @FXML private lateinit var textField2: TextField
    @FXML private lateinit var resultField: TextField
    @FXML private lateinit var colorPicker: ComboBox<String>

    @FXML
    fun initialize() {
        // Afegim colors al desplegable
        colorPicker.items.addAll("White", "LightGray", "LightBlue", "LightGreen", "Pink", "Yellow")
        colorPicker.value = "White"
    }

    @FXML
    fun onSum() = calcular { a, b -> a + b }

    @FXML
    fun onSubtract() = calcular { a, b -> a - b }

    @FXML
    fun onMultiply() = calcular { a, b -> a * b }

    @FXML
    fun onDivide() = calcular { a, b ->
        if (b == 0.0) {
            showError("No es pot dividir per zero!")
            return@calcular null
        }
        a / b
    }

    @FXML
    fun onColorChange() {
        val color = colorPicker.value
        val escena: Scene? = textField1.scene
        escena?.root?.style = "-fx-background-color: $color;"
    }

    private fun calcular(operacio: (Double, Double) -> Double?) {
        try {
            val num1 = textField1.text.toDouble()
            val num2 = textField2.text.toDouble()
            val resultat = operacio(num1, num2)

            resultField.text = resultat?.toString() ?: ""
        } catch (e: NumberFormatException) {
            showError("Introdueix números vàlids!")
        }
    }

    private fun showError(missatge: String) {
        val alert = Alert(Alert.AlertType.ERROR)
        alert.title = "Error"
        alert.headerText = null
        alert.contentText = missatge
        alert.showAndWait()
    }

    private fun onNew() {
        textField1.clear()
        textField2.clear()
        resultField.clear()
    }

    private fun onQuit() {
        val stage = textField1.scene.window as Stage
        stage.close()
    }

    private fun onAbout() {
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.title = "Sobre la calculadora"
        alert.headerText = "Calculadora FXML en Kotlin"
        alert.contentText = "Aquesta és una calculadora bàsica desenvolupada amb Kotlin i JavaFX."
        alert.showAndWait()
    }
}
