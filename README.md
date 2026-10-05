proyek ini tuga mata pelajaran Pemograman berbasis teks dan Grafis dikembangkan bersama miss @Niaadiyant15

Cara Verifikasi: Buka halaman repositori Anda di browser, lalu pastikan teks tersebut berubah menjadi tautan yang bisa diklik dan mengarah langsung ke profil akun yang Anda mention.

package form;

import Koneksi.Koneksi;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormAdmin extends javax.swing.JFrame {

    DefaultTableModel model;

    public FormAdmin() {
        initComponents();
        setLocationRelativeTo(null);
        loadDataPendaftar();
    }

    public void loadDataPendaftar() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama Siswa");
        model.addColumn("Mata Lomba");
        model.addColumn("Status Tahapan");
        tblPendaftar.setModel(model);
        
        try {
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();
            String sql = "SELECT pendaftaran.id, users.username, lomba.nama_lomba, pendaftaran.status " +
                         "FROM pendaftaran " +
                         "JOIN users ON pendaftaran.user_id = users.id " +
                         "JOIN lomba ON pendaftaran.lomba_id = lomba.id";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                model.addRow(new Object[]{
                    r.getString("id"),
                    r.getString("username"),
                    r.getString("nama_lomba"),
                    r.getString("status")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage());
        }
    }

    private void btnSimpanLombaActionPerformed(java.awt.event.ActionEvent evt) {                                               
        try {
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();
            String sql = "INSERT INTO lomba (nama_lomba) VALUES ('" + txtNamaLomba.getText() + "')";
            s.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Mata Lomba berhasil ditambahkan!");
            txtNamaLomba.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal tambah lomba: " + e.getMessage());
        }
    } 
    
    private void btnUbahStatusActionPerformed(java.awt.event.ActionEvent evt) {                                              
        int baris = tblPendaftar.getSelectedRow();
        if (baris == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data siswa di tabel terlebih dahulu!");
            return;
        }
        String idPendaftaran = model.getValueAt(baris, 0).toString();
        String statusBaru = JOptionPane.showInputDialog(this, "Masukkan Status Baru (Contoh: Tahap Briefing / Pelatihan / Selesai):");
        
        if (statusBaru != null && !statusBaru.trim().isEmpty()) {
            try {
                Connection c = Koneksi.configDB();
                Statement s = c.createStatement();
                String sql = "UPDATE pendaftaran SET status='" + statusBaru + "' WHERE id='" + idPendaftaran + "'";
                s.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Status berhasil diperbarui!");
                loadDataPendaftar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal update status: " + e.getMessage());
            }
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNamaLomba = new javax.swing.JTextField();
        btnSimpanLomba = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPendaftar = new javax.swing.JTable();
        btnUbahStatus = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PANEL ADMIN - BI GOT TALENT");

        txtNamaLomba.setText("Nama lomba");
        txtNamaLomba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaLombaActionPerformed(evt);
            }
        });

        btnSimpanLomba.setText("Simpan lomba");

        jLabel2.setText("Nama Mata Lomba Baru:");

        tblPendaftar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nama Siswa", "Mata lomba", "Status Tahapan"
            }
        ));
        jScrollPane1.setViewportView(tblPendaftar);

        btnUbahStatus.setText("Ubah status");

        btnUbah.setText("Ubah");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(32, 32, 32)
                        .addComponent(txtNamaLomba, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUbah)
                            .addComponent(btnSimpanLomba))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUbahStatus)
                .addGap(258, 258, 258))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaLomba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnSimpanLomba))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUbah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(btnUbahStatus)
                .addGap(67, 67, 67))
        );

        pack();
    }// </editor-fold>                        

    private void txtNamaLombaActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(FormAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnSimpanLomba;
    private javax.swing.JButton btnUbah;
    private javax.swing.JButton btnUbahStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPendaftar;
    private javax.swing.JTextField txtNamaLomba;
    // End of variables declaration                   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import Koneksi.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class FormLogin extends javax.swing.JFrame {

    /**
     * Creates new form FormLogin
     */
    public FormLogin() {
        initComponents();
        setLocationRelativeTo(null); // Posisi form di tengah layar
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("LOGIN BI-GOT TALENT");

        jLabel2.setText("Username :");

        jLabel3.setText("Password : ");

        txtUsername.setText("username");
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        txtPassword.setText("jPasswordField1");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(btnLogin)))
                .addContainerGap(266, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(btnLogin)
                .addGap(114, 114, 114))
        );

        pack();
    }// </editor-fold>                        

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        try {
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();
            
            String sql = "SELECT * FROM users WHERE username='" + txtUsername.getText() 
                    + "' AND password='" + txtPassword.getText() + "'";
            ResultSet r = s.executeQuery(sql);
            
            if (r.next()) {
                String role = r.getString("role");
                int idUser = r.getInt("id");
                
                if (role.equals("admin")) {
                    JOptionPane.showMessageDialog(this, "Login Berhasil sebagai Admin!");
                    new FormAdmin().setVisible(true);
                    this.dispose();
                } else if (role.equals("siswa")) {
                    JOptionPane.showMessageDialog(this, "Login Berhasil sebagai Siswa!");
                    new FormSiswa(idUser).setVisible(true);
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password salah!");
                txtPassword.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage());
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
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration                   
}
