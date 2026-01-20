
package ec.edu.espoch.doublylinkedlist.controller;

import ec.edu.espoch.doublylinkedlist.model.ListaDoble;
import ec.edu.espoch.doublylinkedlist.view.ListaDobleCanvas;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class ListController {

    @FXML
    private TextField txtValue;

    @FXML
    private TextField txtReferencia; // para insertar después

    @FXML
    private StackPane canvasHolder;

    private ListaDobleCanvas canvas;
    private final ListaDoble lista = new ListaDoble();

    @FXML
    private void initialize() {
        canvas = new ListaDobleCanvas();
        canvasHolder.getChildren().add(canvas);
        refreshView();
    }

    @FXML
    private void insertarInicio() {
        Integer v = readInt(txtValue);
        if (v != null) {
            lista.insertarInicio(v);
            canvas.clearHighlight();
            refreshView();
        }
    }

    @FXML
    private void insertarFinal() {
        Integer v = readInt(txtValue);
        if (v != null) {
            lista.insertarFinal(v);
            canvas.clearHighlight();
            refreshView();
        }
    }

    @FXML
    private void insertarDespues() {
        Integer ref = readInt(txtReferencia);
        Integer v = readInt(txtValue);

        if (ref != null && v != null) {
            lista.insertarDespues(ref, v);
            canvas.clearHighlight();
            refreshView();
        }
    }

    @FXML
    private void eliminar() {
        Integer v = readInt(txtValue);
        if (v != null) {
            lista.eliminar(v);
            canvas.clearHighlight();
            refreshView();
        }
    }

    @FXML
    private void buscar() {
        Integer v = readInt(txtValue);
        if (v != null) {
            int idx = lista.indexOf(v);
            if (idx >= 0) {
                canvas.setHighlightIndex(idx);
            } else {
                canvas.clearHighlight();
            }
            refreshView();
        }
    }

    private Integer readInt(TextField txt) {
        try {
            String s = txt.getText().trim();
            return s.isEmpty() ? null : Integer.valueOf(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void refreshView() {
        canvas.setValues(lista.toList());
        canvas.render();
    }

    
}
