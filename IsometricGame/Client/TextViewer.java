/*
 * InstructionDialog.java
 *
 * Created on August 24, 2003, 3:54 PM
 */

package IsometricGame.Client;

import java.io.*;

/**
 *
 * @author  jgauci
 */
public class TextViewer extends javax.swing.JDialog {
    
    /** Creates new form InstructionDialog */
    public TextViewer(java.awt.Frame parent, boolean modal,BufferedReader inputFile) {
        super(parent, modal);
        initComponents();
        try{
            
            while(inputFile.ready())
                fileOutput.append(inputFile.readLine()+'\n');
        }
        catch(Exception e) {
            fileOutput.setText(e.getMessage());
        }
        this.repaint();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        fileOutput = new javax.swing.JTextArea();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        fileOutput.setEditable(false);
        fileOutput.setMinimumSize(new java.awt.Dimension(64, 48));
        fileOutput.setPreferredSize(new java.awt.Dimension(640, 480));
        getContentPane().add(fileOutput, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        new TextViewer(new javax.swing.JFrame(), true,new BufferedReader(new FileReader("IsometricGame/instructions.txt"))).show();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea fileOutput;
    // End of variables declaration//GEN-END:variables
    
}
