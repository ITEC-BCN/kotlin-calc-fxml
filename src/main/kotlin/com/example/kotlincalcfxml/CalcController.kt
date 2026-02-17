package com.example.kotlincalcfxml

import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView

class CalcController {
    /* Definim els atributs del controller que corresponen als que hi ha a la view.
    * El nom dels paràmetres ha de coincidir amb l'id de cadascun dels elements de la vista */
    @FXML private lateinit var textField1: TextField
    @FXML private lateinit var textField2: TextField
    @FXML private lateinit var resultField: TextField
    @FXML private lateinit var colorPicker: ComboBox<String>
    @FXML private lateinit var imageView: ImageView

    @FXML
    fun initialize() {
        // Afegim colors al desplegable
        colorPicker.items.addAll("White", "LightGray", "LightBlue", "LightGreen", "Pink", "Yellow")
        // Valors per defecte
        colorPicker.value = "White"
        var imageUrl = selectorPokemon(colorPicker.value)
        imageView.image = Image(imageUrl)
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
        imageView.image = Image(selectorPokemon(color))

        val escena: Scene? = textField1.scene
        escena?.root?.style = "-fx-background-color: $color;"
    }

    /**
     * Funció privada que serveix per a mostrar la imatge del pokémon en funció del color de fons escollit passat per paràmetre
     * @param color de tipus String
     * @return imageUrl de tipus String
     * @author RIS
     */
    private fun selectorPokemon(color: String): String{
        var imageUrl: String

        imageUrl = when(color) {
            "White" -> "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/086.png"
            "LightGray" -> "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/074.png  "
            "LightBlue" -> "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/007.png"
            "LightGreen" -> "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/001.png"
            "Pink" -> "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/039.png"
            "Yellow" -> "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/025.png"
            else -> "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/011.png"
        }

        return imageUrl
    }

    private fun calcular(operacio: (Double, Double) -> Double?) {
        try {
            val num1 = textField1.text.toDouble()
            val num2 = textField2.text.toDouble()
            val resultat = operacio(num1, num2)

            resultField.text = resultat?.toString() ?: ""
        } catch (e: NumberFormatException) {
            /* Fa un control d'errors per si no pot convertir els inputs a Double.
            * Mostra un alert (finestra emergent) amb l'error amb la funció showError() definida a sota. */
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
}
