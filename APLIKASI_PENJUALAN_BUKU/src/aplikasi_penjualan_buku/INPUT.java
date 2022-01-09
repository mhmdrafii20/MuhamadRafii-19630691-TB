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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FAWKES
 */
public class INPUT extends javax.swing.JInternalFrame {

    Connection conn;
    Statement stm;
    ResultSet rs;
    
    /**
     * Creates new form rockwell_what
     */
    public INPUT() {
        initComponents();
        siapIsi(false);
        tombolNormal();
        tb_transaksi();
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
        txkodetransaksi.setEnabled(a);
        txkodebuku.setEnabled(a);
        txnamabuku.setEnabled(a);
        txpengarang.setEnabled(a);
        txpenerbit.setEnabled(a);
        txtahunterbit.setEnabled(a);
        //txtanggal.setEnabled(a);
        txjumlah.setEnabled(a);
        txhargabeli.setEnabled(a);
        txhargajual.setEnabled(a);
        
    }
    
    private void tombolNormal(){
        bttambah.setEnabled(true);
        btsimpan.setEnabled(false);
        bthapus.setEnabled(false);
        btbuku.setEnabled(false);
        
    }
    
    private void bersih(){
        txkodetransaksi.setText("");
        txkodebuku.setText("");
        txnamabuku.setText("");
        txpengarang.setText("");
        txpenerbit.setText("");
        txtahunterbit.setText("");
        //txtanggal.setText("");
        txjumlah.setText("");
        txhargabeli.setText("");
        txhargajual.setText("");
       
    }
    
    private void kodetransaksi(){
        try {
            setKoneksi();
            String sql="select right (kodetransaksi,2)+1 from tb_inputbuku ";
            ResultSet rs=stm.executeQuery(sql);
            if(rs.next()){
                rs.last();
                String no=rs.getString(1);
                while (no.length()<3){
                    no="0"+no;
                    txkodetransaksi.setText("TR"+no);}
                }
            else
            {
                txkodetransaksi.setText("TR001"); 
        }
        } catch (Exception e) 
        {
        }
 }
    private void simpan(){
        try{
            Date skrg=new Date();
           SimpleDateFormat frm=new SimpleDateFormat("yyyy-MM-dd");
           String tanggal=frm.format(skrg); 
           
            setKoneksi();
            String sql="insert into tb_inputbuku values('"+txkodetransaksi.getText()
                    +"','"+txkodebuku.getText()
                    +"','"+txnamabuku.getText()
                    +"','"+txpengarang.getText()
                    +"','"+txpenerbit.getText()
                    +"','"+txtahunterbit.getText()
                    +"','"+tanggal
                    +"','"+txjumlah.getText()
                    +"','"+txhargabeli.getText()
                    +"','"+txhargajual.getText() +"')";
            stm.executeUpdate(sql); 
            JOptionPane.showMessageDialog(null,"Simpan Data Berhasil");
            }
            catch (Exception e) {
        }
        tb_transaksi();
       
    }
    
    
    private void hapus(){
        try{
            String sql="delete from tb_inputbuku where kodetransaksi='"+ txkodetransaksi.getText() +"'";
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Hapus Data Berhasil ");
            }
            catch (Exception e) {
            }
        tb_transaksi();
    }
    
    public void tb_transaksi(){
        Object header[]={"KODE TRANSAKSI","KODE BUKU","NAMA","PENGARANG","PENERBIT","TERBIT","TANGGAL","JUMLAH","HARGA BELI","HARGA JUAL"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tb_transaksi.setModel(data);
        setKoneksi();
        String sql="select*from tb_inputbuku";
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
                String kolom8=rs.getString(8);
                String kolom9=rs.getString(9);
                String kolom10=rs.getString(10);
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8,kolom9,kolom10,};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
    }
    
    public void tb_buku(){
        Object header[]={"KODE BUKU","NAMA BUKU","PENGARANG","PENERBIT","TERBIT","HARGA","STOK"};
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

        jDialog1 = new javax.swing.JDialog();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_buku = new javax.swing.JTable();
        txpencarianbuku = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txkodetransaksi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txkodebuku = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txnamabuku = new javax.swing.JTextField();
        txpengarang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txpenerbit = new javax.swing.JTextField();
        txtahunterbit = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txjumlah = new javax.swing.JTextField();
        txhargabeli = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txhargajual = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        bttambah = new javax.swing.JButton();
        btbuku = new javax.swing.JButton();
        btsimpan = new javax.swing.JButton();
        bthapus = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txpencariantransaksi = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_transaksi = new javax.swing.JTable();

        jDialog1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog1.setBackground(new java.awt.Color(0, 0, 51));
        jDialog1.setMinimumSize(new java.awt.Dimension(694, 430));
        jDialog1.setModal(true);
        jDialog1.setResizable(false);
        jDialog1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDialog1MouseClicked(evt);
            }
        });

        jInternalFrame1.setTitle("TABEL BUKU");
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(694, 430));
        jInternalFrame1.setVisible(true);
        jInternalFrame1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jInternalFrame1MouseClicked(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));

        tb_buku.setAutoCreateRowSorter(true);
        tb_buku.setBackground(new java.awt.Color(204, 204, 204));
        tb_buku.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
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
        jScrollPane3.setViewportView(tb_buku);

        txpencarianbuku.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txpencarianbuku.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txpencarianbuku.setText("KETIK KODE BUKU ATAU NAMA BUKU UNTUK MELAKUKAN PENCARIAN");
        txpencarianbuku.setPreferredSize(new java.awt.Dimension(87, 30));
        txpencarianbuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txpencarianbukuKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txpencarianbuku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txpencarianbuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("INPUT");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Kode Transaksi :");

        txkodetransaksi.setEditable(false);
        txkodetransaksi.setBackground(new java.awt.Color(51, 51, 51));
        txkodetransaksi.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txkodetransaksi.setForeground(new java.awt.Color(204, 204, 204));
        txkodetransaksi.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Kode Buku          :");

        txkodebuku.setEditable(false);
        txkodebuku.setBackground(new java.awt.Color(51, 51, 51));
        txkodebuku.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txkodebuku.setForeground(new java.awt.Color(204, 204, 204));
        txkodebuku.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Nama Buku :");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Pengarang :");

        txnamabuku.setEditable(false);
        txnamabuku.setBackground(new java.awt.Color(51, 51, 51));
        txnamabuku.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txnamabuku.setForeground(new java.awt.Color(204, 204, 204));
        txnamabuku.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txpengarang.setEditable(false);
        txpengarang.setBackground(new java.awt.Color(51, 51, 51));
        txpengarang.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txpengarang.setForeground(new java.awt.Color(204, 204, 204));
        txpengarang.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Penerbit :");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Terbit      :");

        txpenerbit.setEditable(false);
        txpenerbit.setBackground(new java.awt.Color(51, 51, 51));
        txpenerbit.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txpenerbit.setForeground(new java.awt.Color(204, 204, 204));
        txpenerbit.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtahunterbit.setEditable(false);
        txtahunterbit.setBackground(new java.awt.Color(51, 51, 51));
        txtahunterbit.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txtahunterbit.setForeground(new java.awt.Color(204, 204, 204));
        txtahunterbit.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Jumlah     :");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Harga Beli :");

        txjumlah.setBackground(new java.awt.Color(204, 204, 204));
        txjumlah.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txjumlah.setForeground(new java.awt.Color(51, 51, 51));
        txjumlah.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txhargabeli.setBackground(new java.awt.Color(204, 204, 204));
        txhargabeli.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txhargabeli.setForeground(new java.awt.Color(51, 51, 51));
        txhargabeli.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("Harga Jual :");

        txhargajual.setBackground(new java.awt.Color(204, 204, 204));
        txhargajual.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        txhargajual.setForeground(new java.awt.Color(51, 51, 51));
        txhargajual.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txkodebuku, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .addComponent(txkodetransaksi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txnamabuku, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(txpengarang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txpenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtahunterbit)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txhargabeli, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txhargajual, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel5, jLabel6});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txnamabuku, txpengarang});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txpenerbit, txtahunterbit});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel7, jLabel8});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txkodebuku, txkodetransaksi});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txhargabeli, txjumlah});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel4});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txkodetransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txnamabuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txpenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txhargajual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txkodebuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txpengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtahunterbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txhargabeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, jLabel4});

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bttambah.setBackground(new java.awt.Color(0, 0, 0));
        bttambah.setForeground(new java.awt.Color(204, 204, 204));
        bttambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/TAMBAH.png"))); // NOI18N
        bttambah.setText("TAMBAH");
        bttambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttambahActionPerformed(evt);
            }
        });

        btbuku.setBackground(new java.awt.Color(0, 0, 0));
        btbuku.setForeground(new java.awt.Color(204, 204, 204));
        btbuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/tbbuku.png"))); // NOI18N
        btbuku.setText("TB BUKU");
        btbuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbukuActionPerformed(evt);
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

        bthapus.setBackground(new java.awt.Color(0, 0, 0));
        bthapus.setForeground(new java.awt.Color(204, 204, 204));
        bthapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/HAPUS.png"))); // NOI18N
        bthapus.setText("HAPUS");
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(bttambah)
                .addGap(18, 18, 18)
                .addComponent(btbuku)
                .addGap(18, 18, 18)
                .addComponent(btsimpan)
                .addGap(18, 18, 18)
                .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btbuku, bthapus, btsimpan, bttambah});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttambah)
                    .addComponent(btbuku)
                    .addComponent(btsimpan)
                    .addComponent(bthapus))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txpencariantransaksi.setBackground(new java.awt.Color(204, 204, 204));
        txpencariantransaksi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txpencariantransaksi.setForeground(new java.awt.Color(51, 51, 51));
        txpencariantransaksi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txpencariantransaksi.setText("KETIK KODE TRANSAKSI UNTUK MELAKUKAN PENCARIAN");
        txpencariantransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txpencariantransaksiKeyPressed(evt);
            }
        });

        tb_transaksi.setBackground(new java.awt.Color(204, 204, 204));
        tb_transaksi.setFont(new java.awt.Font("Dialog", 1, 9)); // NOI18N
        tb_transaksi.setForeground(new java.awt.Color(51, 51, 51));
        tb_transaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_transaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_transaksi);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(txpencariantransaksi))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txpencariantransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void tb_bukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_bukuMouseClicked
        // TODO add your handling code here:
        int baris = tb_buku.getSelectedRow();
        txkodebuku.setText(tb_buku.getModel().getValueAt(baris, 0).toString());
        txnamabuku.setText(tb_buku.getModel().getValueAt(baris, 1).toString());
        txpengarang.setText(tb_buku.getModel().getValueAt(baris, 2).toString());
        txpenerbit.setText(tb_buku.getModel().getValueAt(baris, 3).toString());
        txtahunterbit.setText(tb_buku.getModel().getValueAt(baris, 4).toString());
        txhargajual.setText(tb_buku.getModel().getValueAt(baris, 5).toString());
        //txhargabeli.setText("0");
        //txjumlah.setText("0");
        jDialog1.dispose();
    }//GEN-LAST:event_tb_bukuMouseClicked

    private void txpencarianbukuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txpencarianbukuKeyPressed
        // TODO add your handling code here:
        Object header[]={"KODE BUKU","NAMA BUKU","PENGARANG","PENERBIT","TERBIT","HARGA","STOK"};
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

    private void jInternalFrame1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jInternalFrame1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jInternalFrame1MouseClicked

    private void jDialog1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDialog1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jDialog1MouseClicked

    private void bttambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttambahActionPerformed
        // TODO add your handling code here:
        if(bttambah.getText().equalsIgnoreCase("TAMBAH")){
            bttambah.setText("REFRESH");
            bersih();
            siapIsi(true);
            kodetransaksi();

            txkodebuku.setEnabled(true);
            bttambah.setEnabled(true);
            btsimpan.setEnabled(true);
            bthapus.setEnabled(false);
            btbuku.setEnabled(true);
        } else{
            bttambah.setText("TAMBAH");
            bersih();
            siapIsi(false);
            tombolNormal();
            tb_transaksi();
        }
    }//GEN-LAST:event_bttambahActionPerformed

    private void btbukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbukuActionPerformed
        // TODO add your handling code here:
        jDialog1.setLocationRelativeTo(null);
        tb_buku();
        jDialog1.setVisible(true);
    }//GEN-LAST:event_btbukuActionPerformed

    private void btsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsimpanActionPerformed
        // TODO add your handling code here:
        if(txkodetransaksi.getText().isEmpty()
            ||txkodebuku.getText().isEmpty()
            ||txnamabuku.getText().isEmpty()
                ||txpengarang.getText().isEmpty()
                ||txpenerbit.getText().isEmpty()
                ||txtahunterbit.getText().isEmpty()
            ||txjumlah.getText().isEmpty()
            ||txhargabeli.getText().isEmpty()
            ||txhargajual.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "Mohon Lengkapi Inputan Data!!!","",JOptionPane.INFORMATION_MESSAGE);
        } else{

            if(bttambah.getText().equalsIgnoreCase("REFRESH")){
                if(bttambah.getText().equalsIgnoreCase("REFRESH")){
                    simpan();
                } else{
                    JOptionPane.showMessageDialog(null, "SIMPAN DATA GAGAL, PERIKSA KEMBALI :( ","",JOptionPane.INFORMATION_MESSAGE);
                }
            
        
            }
            bersih();
            siapIsi(false);
            bttambah.setText("TAMBAH");
            tombolNormal();

        }
    }//GEN-LAST:event_btsimpanActionPerformed

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

    private void tb_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_transaksiMouseClicked
        // TODO add your handling code here:
        int baris = tb_transaksi.getSelectedRow();
        txkodetransaksi.setText(tb_transaksi.getModel().getValueAt(baris, 0).toString());
        txkodebuku.setText(tb_transaksi.getModel().getValueAt(baris, 1).toString());
        txnamabuku.setText(tb_transaksi.getModel().getValueAt(baris, 2).toString());
        txpengarang.setText(tb_transaksi.getModel().getValueAt(baris, 3).toString());
        txpenerbit.setText(tb_transaksi.getModel().getValueAt(baris, 4).toString());
        txtahunterbit.setText(tb_transaksi.getModel().getValueAt(baris, 5).toString());
        //txjumlah.setText(tb_transaksi.getModel().getValueAt(baris, 6).toString());
        txhargabeli.setText(tb_transaksi.getModel().getValueAt(baris, 7).toString());
        txhargajual.setText(tb_transaksi.getModel().getValueAt(baris, 8).toString());
        bthapus.setEnabled(true);
    }//GEN-LAST:event_tb_transaksiMouseClicked

    private void txpencariantransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txpencariantransaksiKeyPressed
        // TODO add your handling code here:
        Object header[]={"KODE TRANSAKSI","KODE BUKU","NAMA","PENGARANG","PENERBIT","TERBIT","TANGGAL","JUMLAH","HARGA BELI","HARGA JUAL"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tb_transaksi.setModel(data);
        setKoneksi();
        String sql="Select * from tb_inputbuku where kodetransaksi like '%" + txpencariantransaksi.getText() + "%'" + "or kodebuku like '%" + txpencariantransaksi.getText()+"%'";
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
                String kolom8=rs.getString(8);
                String kolom9=rs.getString(9);
                String kolom10=rs.getString(10);
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8,kolom9,kolom10,};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txpencariantransaksiKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbuku;
    private javax.swing.JButton bthapus;
    private javax.swing.JButton btsimpan;
    private javax.swing.JButton bttambah;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tb_buku;
    private javax.swing.JTable tb_transaksi;
    private javax.swing.JTextField txhargabeli;
    private javax.swing.JTextField txhargajual;
    private javax.swing.JTextField txjumlah;
    private javax.swing.JTextField txkodebuku;
    private javax.swing.JTextField txkodetransaksi;
    private javax.swing.JTextField txnamabuku;
    private javax.swing.JTextField txpencarianbuku;
    private javax.swing.JTextField txpencariantransaksi;
    private javax.swing.JTextField txpenerbit;
    private javax.swing.JTextField txpengarang;
    private javax.swing.JTextField txtahunterbit;
    // End of variables declaration//GEN-END:variables
}
