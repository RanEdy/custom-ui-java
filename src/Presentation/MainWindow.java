package Presentation;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import Presentation.CustomComponents.UIFlatPanel;
import Presentation.CustomComponents.UICustomPainter;
import Presentation.CustomComponents.UIFlatButton;
import Presentation.CustomComponents.UIMenuPanel;
import Presentation.CustomComponents.UIUpperPanel;
import net.miginfocom.swing.MigLayout;


public class MainWindow extends JFrame
{
    private final int width = 1200;
    private final int height = 700;

    //Variables de Debug
    private boolean debugFlag = false;
    private String debugLayout = "debug,";

    //Componentes
    MigLayout rootLayout;
    UIFlatPanel mainPanel;
    UIUpperPanel upper;
    UIMenuPanel menuPanel;
    UIFlatPanel contentPanel;

    public MainWindow()
    {
        super("Test Window");

        ConfigWindow();
        ConfigPanels();

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
    }

    private void ConfigWindow()
    {
                
        //      Configuracion de la Ventana
        //=====================================================
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(width, height);
        setLocationRelativeTo(null); //Centrar la ventana

        //Convertir la ventana completamente invisible
        if (!debugFlag)
        {
            setUndecorated(true);
            setBackground(new Color(255, 255, 255, 0));
            debugLayout = "";
        }
        
    }

    private void ConfigPanels()
    {
        //      Configuracion de los Paneles
        //=====================================================
        int arcSize = 20;
        Color colorStart = new Color(50, 226, 152, 255);
        Color colorEnd = new Color(15, 52, 67, 255);

        //Panel raiz (el del fondo)
        Color mainPanelColor = new Color(240, 240, 240, 200);
        rootLayout = new MigLayout(debugLayout + "insets 10", "[]0[]", "[]0[]");
        mainPanel = new UIFlatPanel();
        mainPanel.setLayout(rootLayout);
        mainPanel.setSize(getWidth(), getHeight());
        mainPanel.setBackground(mainPanelColor);
        mainPanel.setRoundedCorners(arcSize);

        //Panel superior (arriba) abarca 15% del alto de la ventana
        Dimension upperSize = new Dimension( getWidth(), (int) Math.clamp(getHeight()*0.15f, 100, 200));
        upper = new UIUpperPanel();
        upper.setBackground(colorStart);
        upper.setPreferredSize(upperSize);
        upper.setRoundedCorners(arcSize);

        //Panel menu (izquierda) abarca 15% del ancho de la ventana (min: 50px, max: 250px)
        Dimension menuSize = new Dimension((int) Math.clamp(getWidth()*0.15f, 100, 250), getHeight());
        menuPanel = new UIMenuPanel();
        menuPanel.setPreferredSize(menuSize);
        menuPanel.setGradient(colorStart, colorEnd, UICustomPainter.GRADIENT_VERTICAL);
        menuPanel.setRoundedCorners(arcSize);

        //Panel del contenido (derecha)
        Dimension contentPanelSize = new Dimension(getWidth() - menuPanel.getPreferredSize().width, getHeight() - upper.getPreferredSize().height);
        contentPanel = new UIFlatPanel();
        contentPanel.setLayout(null);
        contentPanel.setPreferredSize(contentPanelSize);
        contentPanel.setBackground(new Color(100, 100, 100, 200));
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        //Boton de prueba
        UIFlatButton boton = new UIFlatButton();
        boton.setText("Hola boton");
        boton.setForeground(Color.WHITE);
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        boton.setSize(200, 50);
        boton.setPreferredSize(new Dimension(200, 50));
        boton.setLocation(100, 200);
        boton.setBackground(new Color(100, 100, 100, 0));
        boton.setRoundedCorners(30);
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                System.out.println("Click");
            }
        });

        contentPanel.add(boton);

        //Configuracion del layout de los componentes
        mainPanel.add(upper, "wrap, span 2");
        mainPanel.add(menuPanel);
        mainPanel.add(contentPanel);
    
        add(mainPanel, BorderLayout.CENTER);
        //=====================================================
    }

}
