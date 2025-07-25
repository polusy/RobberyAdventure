/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package adventure.Boundary.GUI;

import adventure.Boundary.ClientManager;
import adventure.Entity.types.RobberyAdventure;
import javax.swing.JOptionPane;

/**
 *
 * 
 * 
 * La classe swing rappresenta l'interfaccia grafica del menu del gioco.
 * 
 * @author Paolo
 */
public class MenuCommandGUI extends javax.swing.JDialog {
    
    /**
     * manager necessario per permettere di effettuare chiamate REST al server.
     */
    private ClientManager clientManager;
    
    /**
     * Descrizione di gioco di cui si vorranno (probabilmente) salvare i progressi.
     */
    private RobberyAdventure toBeSavedRobberyAdventure;
    
    /**
     * salvataggio dei progressi recuperato attraverso il metodo REST.
     * 
     */
    private RobberyAdventure rescuedRobberyAdventure = null;
    
    private boolean gameEnded = false;

    /**
     *
     * @return
     */
    public RobberyAdventure getRescuedRobberyAdventure() {
        return rescuedRobberyAdventure;
    }

    /**
     *
     * @return
     */
    public boolean isGameEnded() {
        return gameEnded;
    }
    
    
    
    

    /**
     * Creates new form MenuCommandGUI
     * @param clientManager 
     * @param robberyAdventure 
     * @param parent
     * @param modal
     */
    public MenuCommandGUI(ClientManager clientManager,RobberyAdventure robberyAdventure, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.clientManager = clientManager;
        this.toBeSavedRobberyAdventure = robberyAdventure;
    }
    
    /**
     *
     */
    public MenuCommandGUI(){};

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Salva progressi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Carica progressi");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Esci");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * @param evt 
     * 
     * Il metodo permette la visualizzazione della GUI dell'End command,
     * quindi l'interazione per permettere all'utente l'uscita dal gioco (tramite menu).
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        EndCommandGUI endCommandGUI = new EndCommandGUI(null, true);
        endCommandGUI.setVisible(true);
        
        if (endCommandGUI.isGameEnded())
        {
            gameEnded = true;
            dispose();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * 
     * @param evt 
     * 
     * @deprecated 
     * Il metodo dovrebbe completare il suo corpo, inserendo la chiamata alla SaveCommandGUI
     * per permettere il salvataggio del gioco.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(rootPane,"Funzionalità non implementata");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * 
     * @param evt 
     * 
     * @deprecated 
     * Il metodo dovrebbe completare il suo corpo, inserendo la chiamata alla RescueProgressGUI
     * per permettere il recupero del salvataggio di gioco specifico.
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JOptionPane.showMessageDialog(rootPane,"Funzionalità non implementata");
        
        /*
        if (rescueProgressGUI.getRobberyAdventure() != null){
            rescuedRobberyAdventure = rescueProgressGUI.getRobberyAdventure();
            JOptionPane.showMessageDialog(rootPane, "Caricamento del salvataggio effettuato...prego!");
            dispose();
        }
        */
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuCommandGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuCommandGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuCommandGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuCommandGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MenuCommandGUI dialog = new MenuCommandGUI();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration//GEN-END:variables
}
