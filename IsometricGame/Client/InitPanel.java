/*
 * InitPanel.java
 *
 * Created on August 27, 2003, 3:20 PM
 */

package IsometricGame.Client;

import IsometricGame.Net.*;

import javax.swing.*;
import java.sql.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author  jgauci
 */
public class InitPanel extends javax.swing.JPanel {
   Loader parent;
   
   /** Creates new form InitPanel */
   public InitPanel(Loader parent) {
      this.parent = parent;
      initComponents();
      if(parent instanceof JApplet) {
	 serverField.setVisible(false);
	 jLabel3.setVisible(false);
	 serverField.setSelectedItem( ((JApplet)parent).getCodeBase().getHost() );
      }
   }
   
   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   private void initComponents() {//GEN-BEGIN:initComponents
      jLabel1 = new javax.swing.JLabel();
      usernameField = new javax.swing.JTextField();
      jLabel2 = new javax.swing.JLabel();
      passwordField = new javax.swing.JPasswordField();
      jLabel3 = new javax.swing.JLabel();
      serverField = new javax.swing.JComboBox();
      goButton = new javax.swing.JButton();
      errorDisplay = new javax.swing.JTextArea();

      setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

      setAlignmentX(0.0F);
      setAlignmentY(0.0F);
      jLabel1.setText("Enter your User Name");
      jLabel1.setAlignmentX(0.5F);
      jLabel1.setAlignmentY(0.0F);
      jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
      add(jLabel1);

      usernameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
      usernameField.setAlignmentY(0.0F);
      usernameField.setMaximumSize(new java.awt.Dimension(100, 20));
      usernameField.setPreferredSize(new java.awt.Dimension(400, 20));
      add(usernameField);

      jLabel2.setText("Enter Your Password");
      jLabel2.setAlignmentX(0.5F);
      jLabel2.setAlignmentY(0.0F);
      add(jLabel2);

      passwordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
      passwordField.setAlignmentY(0.0F);
      passwordField.setMaximumSize(new java.awt.Dimension(100, 20));
      passwordField.setPreferredSize(new java.awt.Dimension(100, 20));
      add(passwordField);

      jLabel3.setText("Enter the hostname/address of the server");
      jLabel3.setAlignmentX(0.5F);
      jLabel3.setAlignmentY(0.0F);
      add(jLabel3);

      serverField.setEditable(true);
      serverField.setMaximumRowCount(99);
      serverField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "localhost", "ucfpawn.homelinux.com" }));
      serverField.setAlignmentY(0.0F);
      serverField.setMaximumSize(new java.awt.Dimension(200, 20));
      serverField.setName("server");
      add(serverField);

      goButton.setText("Connect to C.O.R.E. Warrior Online");
      goButton.setAlignmentX(0.5F);
      goButton.setAlignmentY(0.0F);
      goButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
      goButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            goButtonActionPerformed(evt);
         }
      });

      add(goButton);

      errorDisplay.setBackground(new java.awt.Color(204, 204, 204));
      errorDisplay.setFont(new java.awt.Font("Arial", 1, 14));
      errorDisplay.setLineWrap(true);
      errorDisplay.setWrapStyleWord(true);
      add(errorDisplay);

   }//GEN-END:initComponents
   
    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
       try {
	  errorDisplay.setText("Connecting to Server, please wait...");
	  this.repaint();
	  
	  // Initiate the connection
	  Socket mySocket = new Socket( (String)serverField.getSelectedItem(), 7766 );
	  
	  // We got a connection!  Tell the world
	  System.out.println( "connected to "+mySocket );
	  
	  // Let's grab the streams and create DataInput/Output streams
	  // from them
	  ObjectInputStream din = new ObjectInputStream( mySocket.getInputStream() );
	  ImprovedObjectOutputStream dout = new ImprovedObjectOutputStream( mySocket.getOutputStream() );
	  
	  dout.reset();
	  dout.writeUTF(usernameField.getText());
	  System.out.println("Wrote username");
	  dout.writeUTF(new String(passwordField.getPassword()));
	  System.out.println("Wrote password");
	  
	  byte status = din.readByte();
	  System.out.println("got status");
	  switch(status) {
	     case PacketOperator.LOGIN_FAILED:
		errorDisplay.setText("Login Failed.");
		break;
	     case PacketOperator.LOGIN_PLAYER:
		new PlayerFrame(parent,din,dout);
		break;
	     case PacketOperator.LOGIN_BUILDER:
		new BuilderFrame(parent,din,dout);
		break;
	  }
	  return;
       }
       catch(Exception e) {
	  errorDisplay.setText(e.getMessage());
	  e.printStackTrace();
       }
       
    }//GEN-LAST:event_goButtonActionPerformed
    
    
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JTextArea errorDisplay;
   private javax.swing.JButton goButton;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JPasswordField passwordField;
   private javax.swing.JComboBox serverField;
   private javax.swing.JTextField usernameField;
   // End of variables declaration//GEN-END:variables
   
}