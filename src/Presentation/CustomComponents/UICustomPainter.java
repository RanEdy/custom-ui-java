package Presentation.CustomComponents;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.RenderingHints;

//TODO:
// - Agregar Sombras Personalizadas

public class UICustomPainter
{
    private JComponent component; //el componente donde se aplicaran los cambios de estilo

    private int arcSize = 0; //tamaño en pixeles de los bordes redondeados

    //Variables para el borde
    private boolean hasBorder = false;
    private int borderThickness = 0;
    private Color borderColor = Color.BLACK;

    //Variables para controlar el gradiente
    public static final int GRADIENT_NO_CHANGE = -1, GRADIENT_VERTICAL = 0, GRADIENT_HORIZONTAL = 1, GRADIENT_DIAGONAL = 2; //todas las opciones para la direccion del gradiente
    private Point gradientStart = new Point(0,0), gradientEnd = new Point(0,0);
    private boolean hasGradient = false;
    private Color gradientColor1 = Color.WHITE, gradientColor2 = Color.WHITE;


    public UICustomPainter(JComponent component)
    {
        if(component == null)
        {
            new JOptionPane(
                "Componente NULO: No se puede aplicar el estilo a este componente",
                JOptionPane.ERROR_MESSAGE,
                JOptionPane.OK_OPTION
            );
        }
        else
        {
            this.component = component;
            this.component.setOpaque(false);
        }
    }

    public void setGradient(Color color1, Color color2, int gradientType)
    {
        hasGradient = true;
        gradientColor1 = color1;
        gradientColor2 = color2;
        
        switch(gradientType)
        {
            case GRADIENT_VERTICAL:
                gradientStart = new Point(0,0);
                gradientEnd = new Point(0, getRealHeight());
                break;
            case GRADIENT_HORIZONTAL:
                gradientStart = new Point(0,0);
                gradientEnd = new Point(getRealWidth(), 0);
                break;
            case GRADIENT_DIAGONAL:
                gradientStart = new Point(0,0);
                gradientEnd = new Point(getRealWidth(), getRealHeight());
                break;
            case GRADIENT_NO_CHANGE:
                break;

        }
    }

    public Color[] getGradientColors() { return new Color[] {gradientColor1, gradientColor2}; }
    
    public boolean hasGradient() { return hasGradient; };

    public void setRoundedCorners(int arcSize) { this.arcSize = arcSize; }
    
    public int getArcSize() { return arcSize; }

    public void setCustomBorder(Color color, int thickness)
    {
        hasBorder = true;
        borderColor = color;
        borderThickness = thickness;
    }

    public int getBorderThickness() { return borderThickness; }

    public void customPaint(Graphics g)
    {
        Graphics2D G2D = (Graphics2D)g; //Obtener un contexto de graficos en 2D basado en el parametro pasado
        
        //          Renderizado Personalizado
        /*====================================================*/

        G2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //Activar el antialiasing

        //Colorear el border
        if (hasBorder)
        {
            G2D.setColor(borderColor);
            G2D.fillRoundRect(0, 0, getRealWidth(), getRealHeight(), arcSize, arcSize);
        }

        //Colorear el fondo
        //Gradiente
        if (hasGradient)
        {
            GradientPaint gradient = new GradientPaint(
                gradientStart,
                gradientColor1,
                gradientEnd,
                gradientColor2
            );
            G2D.setPaint(gradient);
        }
        //O solido
        else
        {
            G2D.setColor(component.getBackground());
        }
        G2D.fillRoundRect(borderThickness, borderThickness, getRealWidth() - 2*borderThickness, getRealHeight() - 2*borderThickness, arcSize, arcSize); //Dibujar un rectangulo con los bordes redondeados

        /*====================================================*/
    }

    public int getRealWidth()
    {
        return Math.max(0, Math.max(component.getWidth(), component.getPreferredSize().width));
    }

    public int getRealHeight()
    {
        return Math.max(0, Math.max(component.getHeight(), component.getPreferredSize().height));
    }
}
