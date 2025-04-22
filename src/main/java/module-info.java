module org.example.project_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens org.example.project_2 to javafx.fxml;
    exports org.example.project_2;
}