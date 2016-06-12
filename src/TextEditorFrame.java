// Created by Archit Garg on 6/8/2016.


import javax.swing.*;
import javax.swing.UIManager.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextEditorFrame extends JFrame{
      private JButton newButton;
      private JButton saveButton;
      private JButton openButton;
      private JButton quitButton;
      private JTextField statusField;
      private JTextArea textArea;
      private JPanel mainPanel;

      public TextEditorFrame() {
            super("Archit's Text Editor..!");
            try{
                  for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                        if("Nimbus".equals(info.getName())){
                              UIManager.setLookAndFeel(info.getClassName());
                              break;
                        }
                  }
            }catch(Exception e){
                  System.out.println("Failed To lOAd nimbus Look and feel.....!");
            }
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setContentPane(this.mainPanel);
            this.setSize(new Dimension(900,700));

            newButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                        textArea.setText("");
                        statusField.setText("New File...!");
                  }
            });

            saveButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                        JFileChooser chooser = new JFileChooser();
                        int chooserValue = chooser.showSaveDialog(null);                   //Hello,.....!
                        if (chooserValue == JFileChooser.APPROVE_OPTION){
                              try {
                                    PrintWriter fout = new PrintWriter(chooser.getSelectedFile());
                                    fout.print(textArea.getText());
                                    fout.close();
                                    statusField.setText("Saved at " + chooser.getSelectedFile().getAbsolutePath() + " ...!");
                              } catch (FileNotFoundException e1) {
                                    e1.printStackTrace();
                              }
                        }
                  }
            });

            quitButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                  }
            });

            openButton.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                        JFileChooser chooser = new JFileChooser();
                        int chooserValue = chooser.showOpenDialog(null);             // Hello..>!
                        if (chooserValue == JFileChooser.APPROVE_OPTION){
                              try {
                                    Scanner fin = new Scanner (chooser.getSelectedFile());
                                    String buffer = "";

                                    while(fin.hasNext()) {
                                          buffer += fin.nextLine() + " \n " ;
                                    }
                                    fin.close();
                                    textArea.setText(buffer);
                                    statusField.setText("File named " + chooser.getSelectedFile().getName() +" Opened Sucessfully...!");
                              } catch (FileNotFoundException e1) {
                                    e1.printStackTrace();
                                    JOptionPane.showMessageDialog(null,"File Not Found...!");               //hello..@!
                              }
                        }
                  }
            });
      }
}
