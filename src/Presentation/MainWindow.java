package Presentation;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;

import Presentation.CustomComponents.UIPanel;

public class MainWindow extends JFrame
{
    private final int width = 800;
    private final int height = 500;

    private Point inicialMouse, inicialFrame;

    public MainWindow()
    {
        super("Test Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(width, height);
        setLocationRelativeTo(null); //Centrar la ventana

        //Convertir la ventana completamente invisible
        setUndecorated(true);
        setBackground(new Color(255, 255, 255, 0));

        UIPanel mainPanel = new UIPanel();
        mainPanel.setGradient(Color.decode("#00F260"), Color.decode("#0575E6"));
        mainPanel.setRoundedCorners(30);

        add(mainPanel, BorderLayout.CENTER);


        //Agregar la funcionalidad para que la ventana se pueda arrastrar con el mouse
        MouseAdapter dragFrame = new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                inicialMouse = e.getLocationOnScreen();
                inicialFrame = getLocation();
            }

            public void mouseDragged(MouseEvent e)
            {
                Point mousePos = e.getLocationOnScreen();
                
                //Calcular el desplazamiento
                int x = inicialFrame.x + mousePos.x - inicialMouse.x;
                int y = inicialFrame.y + mousePos.y - inicialMouse.y;

                setLocation(x, y);
            }
        };
        
        addMouseListener(dragFrame);
        addMouseMotionListener(dragFrame);

        setVisible(true);
    }

}
