/*
 * SpriteCreator.java
 *
 * Created on August 2, 2003, 2:36 AM
 */

package IsometricGame.Item;

import IsometricGame.*;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;

/**
 *
 * @author  Jason
 */
public class SpriteCreator extends javax.swing.JFrame implements ItemOperator {
    JDialog chooseAttributes;
    JCheckBox[] checkBoxes;
    JComboBox itemTypeBox;
    ObjectOutputStream dout;
    MediaTracker tracker;
    
    
    /** Creates new form SpriteCreator */
    public SpriteCreator() {
        tracker = new MediaTracker(this);
        initComponents();
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        fileName = new javax.swing.JTextField();
        browseFiles = new javax.swing.JButton();
        beginInput = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        spriteType = new javax.swing.JComboBox();

        getContentPane().setLayout(new java.awt.GridLayout(10, 0));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SpriteCreator");
        setForeground(java.awt.Color.lightGray);
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 400, 300));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jLabel1.setText("File Name:");
        getContentPane().add(jLabel1);

        getContentPane().add(fileName);

        browseFiles.setText("Browse");
        browseFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseFilesActionPerformed(evt);
            }
        });

        getContentPane().add(browseFiles);

        beginInput.setText("Start");
        beginInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beginInputActionPerformed(evt);
            }
        });

        getContentPane().add(beginInput);

        jLabel2.setText("Type:");
        getContentPane().add(jLabel2);

        spriteType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tile", "Item", "Doodad", "Avatar" }));
        getContentPane().add(spriteType);

        pack();
    }//GEN-END:initComponents
    
	private void beginInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beginInputActionPerformed
            try {
                // Add your handling code here:
                dout = new ObjectOutputStream(new FileOutputStream(new File(fileName.getText().substring(0,fileName.getText().length()-3)+"dat")));
                Image tempImage = Toolkit.getDefaultToolkit().getImage(fileName.getText());
                tracker.addImage(tempImage,0);
                tracker.waitForAll();
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice gs = ge.getDefaultScreenDevice();
                GraphicsConfiguration gc = gs.getDefaultConfiguration();
                BufferedImage tempBuffImage = gc.createCompatibleImage(tempImage.getWidth(null), tempImage.getHeight(null), Transparency.BITMASK);
                tempBuffImage.createGraphics().drawImage(tempImage,null,null);
                System.out.println(spriteType.getSelectedItem().toString());
                int width;
                
                if(((String)spriteType.getSelectedItem()).equalsIgnoreCase("Item")) {
                    itemTypeBox = new JComboBox(ItemOperator.itemCategoryNames);
                    itemTypeBox.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            changeItemCategory(evt.getActionCommand());
                        }
                    });
                    new ShowItemImage(this,true,tempImage,dout).show();
                    dout.writeByte(-1);
                    dout.close();
                    return;
                }
                
                width=Theme.tileWidth;
                
                for(int a=0;a<tempBuffImage.getWidth()/width;a++) {
                    chooseAttributes = new JDialog(this,true);
                    chooseAttributes.setSize(400,400);
                    chooseAttributes.setLocationRelativeTo(this);
                    chooseAttributes.getContentPane().setBackground(Color.WHITE);
                    chooseAttributes.repaint();
                    System.out.println(tempBuffImage.getSubimage(a*width, 0, width, tempBuffImage.getHeight()));
                    JLabel preview = new JLabel(new ImageIcon(tempBuffImage.getSubimage(a*width, 0, width, tempBuffImage.getHeight())));
                    chooseAttributes.getContentPane().add(preview);
                    System.out.println(chooseAttributes.getContentPane().getGraphics());
                    chooseAttributes.getContentPane().setLayout(new javax.swing.BoxLayout(chooseAttributes.getContentPane(), javax.swing.BoxLayout.Y_AXIS));
                    JButton finished = new JButton("Done");
                    
                    
                    if(((String)spriteType.getSelectedItem()).equalsIgnoreCase("Tile")) {
                        checkBoxes = new JCheckBox[2];
                        checkBoxes[0] = new JCheckBox("Passable");
                        checkBoxes[1] = new JCheckBox("SeeThrough");
                        checkBoxes[0].setSelected(true);
                    }
                    else if(((String)spriteType.getSelectedItem()).equalsIgnoreCase("Doodad")) {
                        checkBoxes = new JCheckBox[6];
                        checkBoxes[0] = new JCheckBox("Passable");
                        checkBoxes[1] = new JCheckBox("SeeThrough");
                        checkBoxes[2] = new JCheckBox("BulletProof");
                        checkBoxes[3] = new JCheckBox("Indestructable");
                        checkBoxes[4] = new JCheckBox("Climbable");
                        checkBoxes[5] = new JCheckBox("Fallable");
                    }
                    for(int c=0;c<checkBoxes.length;c++) {
                        checkBoxes[c].setVisible(true);
                        checkBoxes[c].setBackground(Color.WHITE);
                        chooseAttributes.getContentPane().add(checkBoxes[c]);
                    }
                    finished.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            saveSettings();
                        }
                    });
                    chooseAttributes.getContentPane().add(finished);
                    chooseAttributes.show();
                }
                dout.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            
	}//GEN-LAST:event_beginInputActionPerformed
        
        public void changeItemCategory(String command) {
            System.out.println(command);
            
        }
        
        public void saveItem() {
        }
        
        public void saveSettings() {
            try {
                int tempInt =0;
                for(int a=checkBoxes.length-1;a>=0;a--) {
                    tempInt = tempInt << 1;
                    if(checkBoxes[a].isSelected())
                        tempInt = tempInt | 1;
                }
                System.out.println("TEMPINT" + tempInt);
                dout.writeInt(tempInt);
                chooseAttributes.hide();
                chooseAttributes = null;
            }
            catch(Exception e) {
            }
        }
        
	private void browseFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseFilesActionPerformed
            // Add your handling code here:
            final JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
            
            int returnVal = fc.showOpenDialog(this);
            
            if(returnVal==0)
                fileName.setText(fc.getSelectedFile().getAbsolutePath());
	}//GEN-LAST:event_browseFilesActionPerformed
        
        /** Exit the Application */
	private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
            System.exit(0);
	}//GEN-LAST:event_exitForm
        
        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
            new SpriteCreator().show();
        }
        
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton beginInput;
    private javax.swing.JButton browseFiles;
    private javax.swing.JTextField fileName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox spriteType;
    // End of variables declaration//GEN-END:variables
    
}