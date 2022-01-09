/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_penjualan_buku;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author FAWKES
 */
public class KASIR extends javax.swing.JInternalFrame {

    private DefaultTableModel TabModel;
    Connection conn;
    Statement st;
    ResultSet rs;
    
    /**
     * Creates new form rockwell_what
     */
    public KASIR() {
        initComponents();
        txstok.setVisible(false);
        txsubtotal.setVisible(false);
        labelsubtotal.setVisible(false);
        labelstok.setVisible(false);
        SiapIsi(false);
        TombolNormal();
        
        Object header[]={"KODE BUKU","NAMA BUKU","PENGARANG","PENERBIT","TERBIT","HARGA","JUMLAH","SUBTOTAL"};
        TabModel=new DefaultTableModel(null, header);
    }
    
    public Connection setKoneksi(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_tokobuku","root","");
            st=conn.createStatement();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Koneksi Gagal:"+e);
        }
        return conn;
    }
    
    private void SiapIsi(boolean a){
        txkodetransaksi.setEnabled(a);
        txkodebuku.setEnabled(a);
        txnamabuku.setEnabled(a);
        txpengarang.setEnabled(a);
        txpenerbit.setEnabled(a);
        txtahunterbit.setEnabled(a);
        txharga.setEnabled(a);
        txjumlah.setEnabled(a);
        txsubtotal.setEnabled(a);
        txtotal.setEnabled(a);
        txbayar.setEnabled(a);
        txkembalian.setEnabled(a);
        txstok.setEnabled(a);
        
    }
    
    private void TombolNormal(){
        bttambah.setEnabled(true);
        btaddchart.setEnabled(false);
        btbayar.setEnabled(false);        
        bttransaksi.setEnabled(false);
        btpilihbuku.setEnabled(false);
        bthapuschart.setEnabled(false);
        
    }
    
    private void bersih(){
        txkodetransaksi.setText("");
        txkodebuku.setText("");
        txnamabuku.setText("");
        txpengarang.setText("");
        txpenerbit.setText("");
        txtahunterbit.setText("");
        txharga.setText("");
        txjumlah.setText("");
        txsubtotal.setText("0");
        txtotal.setText("0");
        txbayar.setText("0");
        txkembalian.setText("");
        txstok.setText("");
        
    }
    
    private void kodetransaksi(){
       try{
           setKoneksi();
           String sql="select right(kodetransaksi,2)+1 from tb_penjualan";
           ResultSet rs=st.executeQuery(sql);
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
    
    
    
    private void bayar(){
        setKoneksi();
        try{
           Date skrg=new Date();
           SimpleDateFormat frm=new SimpleDateFormat("yyyy-MM-dd");
           String tanggal=frm.format(skrg); 
 
            int t = tabelchart.getRowCount();
             for(int i=0;i<t;i++)    
            {
            String kodebuku=tabelchart.getValueAt(i, 0).toString();
            String namabuku=tabelchart.getValueAt(i, 1).toString();
            String pengarang=tabelchart.getValueAt(i, 2).toString();
            String penerbit=tabelchart.getValueAt(i, 3).toString();
            String terbit=tabelchart.getValueAt(i, 4).toString();
            int harga= Integer.parseInt(tabelchart.getValueAt(i, 5).toString());
            int jml= Integer.parseInt(tabelchart.getValueAt(i, 6).toString());            
            int subtot= Integer.parseInt(tabelchart.getValueAt(i, 7).toString());
         
            String sql ="insert into tb_penjualan values('"+txkodetransaksi.getText()
                    +"','"+kodebuku+"','"
                    +namabuku+"','"
                    +pengarang+"','"
                    +penerbit+"','"
                    +terbit+"','"
                    +tanggal+"','"
                    +harga+"','"
                    +jml+"','"
                    +subtot+"','"
                    +txtotal.getText()+"','"
                    +txbayar.getText()+"','"
                    +txkembalian.getText()+"')";
            
             st.executeUpdate(sql);
             
            }         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SIMPAN TRANSAKSI PENJUALAN GAGAL");
        }
        
    }
    
    
    public void tabeltransaksi(){
        Object header[]={"KTR","KDB","NAMA","PENGARANG","PENERBIT","TERBIT","TANGGAL","HARGA","JUMLAH","SUBTOTAL","TOTAL","BAYAR","KEMBALIAN"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tabeltransaksi.setModel(data);
        setKoneksi();
        String sql="select*from tb_penjualan";
        try {
            ResultSet rs=st.executeQuery(sql);
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
                String kolom11=rs.getString(11);
                String kolom12=rs.getString(12);
                String kolom13=rs.getString(13);
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8,kolom9,kolom10,kolom11,kolom12,kolom13};
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
            ResultSet rs=st.executeQuery(sql);
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
    
    public void hitungstok(){
        int jumlahbeli=Integer.parseInt(txjumlah.getText());
        int stok=Integer.parseInt(txstok.getText());
        
        int total=jumlahbeli-stok;
        txstok.setText(Integer.toString(total));
    }
    
    public void ambildata() {
        try {
            tabelchart.setModel(TabModel);
                String kolom1 = txkodebuku.getText();
                String kolom2 = txnamabuku.getText();
                String kolom3 = txpengarang.getText();
                String kolom4 = txpenerbit.getText();
                String kolom5 = txtahunterbit.getText();
                String kolom6 = txharga.getText();
                String kolom7 = txjumlah.getText();
                String kolom8 = txsubtotal.getText();
                String[] kolom = {kolom1, kolom2, kolom3, kolom4, kolom5,kolom6,kolom7,kolom8};
                TabModel.addRow(kolom);
          }
          catch (Exception ex) {
              JOptionPane.showMessageDialog(null, "Data gagal disimpan");
          }     
    }
    
    public void nota(){
        try {
            String NamaFile = "src/report/notapenjualan.jasper";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            java.sql.Connection setKoneksi = DriverManager.getConnection("jdbc:mysql://localhost/db_tokobuku","root","");
            HashMap param = new HashMap();
            param.put("ptrans",txkodetransaksi.getText());
            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, conn);
            JasperViewer.viewReport(JPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat dicetak!","Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void hapusdatadaritabel() {
        int a = tabelchart.getSelectedRow();
        if(a == -1){
        }TabModel.removeRow(a);
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
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_buku = new javax.swing.JTable();
        txpencarianbuku = new javax.swing.JTextField();
        jDialogtabeltransaksi = new javax.swing.JDialog();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabeltransaksi = new javax.swing.JTable();
        txpencariantransaksi = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txkodetransaksi = new javax.swing.JTextField();
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
        txharga = new javax.swing.JTextField();
        txjumlah = new javax.swing.JTextField();
        labelsubtotal = new javax.swing.JLabel();
        labelstok = new javax.swing.JLabel();
        txsubtotal = new javax.swing.JTextField();
        txstok = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        bttambah = new javax.swing.JButton();
        btpilihbuku = new javax.swing.JButton();
        btaddchart = new javax.swing.JButton();
        bthapuschart = new javax.swing.JButton();
        bttransaksi = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelchart = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtotal = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txbayar = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txkembalian = new javax.swing.JTextField();
        btbayar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();

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

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));

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

        txpencarianbuku.setBackground(new java.awt.Color(204, 204, 204));
        txpencarianbuku.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txpencarianbuku.setForeground(new java.awt.Color(51, 51, 51));
        txpencarianbuku.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txpencarianbuku.setText("KETIK KODE BUKU ATAU NAMA BUKU UNTUK MELAKUKAN PENCARIAN");
        txpencarianbuku.setPreferredSize(new java.awt.Dimension(87, 30));
        txpencarianbuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txpencarianbukuKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txpencarianbuku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
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
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jDialogtabeltransaksi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialogtabeltransaksi.setBackground(new java.awt.Color(0, 0, 51));
        jDialogtabeltransaksi.setMinimumSize(new java.awt.Dimension(1079, 430));
        jDialogtabeltransaksi.setModal(true);
        jDialogtabeltransaksi.setResizable(false);

        jInternalFrame2.setTitle("TABEL TRANSAKSI");
        jInternalFrame2.setPreferredSize(new java.awt.Dimension(694, 430));
        jInternalFrame2.setVisible(true);
        jInternalFrame2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jInternalFrame2MouseClicked(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));

        tabeltransaksi.setAutoCreateRowSorter(true);
        tabeltransaksi.setBackground(new java.awt.Color(204, 204, 204));
        tabeltransaksi.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        tabeltransaksi.setForeground(new java.awt.Color(51, 51, 51));
        tabeltransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tabeltransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeltransaksiMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabeltransaksi);

        txpencariantransaksi.setBackground(new java.awt.Color(204, 204, 204));
        txpencariantransaksi.setForeground(new java.awt.Color(51, 51, 51));
        txpencariantransaksi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txpencariantransaksi.setText("KOLOM PENCARIAN");
        txpencariantransaksi.setPreferredSize(new java.awt.Dimension(87, 30));
        txpencariantransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txpencariantransaksiKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txpencariantransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1065, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txpencariantransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialogtabeltransaksiLayout = new javax.swing.GroupLayout(jDialogtabeltransaksi.getContentPane());
        jDialogtabeltransaksi.getContentPane().setLayout(jDialogtabeltransaksiLayout);
        jDialogtabeltransaksiLayout.setHorizontalGroup(
            jDialogtabeltransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame2, javax.swing.GroupLayout.DEFAULT_SIZE, 1079, Short.MAX_VALUE)
        );
        jDialogtabeltransaksiLayout.setVerticalGroup(
            jDialogtabeltransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("KASIR");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Kode Transaksi :");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Kode Buku         :");

        txkodetransaksi.setEditable(false);
        txkodetransaksi.setBackground(new java.awt.Color(51, 51, 51));
        txkodetransaksi.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        txkodetransaksi.setForeground(new java.awt.Color(204, 204, 204));
        txkodetransaksi.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txkodebuku.setEditable(false);
        txkodebuku.setBackground(new java.awt.Color(51, 51, 51));
        txkodebuku.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        txkodebuku.setForeground(new java.awt.Color(204, 204, 204));
        txkodebuku.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Nama Buku :");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Pengarang  :");

        txnamabuku.setEditable(false);
        txnamabuku.setBackground(new java.awt.Color(51, 51, 51));
        txnamabuku.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        txnamabuku.setForeground(new java.awt.Color(204, 204, 204));
        txnamabuku.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txpengarang.setEditable(false);
        txpengarang.setBackground(new java.awt.Color(51, 51, 51));
        txpengarang.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        txpengarang.setForeground(new java.awt.Color(204, 204, 204));
        txpengarang.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Penerbit :");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Terbit      :");

        txpenerbit.setEditable(false);
        txpenerbit.setBackground(new java.awt.Color(51, 51, 51));
        txpenerbit.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        txpenerbit.setForeground(new java.awt.Color(204, 204, 204));
        txpenerbit.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtahunterbit.setEditable(false);
        txtahunterbit.setBackground(new java.awt.Color(51, 51, 51));
        txtahunterbit.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        txtahunterbit.setForeground(new java.awt.Color(204, 204, 204));
        txtahunterbit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtahunterbit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtahunterbitActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Harga   :");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Jumlah :");

        txharga.setEditable(false);
        txharga.setBackground(new java.awt.Color(51, 51, 51));
        txharga.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        txharga.setForeground(new java.awt.Color(204, 204, 204));
        txharga.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txjumlah.setBackground(new java.awt.Color(204, 204, 204));
        txjumlah.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        txjumlah.setForeground(new java.awt.Color(51, 51, 51));
        txjumlah.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelsubtotal.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        labelsubtotal.setForeground(new java.awt.Color(204, 204, 204));
        labelsubtotal.setText("Subtotal :");

        labelstok.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        labelstok.setForeground(new java.awt.Color(204, 204, 204));
        labelstok.setText("Stok       :");

        txsubtotal.setEditable(false);
        txsubtotal.setBackground(new java.awt.Color(51, 51, 51));
        txsubtotal.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        txsubtotal.setForeground(new java.awt.Color(204, 204, 204));
        txsubtotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txstok.setEditable(false);
        txstok.setBackground(new java.awt.Color(51, 51, 51));
        txstok.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        txstok.setForeground(new java.awt.Color(204, 204, 204));
        txstok.setHorizontalAlignment(javax.swing.JTextField.CENTER);

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txkodetransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txnamabuku))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txkodebuku, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txpengarang)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txpenerbit)
                    .addComponent(txtahunterbit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelstok)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txstok, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txharga, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelsubtotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel4});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel5, jLabel6});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txkodebuku, txkodetransaksi});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel7, jLabel8});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelstok, labelsubtotal});

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
                    .addComponent(txharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelsubtotal)
                    .addComponent(txsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txkodebuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txpengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtahunterbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelstok)
                    .addComponent(txstok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        btpilihbuku.setBackground(new java.awt.Color(0, 0, 0));
        btpilihbuku.setForeground(new java.awt.Color(204, 204, 204));
        btpilihbuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/tbbuku.png"))); // NOI18N
        btpilihbuku.setText("PILIH BUKU");
        btpilihbuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btpilihbukuActionPerformed(evt);
            }
        });

        btaddchart.setBackground(new java.awt.Color(0, 0, 0));
        btaddchart.setForeground(new java.awt.Color(204, 204, 204));
        btaddchart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/addchart.png"))); // NOI18N
        btaddchart.setText("ADD CHART");
        btaddchart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaddchartActionPerformed(evt);
            }
        });

        bthapuschart.setBackground(new java.awt.Color(0, 0, 0));
        bthapuschart.setForeground(new java.awt.Color(204, 204, 204));
        bthapuschart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/hapuschart.png"))); // NOI18N
        bthapuschart.setText("HAPUS CHART");
        bthapuschart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapuschartActionPerformed(evt);
            }
        });

        bttransaksi.setBackground(new java.awt.Color(0, 0, 0));
        bttransaksi.setForeground(new java.awt.Color(204, 204, 204));
        bttransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/tbtransaksi.png"))); // NOI18N
        bttransaksi.setText("TB TRANSAKSI");
        bttransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttransaksiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(bttambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btpilihbuku)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btaddchart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bthapuschart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btaddchart, bthapuschart, btpilihbuku, bttambah, bttransaksi});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttambah)
                    .addComponent(btpilihbuku)
                    .addComponent(btaddchart)
                    .addComponent(bthapuschart)
                    .addComponent(bttransaksi))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tabelchart.setBackground(new java.awt.Color(204, 204, 204));
        tabelchart.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        tabelchart.setForeground(new java.awt.Color(51, 51, 51));
        tabelchart.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelchart);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("TOTAL :");

        txtotal.setEditable(false);
        txtotal.setBackground(new java.awt.Color(51, 51, 51));
        txtotal.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtotal.setForeground(new java.awt.Color(255, 0, 0));
        txtotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("BAYAR :");

        txbayar.setBackground(new java.awt.Color(204, 204, 204));
        txbayar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txbayar.setForeground(new java.awt.Color(255, 0, 0));
        txbayar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txbayarKeyPressed(evt);
            }
        });

        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("KEMBALIAN :");

        txkembalian.setEditable(false);
        txkembalian.setBackground(new java.awt.Color(51, 51, 51));
        txkembalian.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txkembalian.setForeground(new java.awt.Color(255, 0, 0));
        txkembalian.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btbayar.setBackground(new java.awt.Color(0, 0, 0));
        btbayar.setForeground(new java.awt.Color(204, 204, 204));
        btbayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconn/SIMPAN.png"))); // NOI18N
        btbayar.setText("BAYAR");
        btbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbayarActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("\"TEKAN ENTER SETELAH ISI BAYAR, LALU TEKAN TOMBOL BAYAR UNTUK MENYIMPAN TRANSAKSI\"");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 21, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btbayar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
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
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void txbayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txbayarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int total=Integer.parseInt(txtotal.getText());
            int bayar=Integer.parseInt(txbayar.getText());
            if(bayar<total){
                JOptionPane.showMessageDialog(null, "Jumlah bayar tidak mencukupi");
                txbayar.requestFocus();
            } else{
                int kembali=bayar-total;
                txkembalian.setText(Integer.toString(kembali));
            }
        }
    }//GEN-LAST:event_txbayarKeyPressed

    private void btbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbayarActionPerformed
        // TODO add your handling code here:
        if(txkodetransaksi.getText().equals("")
            ||txkodebuku.getText().equals("")
            ||txnamabuku.getText().equals("")
                ||txpengarang.getText().equals("")
                ||txpenerbit.getText().equals("")
                ||txtahunterbit.getText().equals("")
            ||txharga.getText().equals("")
            ||txjumlah.getText().equals("")
            ||txsubtotal.getText().equals("")
            ||txtotal.getText().equals("")
            ||txbayar.getText().equals("")
            ||txkembalian.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lengkapi inputan penjualan barang");
        } else{
            bayar();
            int pesan=JOptionPane.showConfirmDialog(null, "Print Out Nota?","Print",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

            if(pesan==JOptionPane.YES_OPTION){
                nota();
            }else {
                JOptionPane.showMessageDialog(null, "Simpan Transaksi Berhasil");
            }
            bersih();
        }
    }//GEN-LAST:event_btbayarActionPerformed

    private void txtahunterbitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtahunterbitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtahunterbitActionPerformed

    private void tb_bukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_bukuMouseClicked
        // TODO add your handling code here:
        int baris = tb_buku.getSelectedRow();
        txkodebuku.setText(tb_buku.getModel().getValueAt(baris, 0).toString());
        txnamabuku.setText(tb_buku.getModel().getValueAt(baris, 1).toString());
        txpengarang.setText(tb_buku.getModel().getValueAt(baris, 2).toString());
        txpenerbit.setText(tb_buku.getModel().getValueAt(baris, 3).toString());
        txtahunterbit.setText(tb_buku.getModel().getValueAt(baris, 4).toString());
        txharga.setText(tb_buku.getModel().getValueAt(baris, 5).toString());
        txstok.setText(tb_buku.getModel().getValueAt(baris, 6).toString());
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
            ResultSet rs=st.executeQuery(sql);
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

    private void tabeltransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeltransaksiMouseClicked
        // TODO add your handling code here:
        int baris = tabeltransaksi.getSelectedRow();
        txkodetransaksi.setText(tabeltransaksi.getModel().getValueAt(baris, 0).toString());
        txkodebuku.setText(tabeltransaksi.getModel().getValueAt(baris, 1).toString());
        txnamabuku.setText(tabeltransaksi.getModel().getValueAt(baris, 3).toString());
        txpengarang.setText(tabeltransaksi.getModel().getValueAt(baris, 4).toString());
        txpenerbit.setText(tabeltransaksi.getModel().getValueAt(baris, 5).toString());
        txtahunterbit.setText(tabeltransaksi.getModel().getValueAt(baris, 6).toString());
        txharga.setText(tabeltransaksi.getModel().getValueAt(baris, 7).toString());
        txjumlah.setText(tabeltransaksi.getModel().getValueAt(baris, 8).toString());
        txsubtotal.setText(tabeltransaksi.getModel().getValueAt(baris, 9).toString());
        txtotal.setText(tabeltransaksi.getModel().getValueAt(baris, 10).toString());
        txbayar.setText(tabeltransaksi.getModel().getValueAt(baris, 11).toString());
        txkembalian.setText(tabeltransaksi.getModel().getValueAt(baris, 12).toString());
        jDialogtabeltransaksi.dispose();
        nota();
    }//GEN-LAST:event_tabeltransaksiMouseClicked

    private void txpencariantransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txpencariantransaksiKeyPressed
        // TODO add your handling code here:
        Object header[]={"KTR","KDB","NAMA","PENGARANG","PENERBIT","TERBIT","TANGGAL","HARGA","JUMLAH","SUBTOTAL","TOTAL","BAYAR","KEMBALIAN"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tabeltransaksi.setModel(data);
        setKoneksi();
        String sql="Select * from tb_penjualan where kodetransaksi like '%" + txpencariantransaksi.getText() + "%'" + "or kodebuku like '%" + txpencariantransaksi.getText()+"%'";
        try {
            ResultSet rs=st.executeQuery(sql);
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
                String kolom11=rs.getString(11);
                String kolom12=rs.getString(12);
                String kolom13=rs.getString(13);
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8,kolom9,kolom10,kolom11,kolom12,kolom13};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txpencariantransaksiKeyPressed

    private void jInternalFrame2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jInternalFrame2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jInternalFrame2MouseClicked

    private void bttambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttambahActionPerformed
        // TODO add your handling code here:
        if(bttambah.getText().equalsIgnoreCase("TAMBAH")){
            bttambah.setText("REFRESH");
            bersih();
            SiapIsi(true);

            kodetransaksi();
            btaddchart.setEnabled(true);
            bttransaksi.setEnabled(true);
            bttambah.setEnabled(true);
            btbayar.setEnabled(true);
            btpilihbuku.setEnabled(true);
            bthapuschart.setEnabled(true);

        } else{
            bttambah.setText("TAMBAH");
            bersih();
            SiapIsi(false);
            TombolNormal();
            //tabelinventory;
            TabModel.getDataVector().removeAllElements();
            TabModel.fireTableDataChanged();
        }
    }//GEN-LAST:event_bttambahActionPerformed

    private void btpilihbukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btpilihbukuActionPerformed
        // TODO add your handling code here:        // TODO add your handling code here:
        jDialog1.setLocationRelativeTo(null);
        tb_buku();
        jDialog1.setVisible(true);
    }//GEN-LAST:event_btpilihbukuActionPerformed

    private void btaddchartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaddchartActionPerformed
        // TODO add your handling code here:
        int harga=Integer.parseInt(txharga.getText());
        int jml=Integer.parseInt(txjumlah.getText());
        int stok=Integer.parseInt(txstok.getText());
        int total=Integer.parseInt(txtotal.getText());

        if(jml>stok){
            JOptionPane.showMessageDialog(null, "Stok barang tidak mencukupi");
        }else{

            int subtot=harga*jml;
            txsubtotal.setText(Integer.toString(subtot));

            int hasilstok=stok-jml;
            txstok.setText(Integer.toString(hasilstok));

            int totbay=total+(harga*jml);
            txtotal.setText(Integer.toString(totbay));

            ambildata();

        }
    }//GEN-LAST:event_btaddchartActionPerformed

    private void bthapuschartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapuschartActionPerformed
        // TODO add your handling code here:
        int baris = tabelchart.getSelectedRow();
        int jml=Integer.parseInt(tabelchart.getModel().getValueAt(baris, 7).toString());
        int total=Integer.parseInt(txtotal.getText());
        int harga=Integer.parseInt(tabelchart.getModel().getValueAt(baris, 6).toString());

        int totbay=total-(harga*jml);
        txtotal.setText(Integer.toString(totbay));
        hapusdatadaritabel();
    }//GEN-LAST:event_bthapuschartActionPerformed

    private void bttransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttransaksiActionPerformed
        // TODO add your handling code here:
        jDialogtabeltransaksi.setLocationRelativeTo(null);
        tabeltransaksi();
        jDialogtabeltransaksi.setVisible(true);
    }//GEN-LAST:event_bttransaksiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btaddchart;
    private javax.swing.JButton btbayar;
    private javax.swing.JButton bthapuschart;
    private javax.swing.JButton btpilihbuku;
    private javax.swing.JButton bttambah;
    private javax.swing.JButton bttransaksi;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialogtabeltransaksi;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelstok;
    private javax.swing.JLabel labelsubtotal;
    private javax.swing.JTable tabelchart;
    private javax.swing.JTable tabeltransaksi;
    private javax.swing.JTable tb_buku;
    private javax.swing.JTextField txbayar;
    private javax.swing.JTextField txharga;
    private javax.swing.JTextField txjumlah;
    private javax.swing.JTextField txkembalian;
    private javax.swing.JTextField txkodebuku;
    private javax.swing.JTextField txkodetransaksi;
    private javax.swing.JTextField txnamabuku;
    private javax.swing.JTextField txpencarianbuku;
    private javax.swing.JTextField txpencariantransaksi;
    private javax.swing.JTextField txpenerbit;
    private javax.swing.JTextField txpengarang;
    private javax.swing.JTextField txstok;
    private javax.swing.JTextField txsubtotal;
    private javax.swing.JTextField txtahunterbit;
    private javax.swing.JTextField txtotal;
    // End of variables declaration//GEN-END:variables
}
