package br.com.grupocampanha.canhoto;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jfc
 */
public class Main {
    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        if (args.length >0  && args[0].equals("--calibrar"))
            new Calibragem().setVisible(true);
        else if (Log.in()){
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            EventQueue.invokeLater(() -> {
                
                new Principal().setVisible(true);
            });
        }
        else
            System.exit(0);
    }
    
}
