package Presentation;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import Presentation.CustomComponents.UIFlatPanel;
import Presentation.CustomComponents.UICustomPainter;
import Presentation.CustomComponents.UIMenuPanel;
import Presentation.CustomComponents.UIUpperPanel;


public class MainWindow extends JFrame
{
    private final int width = 1200;
    private final int height = 700;




    public MainWindow()
    {
        
        //      Configuracion de la Ventana
        //=====================================================
        super("Test Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(width, height);
        setLocationRelativeTo(null); //Centrar la ventana

        //Convertir la ventana completamente invisible
        setUndecorated(true);
        setBackground(new Color(255, 255, 255, 0));

        //Agregar la funcionalidad para que la ventana se pueda arrastrar con el mouse
        MouseAdapter dragFrame = new MouseAdapter()
        {
            private Point inicialMouse, inicialFrame, inicialMouseLocal;

            public void mousePressed(MouseEvent e)
            {
                inicialMouse = e.getLocationOnScreen();
                inicialMouseLocal = e.getPoint();
                inicialFrame = getLocation();
            }

            public void mouseDragged(MouseEvent e)
            {
                //Verificar que el mouse se encuentre en la parte de arriba o abajo de la ventana
                boolean arriba = inicialMouseLocal.y > 0 && inicialMouseLocal.y < 40;
                boolean abajo = inicialMouseLocal.y > getHeight()-40 && inicialMouseLocal.y < getHeight();

                if (abajo || arriba)
                {
                    Point mousePos = e.getLocationOnScreen();
                
                    //Calcular el desplazamiento
                    int x = inicialFrame.x + mousePos.x - inicialMouse.x;
                    int y = inicialFrame.y + mousePos.y - inicialMouse.y;
    
                    setLocation(x, y);
                }
            }
        };
        
        addMouseListener(dragFrame);
        addMouseMotionListener(dragFrame);
        //=====================================================

        //Configuracion de los paneles
        ConfigPanels();

        setVisible(true);
    }

    private void ConfigPanels()
    {
        //      Configuracion de los Paneles
        //=====================================================
        int arcSize = 20;
        Color colorStart = new Color(50, 226, 152, 255);
        Color colorEnd = new Color(15, 52, 67, 255);

        //Panel el del fondo (blanco)
        UIFlatPanel mainPanel = new UIFlatPanel();
        Color mainPanelColor = new Color(240, 240, 240, 200);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setSize(getWidth(), getHeight());
        mainPanel.setBackground(mainPanelColor);
        mainPanel.setRoundedCorners(arcSize);

        //Panel superior (arriba) abarca 7% del alto de la ventana
        Dimension upperSize = new Dimension( getWidth(), (int)(getHeight()*0.07f));
        UIUpperPanel upper = new UIUpperPanel();
        upper.setBackground(colorStart);
        upper.setPreferredSize(upperSize);
        upper.setRoundedCorners(arcSize);

        //Panel menu (izquierda) abarca 15% del ancho de la ventana
        Dimension menuSize = new Dimension( (int)(getWidth()*0.15f), getHeight());
        UIMenuPanel menuPanel = new UIMenuPanel();
        menuPanel.setPreferredSize(menuSize);
        menuPanel.setGradient(colorStart, colorEnd, UICustomPainter.GRADIENT_VERTICAL);
        menuPanel.setRoundedCorners(arcSize);

        //Panel del contenido (derecha)
        Dimension contentPanelSize = new Dimension(getWidth() - menuPanel.getPreferredSize().width, getHeight() - upper.getPreferredSize().height);
        UIFlatPanel contentPanel = new UIFlatPanel();
        CardLayout cardLayout = new CardLayout();
        
        contentPanel.setLayout(null);
        contentPanel.setPreferredSize(contentPanelSize);
        contentPanel.setBackground(new Color(200,200,200,210));
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        mainPanel.add(upper, BorderLayout.PAGE_START);
        mainPanel.add(contentPanel, BorderLayout.LINE_END);
        mainPanel.add(menuPanel, BorderLayout.LINE_START);
        add(mainPanel, BorderLayout.CENTER);
        //=====================================================
    }

}
