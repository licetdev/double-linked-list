module ec.edu.espoch.doublylinkedlist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espoch.doublylinkedlist.controller to javafx.fxml;

    exports ec.edu.espoch.doublylinkedlist;
    exports ec.edu.espoch.doublylinkedlist.controller;
    exports ec.edu.espoch.doublylinkedlist.model;
    exports ec.edu.espoch.doublylinkedlist.view;

}
