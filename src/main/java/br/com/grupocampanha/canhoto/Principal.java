/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.grupocampanha.canhoto;

import br.com.grupocampanha.xml.XML;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jfc
 */
public class Principal extends javax.swing.JFrame {

    DefaultTableModel modeloTabela;
    List<Cabecalho> cabecalhos;
    String texto;
    Opcoes opcoes;
    Counter counter;

    public Principal() {
        cabecalhos = new ArrayList();
        opcoes = new Opcoes(this);
        try {
            counter = new Counter(
                    Float.parseFloat(XML.parse(new File("./Config.xml")).getRootNode().find(f -> f.getNome().equalsIgnoreCase("TempoLimite")).getValue()),
                    47);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(this, e.getMessage());
        }
        texto = "";

        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Nro nota");
        modeloTabela.addColumn("Ocorrencia");
        modeloTabela.addColumn("NumeroAleatorio");
        modeloTabela.addColumn("Status de importacao");
        initComponents();
        setLocationRelativeTo(null);
        jTable1.removeColumn(jTable1.getColumnModel().getColumn(2));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelPrincipal = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }};
            jProgressBar1 = new javax.swing.JProgressBar();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    formKeyTyped(evt);
                }
            });

            painelPrincipal.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    painelPrincipalKeyTyped(evt);
                }
            });

            jButton2.setText("Deletar");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });

            jButton1.setText("Importar");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            jTable1.setModel(modeloTabela);
            jTable1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            jTable1.setShowGrid(false);
            jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jTable1MouseClicked(evt);
                }
            });
            jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    jTable1KeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    jTable1KeyReleased(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTable1KeyTyped(evt);
                }
            });
            jScrollPane1.setViewportView(jTable1);

            javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
            painelPrincipal.setLayout(painelPrincipalLayout);
            painelPrincipalLayout.setHorizontalGroup(
                painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPrincipalLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton2))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
            );
            painelPrincipalLayout.setVerticalGroup(
                painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPrincipalLayout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addGroup(painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(painelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (modeloTabela.getRowCount() > 0 && JOptionPane.showConfirmDialog(this, "Deseja realmente importar", "importar?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            importar();
        }
        jTable1.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        if ((!evt.isActionKey() && (evt.getKeyChar() == '\n' || evt.getKeyChar() == '\r')) || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            return;
        }

        if (counter.isStoped()) {
            counter.reset();
            counter.init();
            texto = "";
        }
        counter.count();
        if (!counter.isInTime() && !counter.isComplete() && !counter.isStoped()) {
            counter.reset();
            texto = "";
return;
        }
        texto += evt.getKeyChar();

        
        if (counter.isComplete() && counter.isInTime()) {
            processarCodigoDeBarras(texto);
            counter.reset();
        } else if (!counter.isInTime()) {
            counter.reset();
            texto = "";
        }
System.out.println(counter.getCurrentTime() + " " + counter.getCount() + " " + texto);

    }//GEN-LAST:event_jTable1KeyTyped

    private void importar() {
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        int rowsCount = modeloTabela.getRowCount();
        jProgressBar1.setMaximum(rowsCount);
        int progressBar = 0;
        for (int i = rowsCount - 1; i >= 0; i--) {
            Cabecalho cab = findCab(i);
            String retorno = Sankhya.importarCabecalho(cab);
            if (retorno.equals("ok")) {
                modeloTabela.removeRow(i);
                cabecalhos.remove(cab);
            } else {
                modeloTabela.setValueAt(retorno, i, modeloTabela.findColumn("Status de importacao"));
            }

            jProgressBar1.setValue(progressBar++);
        }
        if (modeloTabela.getRowCount() > 0) {
            JOptionPane.showMessageDialog(this, "houve erro ao importar", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Importacao finalizada", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
        }
        jProgressBar1.setValue(0);
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);
        jTable1.requestFocus();
    }

    private void deletar() {
        int[] rows = jTable1.getSelectedRows();
        for (int i = rows.length - 1; i >= 0; i--) {
            Cabecalho cab = findCab(i);
            if (cab != null) {
                cabecalhos.remove(cab);
                modeloTabela.removeRow(i);
            }
        }

    }

    private Cabecalho findCab(int linha) {

        String nroNota = (String) modeloTabela.getValueAt(linha, modeloTabela.findColumn("Nro nota"));
        String status = (String) modeloTabela.getValueAt(linha, modeloTabela.findColumn("Ocorrencia"));
        String numeroAleatorio = (String) modeloTabela.getValueAt(linha, modeloTabela.findColumn("NumeroAleatorio"));
        return cabecalhos.stream().filter(f -> f.getNumeroDaNota().equals(nroNota) && f.getNunAletNota().equals(numeroAleatorio) && f.getStatus().contains(status)).findFirst().orElse(null);

    }

    private void processarCodigoDeBarras(String codigo) {
        Cabecalho cab = new Cabecalho(codigo);
        if (cabecalhos.stream().filter(f -> f.getNunAletNota().equals(cab.getNunAletNota()) && f.getNumeroDaNota().equals(cab.getNumeroDaNota())).findFirst().orElse(null) != null) {
            Mensagem.show(this, "Nota ja Vinculada", "ERRO");
            return;
        }
        String opcao = opcoes.getOpcao();
        if (opcao != null) {
            cab.setStatus(opcao);
            modeloTabela.addRow(new Object[]{cab.getNumeroDaNota(), cab.getStatus(), cab.getNunAletNota()});
            cabecalhos.add(cab);
        }

    }

    private void deletarLinhas() {
        if (jTable1.getSelectedRowCount() != 0) {
            String s = jTable1.getSelectedRowCount() > 1 ? "s" : "";

            if (JOptionPane.showConfirmDialog(this, "Deseja realmente apagar esta" + s + " linha" + s + "?", "Opcao", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                deletar();

                JOptionPane.showMessageDialog(this, "Linha" + s + " apagada" + s);
            }
        }
        jTable1.requestFocus();
    }
    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased


    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE)
            deletarLinhas();
    }//GEN-LAST:event_jTable1KeyPressed


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        deletarLinhas();
        jTable1.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyTyped

    private void painelPrincipalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_painelPrincipalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_painelPrincipalKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
            String valorEditado = JOptionPane.showInputDialog(this, modeloTabela.getValueAt(jTable1.getSelectedRow(),
                    modeloTabela.findColumn("Ocorrencia")), "Nro nota: " + modeloTabela.getValueAt(jTable1.getSelectedRow(),
                    modeloTabela.findColumn("Nro nota")), JOptionPane.INFORMATION_MESSAGE);
            if (valorEditado != null && !valorEditado.isEmpty()) {
                findCab(jTable1.getSelectedRow()).setStatus(valorEditado);
                modeloTabela.setValueAt(valorEditado, jTable1.getSelectedRow(), modeloTabela.findColumn("Ocorrencia"));
                JOptionPane.showMessageDialog(this, "Valor editado!", "sucesso", JOptionPane.INFORMATION_MESSAGE);
            }

        }

    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel painelPrincipal;
    // End of variables declaration//GEN-END:variables
}
