import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class SimpleTextEditor extends JFrame implements ActionListener {
    JFrame frame;
    JTextArea textArea;
    SimpleTextEditor(){
        frame=new JFrame("Simple Text Editor");//created the frame
        textArea=new JTextArea();//created the textarea
        frame.add(textArea);//added the text area to the frame
        frame.setSize(800,800);//setting the size for the frame
        frame.setVisible(true);//setting the visual
        JMenuBar menuBar=new JMenuBar();//creating menubar
        JMenu FileMenu = new JMenu("File ");//created menu
        JMenu EditMenu = new JMenu("Edit ");//ceated another menu
        menuBar.add(FileMenu);//added both the menu to the menubar
        menuBar.add(EditMenu);
        JMenuItem Open = new JMenuItem("Open File");//created Menuitem for fileMenu
        JMenuItem Save = new JMenuItem("Save File");
        JMenuItem Print = new JMenuItem("Print File");
        JMenuItem New = new JMenuItem("New File");
        FileMenu.add(Open);//added all the menu items to FileMenu
        FileMenu.add(Save);
        FileMenu.add(Print);
        FileMenu.add(New);
        Save.addActionListener(this);
        Open.addActionListener(this);
        New.addActionListener(this);
        JMenuItem Cut = new JMenuItem("Cut");//creating Menuitem for Edit Menu
        JMenuItem Copy = new JMenuItem("Copy");
        JMenuItem Paste = new JMenuItem("Paste");
        JMenuItem Close = new JMenuItem("Close");
        EditMenu.add(Cut);//Added all the menuItem to EditMenu
        EditMenu.add(Copy);
        EditMenu.add(Paste);
        EditMenu.add(Close);
        //Created actionListener for three menuItems of EditMenu ---> Functionality are described in the @override actionPerformed function
        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        Close.addActionListener(this);
        frame.setJMenuBar(menuBar);//Adding the MenuBar to the Frame
        frame.show();//This will show the menubar



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SimpleTextEditor SimpleTextEditor=new SimpleTextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s=="Cut"){
            textArea.cut();//working of the actionListener of the EditMenu
        }else if(s=="Copy"){
            textArea.copy();
        } else if (s=="Paste") {
            textArea.paste();
        } else if(s=="Save File"){
            JFileChooser JFileChooser = new JFileChooser("E:\\Text Editor");
            int ans = JFileChooser.showOpenDialog(null);
            if(ans== javax.swing.JFileChooser.APPROVE_OPTION){
                File file = new File(JFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file,false));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.write(textArea.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (s=="Print File") {
            try {
                textArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        } else if (s=="Open File") {
            JFileChooser JFileChooser = new JFileChooser("E:\\Text Editor");
            int ans = JFileChooser.showOpenDialog(null);
            if(ans== javax.swing.JFileChooser.APPROVE_OPTION){
                File file = new File(JFileChooser.getSelectedFile().getAbsolutePath());
                try{
                    String s1="",s2="";
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2=bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null){
                        s2+=s1+"/n";
                    }
                    textArea.setText(s2);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        } else if (s=="New File") {
            textArea.setText("");
        } else if (s=="Close") {
            frame.setVisible(false);
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
