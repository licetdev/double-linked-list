package ec.edu.espoch.doublylinkedlist.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Collections;
import java.util.List;

public class ListaDobleCanvas extends Canvas {

    private List<Integer> values = Collections.emptyList();
    private int highlightIndex = -1;

    private final double margin = 24;
    private final double nodeW = 140;
    private final double nodeH = 55;
    private final double spacing = 55;
    private final double baseY = 150;

    public ListaDobleCanvas() {
        setWidth(1200);
        setHeight(360);
    }

    // ================================
    // API CONTROLADOR
    // ================================
    public void setValues(List<Integer> values) {
        this.values = values == null ? Collections.emptyList() : values;
    }

    public void setHighlightIndex(int idx) {
        this.highlightIndex = idx;
    }

    public void clearHighlight() {
        this.highlightIndex = -1;
    }

    // ================================
    // RENDER
    // ================================
    public void render() {
        GraphicsContext g = getGraphicsContext2D();

        g.setFill(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setFont(Font.font(16));
        g.setFill(Color.BLACK);
        g.fillText("Lista Doblemente Enlazada", margin, 28);

        g.setFont(Font.font(12));
        g.setFill(Color.DARKGRAY);
        g.fillText("[ prev | dato | next ]", margin, 48);

        if (values.isEmpty()) {
            g.setFont(Font.font(14));
            g.setFill(Color.GRAY);
            g.fillText("La lista está vacía.", margin, baseY);
            return;
        }

        double x = margin;

        for (int i = 0; i < values.size(); i++) {
            boolean hl = (i == highlightIndex);
            drawNode(g, x, baseY, values.get(i), hl);

            if (i < values.size() - 1) {
                drawNextArrow(g, x);
                drawPrevArrow(g, x);
            }

            x += nodeW + spacing;
        }

        drawNull(g, x - spacing + 20);
    }

    // ================================
    // DIBUJO DEL NODO
    // ================================
    private void drawNode(GraphicsContext g, double x, double y, int dato, boolean highlighted) {

        double prevW = nodeW * 0.25;
        double dataW = nodeW * 0.5;

        g.setLineWidth(highlighted ? 3 : 2);
        g.setStroke(highlighted ? Color.rgb(25, 90, 200) : Color.BLACK);
        g.strokeRoundRect(x, y, nodeW, nodeH, 18, 18);

        // divisiones
        g.strokeLine(x + prevW, y, x + prevW, y + nodeH);
        g.strokeLine(x + prevW + dataW, y, x + prevW + dataW, y + nodeH);

        // texto prev
        g.setFont(Font.font(11));
        g.setFill(Color.FORESTGREEN);
        g.fillText("prev", x + 8, y + 32);

        // texto dato
        g.setFont(Font.font(14));
        g.setFill(Color.BLACK);
        g.fillText(String.valueOf(dato), x + prevW + 20, y + 34);

        // texto next
        g.setFont(Font.font(11));
        g.setFill(Color.DODGERBLUE);
        g.fillText("next", x + prevW + dataW + 8, y + 32);

        if (highlighted) {
            g.setFont(Font.font(11));
            g.setFill(Color.rgb(25, 90, 200));
            g.fillText("FOUND", x + 6, y - 6);
        }
    }

    // ================================
    // FLECHA NEXT →
    // ================================
    private void drawNextArrow(GraphicsContext g, double x) {
        double y = baseY + nodeH / 2 - 6;
        double start = x + nodeW * 0.85;
        double end = start + spacing - 10;

        g.setStroke(Color.DODGERBLUE);
        g.setLineWidth(2);
        g.strokeLine(start, y, end, y);
        arrowHead(g, start, y, end, y);
    }

    // ================================
    // FLECHA PREV ←
    // ================================
    private void drawPrevArrow(GraphicsContext g, double x) {
        double y = baseY + nodeH / 2 + 6;
        double end = x + nodeW * 0.15;
        double start = end + spacing - 10;

        g.setStroke(Color.FORESTGREEN);
        g.setLineWidth(2);
        g.strokeLine(start, y, end, y);
        arrowHead(g, start, y, end, y);
    }

    // ================================
    // NULL FINAL
    // ================================
    private void drawNull(GraphicsContext g, double x) {
        g.setFont(Font.font(12));
        g.setFill(Color.GRAY);
        g.fillText("null", x + nodeW, baseY + nodeH / 2 + 4);
    }

    // ================================
    // PUNTA DE FLECHA
    // ================================
    private void arrowHead(GraphicsContext g, double x1, double y1, double x2, double y2) {
        double angle = Math.atan2(y2 - y1, x2 - x1);
        double len = 10;

        double a1 = angle - Math.PI / 8;
        double a2 = angle + Math.PI / 8;

        g.strokeLine(
                x2, y2,
                x2 - len * Math.cos(a1),
                y2 - len * Math.sin(a1)
        );
        g.strokeLine(
                x2, y2,
                x2 - len * Math.cos(a2),
                y2 - len * Math.sin(a2)
        );
    }

}
