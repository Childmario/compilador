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
    String texto = "";
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

        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_tokens = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Buscar y analizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(99, 99, 99)
                        .addComponent(jLabel2)))
                .addContainerGap(134, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(410, 410, 410)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     String ruta = "";
//     DefaultTableModel model = (DefaultTableModel) tabla_tokens.getModel();
//        while (model.getRowCount() > 0) {            
//            for (int i = 0; i < model.getRowCount(); i++) {
//                model.removeRow(i);
//            }
//        }
     //DefaultTableModel modelo = (DefaultTableModel) tabla_tokens.getModel();
     //tabla_tokens.setModel(new DefaultTableModel(null,new String [] {"TOKEN        LEXEMA                                             LINEA        COLUMNA"}));
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
        
//JFileChooser fileChooser_save = new JFileChooser();
//fileChooser_save.setCurrentDirectory(new File(System.getProperty("user.home")));
//int result_save = fileChooser_save.showSaveDialog(this);
//        if (result_save == JFileChooser.APPROVE_OPTION) {
//    File selectedFile = fileChooser_save.getSelectedFile();
//     
//            try {
//
//    FileWriter escritor = new FileWriter(selectedFile);
//    BufferedWriter escritor2 = new BufferedWriter(escritor);
//    escritor2.write("TOKEN        LEXEMA                                             LINEA        COLUMNA");
//    escritor2.newLine();
//                for (int i = 0; i < tabla_tokens.getRowCount(); i++) {
//                    int size_id;
//                    int size_lex;
//                    int size_linea;
//                    int equilibrio1 = 0;
//                    int equilibrio2 = 10;
//                    int equilibrio3 = 0;
//                    int equilibrio4 = 50;
//                    int equilibrio5 = 0;
//                    int equilibrio6 = 12;
//                    for (int j = 0; j < tabla_tokens.getColumnCount(); j++) {
//                        if (j==0) {
//                            size_id = tabla_tokens.getValueAt(i, j).toString().length();
//                            equilibrio1 = size_id - 2;
//                            equilibrio2 = equilibrio2 - equilibrio1;
//                        }
//                       
//                        if (j!=0) {
//                            if (j==1) {
//                            size_lex = tabla_tokens.getValueAt(i, j).toString().length();
//                            equilibrio3 = size_lex - 1;
//                            equilibrio4 = equilibrio4 - equilibrio3;                                
//                            for (int k = 0; k <= equilibrio2; k++) {
//                            escritor2.write(" ");                                
//                            }
//                            
//                        }
//                            if (j==2) {
//                            size_linea = tabla_tokens.getValueAt(i, j).toString().length();
//                            equilibrio5 = size_linea - 1;
//                            equilibrio6 = equilibrio6 - equilibrio5;                                
//                                for (int k = 0; k < equilibrio4; k++) {
//                                    escritor2.write(" ");
//                                }
//                            }
//                            
//                            if (j==3) {
//                                for (int k = 0; k < equilibrio6; k++) {
//                                    escritor2.write(" ");
//                                }                                
//                            }
//                        }
//                      
//                        escritor2.write(tabla_tokens.getValueAt(i, j).toString());
//                        //escritor2.write("-");
//                    }
//                    escritor2.newLine();
//                }
//                escritor2.close();
//                escritor.close();
//                
//            } catch (IOException ex) {
//            }

       // }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    public void analizador_lex(String ruta) throws IOException{
            FileReader fr = new FileReader(ruta);
            BufferedReader lector = new BufferedReader(fr);
            
            
            String temp = "";
            String bfread;
            while ((bfread = lector.readLine()) != null) {            
            temp = temp + bfread;
        }
            jTextPane1.setText("");
            jLabel2.setText("");
            texto += temp;
            jTextPane1.setText(texto);
//            
//            texto = temp;
            //reglas analisis = new reglas(new BufferedReader(new StringReader(texto)));
            Sintactico sintactico = new Sintactico(new reglas(new FileReader(ruta)));
           // DefaultTableModel model = (DefaultTableModel) tabla_tokens.getModel();
           // String resultados = "";
            

            try {
                
            sintactico.parse(); 
            //ArrayList<t_simbolo> aux = new ArrayList<>();
                for (int i = 0; i < Env.t_salida.size(); i++) {
                    Salida aux = Env.t_salida.get(i);
                    String a = aux.simbolo.tipo_dato;
                    String b = aux.simbolo.tipo;
                    String c = aux.simbolo.valor;
                    String d = aux.simbolo.ambito;
                    System.out.println(aux.nombre + "|" +"(tipo)"+ b+ " -- " + "(dato)"+a +" -- " +"(valor)"+c+" -- " + "(ambito)"+d );
                }
            //aux = reglas.simbolo;
            //System.out.println(sintactico.resultado);
//            jLabel1.setText("Leido correctamente");
//            System.out.println("Correcto");
if (!Sintactico.error) {
                    System.out.println("Sintacticamente correcto");
                    jLabel2.setText("Sintacticamente correcto");
 
                    
 JFileChooser fileChooser_save = new JFileChooser();
 fileChooser_save.setCurrentDirectory(new File(System.getProperty("user.home")));
 int result_save = fileChooser_save.showSaveDialog(this);
 if (result_save == JFileChooser.APPROVE_OPTION) {
     File selectedFile = fileChooser_save.getSelectedFile();
      
             try {
 
     FileWriter escritor = new FileWriter(selectedFile);
     BufferedWriter escritor2 = new BufferedWriter(escritor);
     escritor2.write("SIMBOLO                 TIPO              DATO          VALOR              AMBITO");
     escritor2.newLine();
                 for (int i = 0; i < Env.t_salida.size(); i++) {
                    Salida aux = Env.t_salida.get(i);
                    String a = aux.simbolo.tipo_dato;
                    String b = aux.simbolo.tipo;
                    String c = aux.simbolo.valor;
                    String d = aux.simbolo.ambito;
                     if (a==null||b==null||c==null||d==null) {
                         System.out.println("err");
                     }
                    escritor2.write(aux.nombre + Env.escritura(aux.nombre,24,0) + b +Env.escritura(b, 42,24)+ a+Env.escritura(a, 56,42)+c+Env.escritura(c, 75,56)+d);
                    escritor2.newLine();
                 }
                 escritor2.close();
                 escritor.close();
                 
             } catch (IOException ex) {
           }

        }
 
 // <editor-fold defaultstate="collapsed" desc="Guardar CSV"> 
  JFileChooser save2 = new JFileChooser();
 save2.setCurrentDirectory(new File(System.getProperty("user.home")));
 int result2 = save2.showSaveDialog(this);
 if (result2 == JFileChooser.APPROVE_OPTION) {
     File sel = save2.getSelectedFile();
      
             try {
 
     FileWriter out = new FileWriter(sel);
     String HEADER = "SIMBOLO,TIPO,DATO,VALOR,FIRMA,AMBITO";
     String SEPARATOR = "\n";
     String COMA = ",";
                 out.append(HEADER);
                 for (Salida z:  Env.t_salida) {
                    Salida aux = z;
                    out.append(SEPARATOR);
                    out.append(z.nombre);
                    out.append(COMA);
                    String a = aux.simbolo.tipo_dato;
                    out.append(a);
                    out.append(COMA);
                    String b = aux.simbolo.tipo;
                    out.append(b);
                    out.append(COMA);
                    String c = aux.simbolo.valor;
                    out.append(c);
                    out.append(COMA);
                    String e = aux.simbolo.vars;
                    out.append(e);
                    out.append(COMA);                    
                    String d = aux.simbolo.ambito;
                    out.append(d);
                    out.append(COMA);

                 }
                 out.flush();
                 out.close();
                 
             } catch (IOException ex) {
           }

        }
// </editor-fold>
                }
                Sintactico.error = false;
            } catch (Exception e) {
                System.out.println(sintactico.error_sym()); 
            }

            //String token = analisis.yylex();
        
//            if (token == "FIN") {
//                resultados = resultados + "\n\rFIN";
//                jTextArea1.setText(resultados);
//                return;
//            }
            
//            lista_resultado.addAll(Arrays.asList(token.split(",")));
//            if (lista_resultado.size()==4) {
//            model.addRow(new Object[] {lista_resultado.get(0),lista_resultado.get(1),lista_resultado.get(2),lista_resultado.get(3)});
//            }
//             lista_resultado.clear();

//            
//            resultados+=token+"\n\r";
            
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTable tabla_tokens;
    // End of variables declaration//GEN-END:variables
}
