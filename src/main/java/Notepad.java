import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
public class Notepad {

    UIManager.LookAndFeelInfo[] looks;
    String title = "Untitled - Notepad";
    JFrame jf;
    JMenuBar menubar;
    JMenu file;
    JMenuItem neww, open , save , saveas , exit;
    JTextArea text;
    Notepad(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
             }
        catch(Exception e){ e.printStackTrace();}
        // Creating Frame --
        jf = new JFrame(title);
        jf.setSize(500,500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menubar = new JMenuBar();
        file = new JMenu("File");

        neww = new JMenuItem("New");
        file.add(neww);

        open = new JMenuItem("Open");
        file.add(open);

        save = new JMenuItem("Save");
        file.add(save);

        saveas = new JMenuItem("Save as");
        file.add(saveas);
        // Added separator
        file.addSeparator();

        exit = new JMenuItem("Exit");
        file.add(exit);
        menubar.add(file);
        jf.setJMenuBar(menubar);

        text = new JTextArea();
        // adding scroll pane
        JScrollPane scrollpane = new JScrollPane(text);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jf.add(scrollpane);


        jf.setVisible(true);
        //
    }
}

// UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");