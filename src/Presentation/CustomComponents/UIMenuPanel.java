package Presentation.CustomComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class UIMenuPanel extends JPanel
{
    private UICustomPainter customPainter;

    public UIMenuPanel()
    {
        super();
        customPainter = new UICustomPainter(this);
        setLayout(new MigLayout());
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
        Graphics2D G2D = (Graphics2D) g;
        G2D.fillRect(0, 0, getWidth(), 8);
        G2D.fillRect(getWidth()-8, 0, 8, getHeight());
    }
}
