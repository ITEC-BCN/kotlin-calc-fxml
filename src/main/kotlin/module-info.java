module com.example.kotlincalcfxml {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens com.example.kotlincalcfxml to javafx.fxml;
    exports com.example.kotlincalcfxml;
}