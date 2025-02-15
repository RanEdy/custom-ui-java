import java.awt.EventQueue;

import Presentation.MainWindow;

public class Main {
    public static void main(String[] args) throws Exception
    {
        EventQueue.invokeLater(() -> new MainWindow());
    }
}
