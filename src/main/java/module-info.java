module onpoint.onpoint {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.OnPoint to javafx.fxml;
    exports com.OnPoint;
}