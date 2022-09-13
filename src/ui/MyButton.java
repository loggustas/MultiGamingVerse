package ui;

import java.awt.*;

public class MyButton {

    private int x, y, width, height, id;
    private String text;
    private Rectangle bounds;

    private boolean mouseOver, mousePressed;

    public MyButton(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = -1;

        initBounds();
    }
    //for tile buttons
    public MyButton(String text, int x, int y, int width, int height, int id) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;

        initBounds();
    }
    private void initBounds() {   //initializinu boundus rectangle, kad galeciau veliau tikrinti ar mouse clickas patenka i boundus
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        //body
        drawBody(g);
        //border
        drawRect(g);
        //text
        drawText(g);

    }

    private void drawBody(Graphics g) {
        if(mouseOver) {

            g.setColor(new Color(204, 188, 155));
        } else {
            g.setColor(new Color(0, 255, 128));

        }
        g.fillRect(x, y, width, height);
    }

    private void drawRect(Graphics g) {
        if(mouseOver) {
            g.setColor(Color.RED);
            g.drawRect(x, y ,width, height);
        } else {
            g.setColor(new Color(0, 204, 102));
            g.drawRect(x, y ,width, height);
        }
        if(mousePressed) {
            g.drawRect(x + 1, y + 1, width - 2, height - 2);
            g.drawRect(x + 2 , y + 2, width - 4, height - 4);
        }

    }

    private void drawText(Graphics g) {
        g.setColor(Color.darkGray);
        g.setFont(new Font("", Font.BOLD, 14));
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x - w/2 + width/2, y + h/2 + height / 2);
    }

    public void resetBooleans() {
        this.mousePressed = false;
        this.mouseOver = false;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    public boolean isMouseOver() {
        return this.mouseOver;
    }

    public boolean isMousePressed() {
        return this.mousePressed;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public int getId() {
        return id;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void setText(String text) {
        this.text = text;
    }
}
