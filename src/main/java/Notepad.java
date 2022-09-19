import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
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
        if(e.getSource() == open){
            openFile();
        }
        if(e.getSource() == saveas){
            setSaveas();
        }
    }
    public void newNotepad(){
        String texting = text.getText();
        if(!texting.equals("")){
            int i  = JOptionPane.showConfirmDialog(jf,"Do you want to save this file");
            if(i==0){ setSaveas(); setTitle(title); text.setText("");} else if (i == 1) {
                text.setText("");}
            else {text.setText(texting);}
            System.out.println(i);
        }
        text.setText("");
    }
    public void setSaveas()  {
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
    public void openFile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(jf);
        text.setText("");
        if(result!=0) JOptionPane.showMessageDialog(fileChooser,"File not Selected!" , "👋📁" , JOptionPane.WARNING_MESSAGE);
        else {
            try{
                filee = fileChooser.getSelectedFile();
                FileInputStream fos = new FileInputStream(filee);
                int i;
                while ((i=fos.read())!= -1){
                    // TODO: read file to textarea
                    text.append(String.valueOf((char)i));
                    setTitle(filee.getName());
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public void setSave(){
        if(jf.getTitle().equals(title)){
            setSaveas();
        }else{
            try{
                String updatedText = text.getText();
                byte [] b = updatedText.getBytes();
                FileOutputStream fos = new FileOutputStream(filee);
                fos.write(b);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

// UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");