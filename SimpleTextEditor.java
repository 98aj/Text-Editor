import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class SimpleTextEditor extends JFrame implements ActionListener{
    JFrame js;
    JTextArea textArea;
    SimpleTextEditor(){
        //Creating Object 
        js = new JFrame("Simple Text Area");

        textArea = new JTextArea();
        //Setting layout
        js.setSize(800, 800);
        js.setVisible(true);
        //adding Text Area
        js.add(textArea);

        //Creating JMenuBar, JMenu and JMenuItem
        JMenuBar menuBar = new JMenuBar();
        JMenu File = new JMenu("File");
        JMenu Edit = new JMenu("Edit");
        JMenuItem Save = new JMenuItem("Save");
        JMenuItem Open = new JMenuItem("Open File");
        JMenuItem Print = new JMenuItem("Print");
        JMenuItem NewFile = new JMenuItem("New File");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        JMenuItem close = new JMenuItem("Close");

        //Adding Menu and menuitem to menubar
        menuBar.add(File);
        menuBar.add(Edit);
        File.add(NewFile);
        File.add(Save);
        File.add(Open);
        File.add(Print);
        Edit.add(cut);
        Edit.add(copy);
        Edit.add(paste);
        Edit.add(close);
        //Adding Menubar to frame
        js.setJMenuBar(menuBar);
        js.show();

        //Adding Funcinility to Menu by using Action listener
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        Save.addActionListener(this);
        Print.addActionListener(this);
        Open.addActionListener(this);
        NewFile.addActionListener(this);
        close.addActionListener(this);
        js.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        SimpleTextEditor tec = new SimpleTextEditor();
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s=="Cut"){
            textArea.cut();
        }
        else if(s=="Copy"){
            textArea.copy();
        }
        else if(s=="Paste"){
            textArea.paste();
        }

        // To Save file 
        /*
         * File file = new File(chooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
                writer.write(textArea.getText());
                writer.flush();
                writer.close();
                and then just add try and catch block
         */
        else if(s=="Save"){
            JFileChooser chooser = new JFileChooser("C:");
            int ans = chooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION){
                File file = new File(chooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer=null;
                try {
                    writer = new BufferedWriter(new FileWriter(file, false));
                } catch (IOException e1) {
                   
                    e1.printStackTrace();
                }
                try {
                    writer.write(textArea.getText());
                } catch (IOException e1) {
                   
                    e1.printStackTrace();
                }
                try {
                    writer.flush();
                } catch (IOException e1) {
                   
                    e1.printStackTrace();
                }
                try {
                    writer.close();
                } catch (IOException e1) {
                   
                    e1.printStackTrace();
                }

            }

        }

        //print file
        else if(s=="Print"){
            try {
                textArea.print();
            } catch (PrinterException e1) {
                
                e1.printStackTrace();
            }
        }

        // Open File
        else if(s=="Open File"){
            JFileChooser choose = new JFileChooser("C:");
            int ans = choose.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION){
                File file = new File(choose.getSelectedFile().getAbsolutePath());
                
                try{
                    String s1="", s2="";
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                        s2 = bufferedReader.readLine();
                        while((s1=bufferedReader.readLine())!=null){
                            s2 += s1+"\n";
                        }
                    }
                    textArea.setText(s2);
                }catch(IOException e1){
                    e1.printStackTrace();
                }
            } 

        }

        //New File
        else if(s=="New File"){
            textArea.setText("");
        }

        //Close file
        else if(s=="Close"){
            js.setVisible(false);
        }

        
    }

}