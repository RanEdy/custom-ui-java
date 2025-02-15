package Presentation.CustomComponents;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLayeredPane;

public class UIFlatPanel extends JLayeredPane
{
    private UICustomPainter customPainter;

    public UIFlatPanel()
    {
        super();
        customPainter = new UICustomPainter(this);
    }

    public void setGradient(Color color1, Color color2, int gradientType)
    {
        customPainter.setGradient(color1, color2, gradientType);
    }

    public void setRoundedCorners(int arcSize) { customPainter.setRoundedCorners(arcSize); }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g); //utilizar el metodo de pintar de la clase padre
        customPainter.customPaint(g);
    }
}