package sk.palenka.display;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class Display {

    private static final Logger LOG = Logger.getLogger(Display.class);


    private Canvas canvas;
    private String title;
    private Integer width;
    private Integer height;
    private JTextArea text = new JTextArea(1,1);

    public Display(String title, Integer width, Integer height) {
        this.title = title;
        this.height = height;
        this.width = width;

        initWindow(title, height, width);
    }

    private void initWindow(String title, Integer height, Integer width) {
        JFrame window = new JFrame( title );
        window.setSize(width, height);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.add(text, BorderLayout.NORTH);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        window.add(canvas);
        window.pack();
        LOG.debug("created new window with title = " + title + " , width = " + width + " and height = " + height);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public String getText() { return text.getText(); }

    public void setjText(String text) { this.text.setText(text); }
}
