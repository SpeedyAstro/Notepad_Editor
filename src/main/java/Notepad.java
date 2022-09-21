import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;

public class Notepad implements ActionListener {
    // Implementation
    UIManager.LookAndFeelInfo[] looks;
    String title = "Untitled - Notepad";
    JFrame jf , font_frame;
    JMenuBar menu_bar;
    JMenu file , edit , format , help;
    JMenuItem new_tv, open , save , save_as, exit , page_setup , prints;
    JMenuItem cut , copy , paste , replace , date_time;
    JMenuItem font_menu , font_color , workspace_color, info;
    JCheckBoxMenuItem word_wrap;
    JTextArea text;
    File file_e;
    JButton jbtn , selected;
    JFrame replaceframe;
    JTextField jtf , jtf2;
    JComboBox font_family,font_style,font_size;
    Notepad(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
             }
        catch(Exception e){ e.printStackTrace();}
        // Creating Frame --
        jf = new JFrame(title);
        jf.setSize(500,500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\pande\\Documents\\NotepadEditor\\src\\main\\java\\skull1.png");
        jf.setIconImage(icon);
        menu_bar = new JMenuBar();
        file = new JMenu("File");

        new_tv = new JMenuItem("New");
        new_tv.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK));
        new_tv.addActionListener(this);
        file.add(new_tv);

        open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke('O' , CTRL_DOWN_MASK));
        open.addActionListener(this);
        file.add(open);

        save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
        save.addActionListener(this);
        file.add(save);

        save_as = new JMenuItem("Save as");
        save_as.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.SHIFT_DOWN_MASK));
        save_as.addActionListener(this);
        file.add(save_as);
        // Added separator
        file.addSeparator();

        page_setup = new JMenuItem("Page Setup");
        page_setup.addActionListener(this);
        file.add(page_setup);
        prints = new JMenuItem("Print");
        prints.setAccelerator(KeyStroke.getKeyStroke('P', CTRL_DOWN_MASK));
        prints.addActionListener(this);
        file.add(prints);

        file.addSeparator();

        exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke('E', CTRL_DOWN_MASK));
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
        menu_bar.add(file);
        menu_bar.add(edit);
        jf.setJMenuBar(menu_bar);

        format = new JMenu("Format");
        menu_bar.add(format);
        word_wrap = new JCheckBoxMenuItem("Word Wrap") ;
        word_wrap.addActionListener(this);
        format.add(word_wrap);
        format.addSeparator();
        font_menu = new JMenuItem("Font");
        font_menu.addActionListener(this);
        format.add(font_menu);
        format.addSeparator();
        font_color = new JMenuItem("Font Color");
        font_color.addActionListener(this);
        format.add(font_color);

        workspace_color = new JMenuItem("WorkSpace Editor");
        workspace_color.addActionListener(this);
        format.add(workspace_color);

        help = new JMenu("Help");
        help.addActionListener(this);
        menu_bar.add(help);
        info = new JMenuItem("Info");
        info.addActionListener(this);
        help.add(info);





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
        if(e.getSource() == jbtn){
            replace();
        }
        if (e.getSource() == date_time){
            setDateTime();
        }
        if(e.getSource() == word_wrap){
            boolean b =word_wrap.getState();
            text.setLineWrap(b);
        }
        if(e.getSource() == font_color){
            setFontColor();
        }
        if(e.getSource() == workspace_color){
            WorkSpaceColor();
        }
        if(e.getSource() == font_menu){
            openFontFrame();
        }
        if(e.getSource() == selected){
            setFont();
        }
        if (e.getSource() == info){
            System.out.println("hep");
            setHelp();
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
                file_e = fileChooser.getSelectedFile();
                FileOutputStream fos = new FileOutputStream(file_e);
                fos.write(b);
                fos.close();
                setTitle(file_e.getName());
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
                file_e = fileChooser.getSelectedFile();
                FileInputStream fos = new FileInputStream(file_e);
                int i;
                while ((i=fos.read())!= -1){
                    // TODO: read file to textarea
                    text.append(String.valueOf((char)i));
                    setTitle(file_e.getName());
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
                FileOutputStream fos = new FileOutputStream(file_e);
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
        replaceframe = new JFrame("Replace");
        replaceframe.setSize(500,300);
        replaceframe.setLayout(null);
        JLabel j11 = new JLabel("Find What ");
        j11.setBounds(50,50,80,40);
        replaceframe.add(j11);

        jtf = new JTextField();
        jtf.setBounds(150 , 50 , 200 , 40);
        replaceframe.add(jtf);

        JLabel j12 = new JLabel("Replace with : ");
        j12.setBounds(50,100,100,40);
        replaceframe.add(j12);

        jtf2 = new JTextField();
        jtf2.setBounds(150 , 100 , 200 , 40);
        replaceframe.add(jtf2);

        jbtn = new JButton("Replace");
        jbtn.addActionListener(this);
        jbtn.setBounds(200 , 150 , 100 , 40 );
        replaceframe.add(jbtn);
        replaceframe.setVisible(true);
    }
    public void replace(){
        String find_what = jtf.getText();
        String replace_with = jtf2.getText();
        String update = text.getText();
        String new_text = update.replace(find_what,replace_with);
        text.setText(new_text);
        replaceframe.setVisible(false);
    }
    public void setDateTime(){
        LocalDateTime ldt = LocalDateTime.now();
        String s = ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        text.append(s);
    }
    public void setFontColor(){
        Color c = JColorChooser.showDialog(jf , "Select Font Color" , Color.BLACK);
        text.setForeground(c);
    }
    public void WorkSpaceColor(){
        Color c = JColorChooser.showDialog(jf , "Select WorkSpace Color" , Color.white);
        text.setBackground(c);
    }
    public void openFontFrame(){
        font_frame = new JFrame("Fonts");
        font_frame.setSize(700,300);
        font_frame.setLayout(null);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String []fontFamilies = ge.getAvailableFontFamilyNames();
        font_family = new JComboBox(fontFamilies);
        font_family.setBounds(50,50,200,40);
        font_frame.add(font_family);
        String [] font_style_list = {"Plain","Bold","Italic"};
        font_style = new JComboBox(font_style_list);
        font_style.setBounds(300,50,100,40);
        font_frame.add(font_style);
        Integer [] font_size_list = {10,15,20,21,23,26,30,35,40,45,50,55,60};
        font_size = new JComboBox(font_size_list);
        font_size.setBounds(450,50,80,40);
        font_frame.add(font_size);
        selected = new JButton("Select");
        selected.setBounds(200,150,80,50);
        selected.addActionListener(this);
        font_frame.add(selected);
        font_frame.setVisible(true);
    }
    public void setFont(){
        String selected_font_family =(String) font_family.getSelectedItem();
        Integer selected_font_size = (Integer) font_size.getSelectedItem();
        String selected_font_style = (String) font_style.getSelectedItem();
        int font_setter = 0;
        if(selected_font_style.equals("Plain")) font_setter=Font.PLAIN;
        else if(selected_font_style.equals("Bold")) font_setter=Font.BOLD;
        else if(selected_font_style.equals("Italic")) font_setter=Font.ITALIC;
        Font font = new Font(selected_font_family,font_setter,selected_font_size);
        text.setFont(font);
        font_frame.setVisible(false);
    }
    public void setHelp(){
        JFrame help_menu = new JFrame("Help");
        help_menu.setSize(500,400);
        help_menu.setLayout(null);
        JLabel label1 = new JLabel(new ImageIcon("about.gif"));
        //adding label1 to the JPanel
        help_menu.add(label1);
        JLabel jlt = new JLabel("Credits: \n Anubhav Pandey  ");
        jlt.setBounds(50,100,300,100);
        help_menu.add(jlt);
        help_menu.setVisible(true);

    }
}

// UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");