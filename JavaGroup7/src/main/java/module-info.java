module com.example.javagroup7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens com.network.javagroup7 to javafx.fxml;
    exports com.network.javagroup7;
}