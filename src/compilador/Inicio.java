/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import jdk.nashorn.internal.parser.Lexer;

/**
 *
 * @author Mario
 */
public class Inicio extends javax.swing.JFrame {
    
    /**
     * Creates new form Inicio
     */
    ArrayList<String> lista_resultado = new ArrayList<>();
    public Inicio() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_tokens = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Analizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tabla_tokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "Lexema", "Línea", "Columna"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabla_tokens);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     String ruta = "";
        JFileChooser fileChooser = new JFileChooser();
fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
int result = fileChooser.showOpenDialog(this);
if (result == JFileChooser.APPROVE_OPTION) {
    File selectedFile = fileChooser.getSelectedFile();
    ruta = selectedFile.getAbsolutePath();
    
}
        
        try {
            analizador_lex(ruta);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
JFileChooser fileChooser_save = new JFileChooser();
fileChooser_save.setCurrentDirectory(new File(System.getProperty("user.home")));
int result_save = fileChooser_save.showSaveDialog(this);
        if (result_save == JFileChooser.APPROVE_OPTION) {
    File selectedFile = fileChooser_save.getSelectedFile();
     
            try {

    FileWriter escritor = new FileWriter(selectedFile);
    BufferedWriter escritor2 = new BufferedWriter(escritor);
    escritor2.write("TOKEN        LEXEMA          LINEA        COLUMNA");
    escritor2.newLine();
                for (int i = 0; i < tabla_tokens.getRowCount(); i++) {
                    int size_id;
                    int equilibrio1 = 0;
                    int equilibrio2 = 10;
                    for (int j = 0; j < tabla_tokens.getColumnCount(); j++) {
                        if (j==0) {
                            size_id = tabla_tokens.getValueAt(i, j).toString().length();
                            equilibrio1 = size_id - 2;
                            equilibrio2 = equilibrio2 - equilibrio1;
                        }
                       
                        if (j!=0) {
                            for (int k = 0; k <= equilibrio2; k++) {
                            escritor2.write(" ");
                        }  
                        }
                      
                        escritor2.write(tabla_tokens.getValueAt(i, j).toString());
                        //escritor2.write("-");
                    }
                    escritor2.newLine();
                }
                escritor2.close();
                escritor.close();
                
            } catch (IOException ex) {
            }

        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    public void analizador_lex(String ruta) throws IOException{
            FileReader fr = new FileReader(ruta);
            Reader lector = new BufferedReader(fr);
            reglas analisis = new reglas(lector);
            DefaultTableModel model = (DefaultTableModel) tabla_tokens.getModel();
            String resultados = "";
            
        while (true){
        
            
            String token = analisis.yylex();
        
            if (token == "FIN") {
                resultados = resultados + "\n\rFIN";
                jTextArea1.setText(resultados);
                return;
            }
            
            lista_resultado.addAll(Arrays.asList(token.split(",")));
            if (lista_resultado.size()==4) {
            model.addRow(new Object[] {lista_resultado.get(0),lista_resultado.get(1),lista_resultado.get(2),lista_resultado.get(3)});
            }
             lista_resultado.clear();

            
            resultados+=token+"\n\r";
            
//            switch(token){
//            
//                case "Decimal":
//                    resultados = resultados + "Token: "+token+" "+analisis.yytext();
//                    break;
//                case "Octal":
//                    resultados = resultados + "Token: "+token+" "+analisis.yytext();
//                    break;
//                case "Hex":
//                    resultados = resultados + "Token: "+token+" "+analisis.yytext();
//                    break;
//                case "Identifier":
//                    resultados = resultados + "Token: "+token+" "+analisis.yytext();
//                    break;
//                    default:
//                        break;
//                
//            }
                    
            
        }
    
    }
    
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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable tabla_tokens;
    // End of variables declaration//GEN-END:variables
}
