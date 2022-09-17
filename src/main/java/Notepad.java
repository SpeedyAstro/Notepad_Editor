import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Notepad implements ActionListener {

    UIManager.LookAndFeelInfo[] looks;
    String title = "Untitled - Notepad";
    JFrame jf;
    JMenuBar menubar;
    JMenu file;
    JMenuItem neww, open , save , saveas , exit , page_setup , prints;
    JTextArea text;
    File filee;
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
        neww.addActionListener(this);
        file.add(neww);

        open = new JMenuItem("Open");
        open.addActionListener(this);
        file.add(open);

        save = new JMenuItem("Save");
        save.addActionListener(this);
        file.add(save);

        saveas = new JMenuItem("Save as");
        saveas.addActionListener(this);
        file.add(saveas);
        // Added separator
        file.addSeparator();

        page_setup = new JMenuItem("Page Setup");
        page_setup.addActionListener(this);
        file.add(page_setup);
        prints = new JMenuItem("Print");
        prints.addActionListener(this);
        file.add(prints);

        file.addSeparator();

        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == neww){
            newNotepad();
        }
        if(e.getSource() == exit){
            System.exit(0);
        }
        if(e.getSource() == save){
            setSave();
        }
    }
    public void newNotepad(){
        text.setText("");
    }
    public void setSave()  {
        JFileChooser fileChooser = new JFileChooser();
        int i = fileChooser.showSaveDialog(jf);
        if(i!=0) JOptionPane.showMessageDialog(fileChooser,"File not Saved!" , "👋📁" , JOptionPane.WARNING_MESSAGE);
        else {
            try {
                String data = text.getText();
                byte[] b = data.getBytes();
                filee = fileChooser.getSelectedFile();
                FileOutputStream fos = new FileOutputStream(filee);
                fos.write(b);
                fos.close();
                setTitle(filee.getName());
            }
            catch (Exception e) { e.printStackTrace();}
        }
    }
    public void setTitle(String title){
        jf.setTitle(title);
    }
}

// UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");