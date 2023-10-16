module com.example.learn {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.learn to javafx.fxml;
    exports com.example.learn;
}