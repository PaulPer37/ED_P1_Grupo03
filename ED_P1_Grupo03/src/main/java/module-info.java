module ec.edu.espol.ed_p1_grupo03 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.ed_p1_grupo03 to javafx.fxml;
    exports ec.edu.espol.ed_p1_grupo03;
}
