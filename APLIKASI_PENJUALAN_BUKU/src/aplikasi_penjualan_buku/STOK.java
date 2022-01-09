/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_penjualan_buku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FAWKES
 */
public class STOK extends javax.swing.JInternalFrame {

    Connection conn;
    Statement stm;
    ResultSet rs;
    
    /**
     * Creates new form rockwell_what
     */
    public STOK() {
        initComponents();
        siapIsi(false);
        tombolNormal();
        tb_buku();
    }
    
    public Connection setKoneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_tokobuku","root","");
            stm=conn.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Koneksi Gagal :" +e);
        }
       return conn; 
    }
    
        private void siapIsi(boolean a){
        txkodebuku.setEnabled(a);
        txnamabuku.setEnabled(a);
        txpengarang.setEnabled(a);
        txpenerbit.setEnabled(a);
        txtahunterbit.setEnabled(a);
        txhargabuku.setEnabled(a);
        txstok.setEnabled(a);
        
    }
    
    private void tombolNormal(){
        bttambah.setEnabled(true);
        btsimpan.setEnabled(false);
        bthapus.setEnabled(false);
        btedit.setEnabled(false);
        
    }
    
    private void bersih(){
        txkodebuku.setText("");
        txnamabuku.setText("");
        txpengarang.setText("");
        txpenerbit.setText("");
        txtahunterbit.setText("");
        txhargabuku.setText("");
        txstok.setText("");
       
    }
    
    private void kodebuku(){
        try {
            setKoneksi();
            String sql="select right (kodebuku,2)+1 from tb_stokbuku ";
            ResultSet rs=stm.executeQuery(sql);
            if(rs.next()){
                rs.last();
                String no=rs.getString(1);
                while (no.length()<3){
                    no="0"+no;
                    txkodebuku.setText("KB"+no);}
                }
            else
            {
                txkodebuku.setText("KB001"); 
        }
        } catch (Exception e) 
        {
        }
 }
    private void simpan(){
        try{
            setKoneksi();
            String sql="insert into tb_stokbuku values('"+txkodebuku.getText()
                    +"','"+txnamabuku.getText()
                    +"','"+txpengarang.getText()
                    +"','"+txpenerbit.getText()
                    +"','"+txtahunterbit.getText()
                    +"','"+txhargabuku.getText()
                    +"','"+txstok.getText() +"')";
            stm.executeUpdate(sql); 
            JOptionPane.showMessageDialog(null,"Simpan Data Berhasil");
            }
            catch (Exception e) {
        }
        tb_buku();
       
    }
    
    private void edit(){
        try{
            setKoneksi();
            String sql="update tb_stokbuku set namabuku='"+txnamabuku.getText()
                    +"',pengarang='"+txpengarang.getText()
                    +"',penerbit='"+txpenerbit.getText()
                    +"',tahunterbit='"+txtahunterbit.getText()
                    +"',hargabuku='"+txhargabuku.getText()
                    +"',stok='"+txstok.getText()
                    +"' where kodebuku='"+txkodebuku.getText()+"'";
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Edit Data Berhasil","",JOptionPane.INFORMATION_MESSAGE);
        } 
        catch(Exception e){
        }
        tb_buku();
        
    }
    
    private void hapus(){
        try{
            String sql="delete from tb_stokbuku where kodebuku='"+ txkodebuku.getText() +"'";
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Hapus Data Berhasil ");
            }
            catch (Exception e) {
            }
        tb_buku();
    }
    
    public void tb_buku(){
        Object header[]={"KODE BUKU","NAMA BUKU","PENGARANG","PENERBIT","TAHUN TERBIT","HARGA","STOK"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tb_buku.setModel(data);
        setKoneksi();
        String sql="select*from tb_stokbuku";
        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);
                String kolom6=rs.getString(6);
                String kolom7=rs.getString(7);
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txkodebuku = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txnamabuku = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txpengarang = new javax.swing.JTextField();
        txpenerbit = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtahunterbit = new javax.swing.JTextField();
        txhargabuku = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txstok = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txpencarianbuku = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_buku = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        bttambah = new javax.swing.JButton();
        btsimpan = new javax.swing.JButton();
        btedit = new javax.swing.JButton();
        bthapus = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("STOK");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Kode Buku  :");

        txkodebuku.setEditable(false);
        txkodebuku.setBackground(new java.awt.Color(51, 51, 51));
        txkodebuku.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txkodebuku.setForeground(new java.awt.Color(204, 204, 204));
        txkodebuku.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txkodebuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txkodebukuActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Nama Buku :");

        txnamabuku.setBackground(new java.awt.Color(204, 204, 204));
        txnamabuku.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txnamabuku.setForeground(new java.awt.Color(51, 51, 51));
        txnamabuku.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txnamabuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txnamabukuActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Pengarang :");

        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Penerbit      :");

        txpengarang.setBackground(new java.awt.Color(204, 204, 204));
        txpengarang.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txpengarang.setForeground(new java.awt.Color(51, 51, 51));
        txpengarang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txpengarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txpengarangActionPerformed(evt);
            }
        });

        txpenerbit.setBackground(new java.awt.Color(204, 204, 204));
        txpenerbit.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txpenerbit.setForeground(new java.awt.Color(51, 51, 51));
        txpenerbit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txpenerbit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txpenerbitActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Tahun Terbit :");

        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Harga Buku  :");

        txtahunterbit.setBackground(new java.awt.Color(204, 204, 204));
        txtahunterbit.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txtahunterbit.setForeground(new java.awt.Color(51, 51, 51));
        txtahunterbit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtahunterbit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtahunterbitActionPerformed(evt);
            }
        });

        txhargabuku.setBackground(new java.awt.Color(204, 204, 204));
        txhargabuku.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txhargabuku.setForeground(new java.awt.Color(51, 51, 51));
        txhargabuku.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txhargabuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txhargabukuActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Stok :");

        txstok.setBackground(new java.awt.Color(204, 204, 204));
        txstok.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txstok.setForeground(new java.awt.Color(51, 51, 51));
        txstok.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txstok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txstokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txkodebuku, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(txnamabuku))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txpengarang, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txpenerbit)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtahunterbit, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txhargabuku, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txstok)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txkodebuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txpengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtahunterbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txstok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txnamabuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txpenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txhargabuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txpencarianbuku.setBackground(new java.awt.Color(204, 204, 204));
        txpencarianbuku.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txpencarianbuku.setForeground(new java.awt.Color(51, 51, 51));
        txpencarianbuku.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txpencarianbuku.setText("KETIK KODE BUKU ATAU NAMA BUKU UNTUK MELAKUKAN PENCARIAN");
        txpencarianbuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txpencarianbukuActionPerformed(evt);
            }
        });
        txpencarianbuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txpencarianbukuKeyPressed(evt);
            }
        });

        tb_buku.setBackground(new java.awt.Color(204, 204, 204));
        tb_buku.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        tb_buku.setForeground(new java.awt.Color(51, 51, 51));
        tb_buku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_buku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_bukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_buku);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(txpencarianbuku))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txpencarianbuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bttambah.setBackground(new java.awt.Color(0, 0, 0));
        bttambah.setForeground(new java.awt.Color(204, 204, 204));
        bttambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/TAMBAH.png"))); // NOI18N
        bttambah.setText("TAMBAH");
        bttambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttambahActionPerformed(evt);
            }
        });

        btsimpan.setBackground(new java.awt.Color(0, 0, 0));
        btsimpan.setForeground(new java.awt.Color(204, 204, 204));
        btsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/SIMPAN.png"))); // NOI18N
        btsimpan.setText("SIMPAN");
        btsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsimpanActionPerformed(evt);
            }
        });

        btedit.setBackground(new java.awt.Color(0, 0, 0));
        btedit.setForeground(new java.awt.Color(204, 204, 204));
        btedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/EDIT.png"))); // NOI18N
        btedit.setText("EDIT");
        btedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteditActionPerformed(evt);
            }
        });

        bthapus.setBackground(new java.awt.Color(0, 0, 0));
        bthapus.setForeground(new java.awt.Color(204, 204, 204));
        bthapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/HAPUS.png"))); // NOI18N
        bthapus.setText("HAPUS");
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(bttambah)
                .addGap(18, 18, 18)
                .addComponent(btsimpan)
                .addGap(18, 18, 18)
                .addComponent(btedit)
                .addGap(18, 18, 18)
                .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btedit, bthapus, btsimpan, bttambah});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttambah)
                    .addComponent(btsimpan)
                    .addComponent(btedit)
                    .addComponent(bthapus))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(0, 0, 865, 384);
    }// </editor-fold>//GEN-END:initComponents

    private void txkodebukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txkodebukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txkodebukuActionPerformed

    private void txnamabukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txnamabukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txnamabukuActionPerformed

    private void txpenerbitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txpenerbitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txpenerbitActionPerformed

    private void txpengarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txpengarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txpengarangActionPerformed

    private void txtahunterbitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtahunterbitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtahunterbitActionPerformed

    private void txhargabukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txhargabukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txhargabukuActionPerformed

    private void txstokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txstokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txstokActionPerformed

    private void txpencarianbukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txpencarianbukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txpencarianbukuActionPerformed

    private void txpencarianbukuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txpencarianbukuKeyPressed
        // TODO add your handling code here:
        Object header[]={"KODE BUKU","NAMA BUKU","PENGARANG","PENERBIT","TAHUN TERBIT","HARGA","STOK"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tb_buku.setModel(data);
        setKoneksi();
        String sql="Select * from tb_stokbuku where kodebuku like '%" + txpencarianbuku.getText() + "%'" + "or namabuku like '%" + txpencarianbuku.getText()+"%'";
        try {
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);
                String kolom6=rs.getString(6);
                String kolom7=rs.getString(7);
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txpencarianbukuKeyPressed

    private void tb_bukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_bukuMouseClicked
        // TODO add your handling code here:
        int baris = tb_buku.getSelectedRow();
        txkodebuku.setText(tb_buku.getModel().getValueAt(baris, 0).toString());
        txnamabuku.setText(tb_buku.getModel().getValueAt(baris, 1).toString());
        txpengarang.setText(tb_buku.getModel().getValueAt(baris, 2).toString());
        txpenerbit.setText(tb_buku.getModel().getValueAt(baris, 3).toString());
        txtahunterbit.setText(tb_buku.getModel().getValueAt(baris, 4).toString());
        txhargabuku.setText(tb_buku.getModel().getValueAt(baris, 5).toString());
        txstok.setText(tb_buku.getModel().getValueAt(baris, 6).toString());
        bthapus.setEnabled(true);
        btedit.setEnabled(true);
    }//GEN-LAST:event_tb_bukuMouseClicked

    private void bttambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttambahActionPerformed
        // TODO add your handling code here:
        if(bttambah.getText().equalsIgnoreCase("TAMBAH")){
            bttambah.setText("REFRESH");
            bersih();
            siapIsi(true);
            kodebuku();

            txkodebuku.setEnabled(true);
            bttambah.setEnabled(true);
            btsimpan.setEnabled(true);
            bthapus.setEnabled(false);
            btedit.setEnabled(false);
        } else{
            bttambah.setText("TAMBAH");
            bersih();
            siapIsi(false);
            tombolNormal();
            tb_buku();
        }
    }//GEN-LAST:event_bttambahActionPerformed

    private void btsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsimpanActionPerformed
        // TODO add your handling code here:
        if(txkodebuku.getText().isEmpty()
            ||txnamabuku.getText().isEmpty()
                ||txpengarang.getText().isEmpty()
                ||txpenerbit.getText().isEmpty()
                ||txtahunterbit.getText().isEmpty()
            ||txhargabuku.getText().isEmpty()
            ||txstok.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "Mohon Lengkapi Inputan Data!!!","",JOptionPane.INFORMATION_MESSAGE);
        } else{

            if(bttambah.getText().equalsIgnoreCase("REFRESH")){
                if(bttambah.getText().equalsIgnoreCase("REFRESH")){
                    simpan();
                } else{
                    JOptionPane.showMessageDialog(null, "SIMPAN DATA GAGAL, PERIKSA KEMBALI :( ","",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if(btedit.getText().equalsIgnoreCase("BATAL")){
                if(btedit.getText().equalsIgnoreCase("BATAL")){
                    edit();
                } else{
                    JOptionPane.showMessageDialog(null, "EDIT DATA GAGAL, PERIKSA KEMBALI :( ","",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            bersih();
            siapIsi(false);
            bttambah.setText("TAMBAH");
            btedit.setText("EDIT");
            tombolNormal();

        }
    }//GEN-LAST:event_btsimpanActionPerformed

    private void bteditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteditActionPerformed
        // TODO add your handling code here:
        if(btedit.getText().equalsIgnoreCase("EDIT")){
            btedit.setText("BATAL");
            siapIsi(true);
            txkodebuku.setEnabled(false);
            bttambah.setEnabled(false);
            btsimpan.setEnabled(true);
            bthapus.setEnabled(false);
            btedit.setEnabled(true);
        } else{
            btedit.setText("EDIT");
            bersih();
            siapIsi(false);
            tombolNormal();

        }
    }//GEN-LAST:event_bteditActionPerformed

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed
        // TODO add your handling code here:
        int pesan=JOptionPane.showConfirmDialog(null, "YAKIN DATA AKAN DIHAPUS ?","Konfirmasi",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(pesan==JOptionPane.YES_OPTION){
            if(pesan==JOptionPane.YES_OPTION){
                hapus();
                bersih();
                siapIsi(false);
                tombolNormal();
            } else{
                JOptionPane.showMessageDialog(null, "HAPUS DATA GAGAL :(");
            }

        }
    }//GEN-LAST:event_bthapusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btedit;
    private javax.swing.JButton bthapus;
    private javax.swing.JButton btsimpan;
    private javax.swing.JButton bttambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_buku;
    private javax.swing.JTextField txhargabuku;
    private javax.swing.JTextField txkodebuku;
    private javax.swing.JTextField txnamabuku;
    private javax.swing.JTextField txpencarianbuku;
    private javax.swing.JTextField txpenerbit;
    private javax.swing.JTextField txpengarang;
    private javax.swing.JTextField txstok;
    private javax.swing.JTextField txtahunterbit;
    // End of variables declaration//GEN-END:variables
}
