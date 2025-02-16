package Presentation.CustomComponents;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;


//TODO:
// - Colores de Hover personalizados?

public class UIFlatButton extends JLabel
{
    private UICustomPainter customPainter;


    //Variables para el Hover
    private boolean isInHover = false;

    public UIFlatButton()
    {
        super();
        customPainter = new UICustomPainter(this);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                isInHover = true;
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                isInHover = false;
                repaint();
            }
        });
    }

    public void setBackground(Color color)
    {
        super.setBackground(color);
    }

    public void setCustomBorder(Color color, int thickness)
    {
        customPainter.setCustomBorder(color, thickness);
    }

    public void setGradient(Color color1, Color color2, int gradientType)
    {
        customPainter.setGradient(color1, color2, gradientType);
    }

    public void setRoundedCorners(int arcSize) { customPainter.setRoundedCorners(arcSize); }

    @Override
    protected void paintComponent(Graphics g)
    {
        customPainter.customPaint(g);
        super.paintComponent(g);

        Graphics2D G2D = (Graphics2D)g;
        G2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (isInHover)
        {
            G2D.setColor(new Color(255, 255, 255, 30));
            G2D.fillRoundRect(0, 0, customPainter.getRealWidth() , customPainter.getRealHeight(), customPainter.getArcSize(), customPainter.getArcSize());
        }
    }
    
}
