package Presentation.CustomComponents;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class UIPanel extends JPanel
{
    private int arcSize = 0; //tamaño en pixeles de los bordes redondeados
    private boolean hasGradient = false;
    private Color gradientColor1, gradientColor2;

    public UIPanel()
    {
        super();
        setOpaque(false);
    }

    public void setGradient(Color color1, Color color2)
    {
        hasGradient = true;
        gradientColor1 = color1;
        gradientColor2 = color2;
    }

    public void setRoundedCorners(int arcSize) { this.arcSize = arcSize; }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D G2D = (Graphics2D)g; //Obtener un contexto de graficos en 2D basado en el parametro pasado
        System.out.println("Arc: " + arcSize);

        
        //          Renderizado Personalizado
        /*====================================================*/

        G2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //Activar el antialiasing

        //Colorear el fondo (gradiente o solido)
        if (hasGradient)
        {
            GradientPaint gradient = new GradientPaint(
                0,0,
                gradientColor1,
                0, getHeight(),
                gradientColor2
            );
            G2D.setPaint(gradient);
        }
        else
        {
            G2D.setColor(getBackground());
        }
        G2D.fillRoundRect(0, 0, getWidth(), getHeight(), arcSize, arcSize); //Dibujar un rectangulo con los bordes redondeados

        /*====================================================*/
        


        super.paintComponent(g); //utilizar el metodo de pintar de la clase padre

    }


}