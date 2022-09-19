import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Notepad implements ActionListener {

    UIManager.LookAndFeelInfo[] looks;
    String title = "Untitled - Notepad";
    JFrame jf;
    JMenuBar menubar;
    JMenu file , edit;
    JMenuItem new_tv, open , save , save_as, exit , page_setup , prints;
    JMenuItem cut , copy , paste , replace , date_time;
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

        new_tv = new JMenuItem("New");
        new_tv.addActionListener(this);
        file.add(new_tv);

        open = new JMenuItem("Open");
        open.addActionListener(this);
        file.add(open);

        save = new JMenuItem("Save");
        save.addActionListener(this);
        file.add(save);

        save_as = new JMenuItem("Save as");
        save_as.addActionListener(this);
        file.add(save_as);
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

        // Edit menu
        edit = new JMenu("Edit");
        // Edit menu items
        cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        edit.add(cut);

        copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        edit.add(copy);

        paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        edit.add(paste);

        edit.addSeparator();

        replace = new JMenuItem("Replace");
        replace.addActionListener(this);
        edit.add(replace);
        edit.addSeparator();
        date_time = new JMenuItem("Date & Time");
        date_time.addActionListener(this);
        edit.add(date_time);
        // Menu items ...
        menubar.add(file);
        menubar.add(edit);
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
        if(e.getSource() == new_tv){
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
        if(e.getSource() == save_as){
            setSaveas();
        }
        if(e.getSource() == page_setup){
            pageSetup();
        }
        if(e.getSource() == prints){
            printPage();
        }
        if (e.getSource() == cut){
            text.cut();
        }
        if(e.getSource() == copy){
            text.copy();
        }
        if(e.getSource() == paste){
            text.paste();
        }
        if(e.getSource() == replace){
            replaceText();
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
    public void pageSetup(){
        PrinterJob pj = PrinterJob.getPrinterJob();
        PageFormat pf = pj.pageDialog(pj.defaultPage());
    }
    public void printPage(){
        PrinterJob pj = PrinterJob.getPrinterJob();
        if (pj.printDialog()) {
            try {
                pj.print();
            }
            catch (PrinterException exc) {
                System.out.println(exc);
            }
        }
    }
    public void  replaceText(){
        JFrame replacefram = new JFrame("Replace");
        replacefram.setSize(500,300);
        replacefram.setLayout(null);
        JLabel j11 = new JLabel("Find What ");
        j11.setBounds(50,50,80,40);
        replacefram.add(j11);

        JTextField jtf = new JTextField();
        jtf.setBounds(150 , 50 , 200 , 40);
        replacefram.add(jtf);

        JLabel j12 = new JLabel("Replace with : ");
        j12.setBounds(50,100,100,40);
        replacefram.add(j12);

        JTextField jtf2 = new JTextField();
        jtf2.setBounds(150 , 100 , 200 , 40);
        replacefram.add(jtf2);

        JButton jbtn = new JButton("Replace");
        jbtn.setBounds(200 , 150 , 100 , 40 );
        replacefram.add(jbtn);
        replacefram.setVisible(true);
    }
}

// UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");