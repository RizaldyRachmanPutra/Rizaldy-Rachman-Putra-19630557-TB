
package tb_5n;

import java.io.File;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Administrator
 */
public class Koneksi {
    private String jdbcURL="jdbc:mysql://localhost:3306/rental";
    private String username="root";
    private String password="";
    
    
    private DefaultTableModel Modelnya;
    private TableColumn Kolomnya;
    
    
    public Koneksi(){}
    
    public Connection getKoneksiDB() throws SQLException{
        try{
            Driver mysqldriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(mysqldriver);
            System.out.println("Koneksi DB Sukses");
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return DriverManager.getConnection(jdbcURL, username, password);
    }
    
    public boolean DuplicateKey(String NamaTabel, String PrimaryKey, String isiData){
        boolean hasil=false;
        try{
            String SQL="SELECT * FROM "+NamaTabel+" WHERE "+PrimaryKey+" ='"+isiData+"'";
            Statement perintah = getKoneksiDB().createStatement();
            ResultSet hasilData = perintah.executeQuery(SQL);
            
            hasil=hasilData.next();
            
        }catch (Exception e) {
            System.out.println(e.toString());
        }
        return hasil;
    }
    
    public void SimpanAdmin(String username, String password, String id){
        try {
            String SQL ="INSERT INTO admin (id,username,password) VALUES ('"+id+"','"+username+"','"+password+"')";
            
            Statement perintah = getKoneksiDB().createStatement();
            int jumlah=perintah.executeUpdate(SQL);
            
            System.out.println("jumlah data tersimpan:"+jumlah);
            
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    
    public void TampildataAdmin(){
        try{
            Statement perintah= getKoneksiDB().createStatement();
            ResultSet hasil= perintah.executeQuery("SELECT * FROM admin");
            
            while (hasil.next()){
              System.out.println(hasil.getString("username")+"     "+hasil.getString("password")+" "+hasil.getString("level"));  
            }
            
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    
    public void HapusAdmin(String username){
        try {
            String SQL="DELETE FROM admin WHERE username='"+username+"'";
            Statement perintah = getKoneksiDB().createStatement();
            perintah.executeUpdate(SQL);
            
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public void UbahAdmin(String username, String password, String id){
        try {
            String SQL="UPDATE admin SET password='"+password+"', id='"+id+"' WHERE username='"+username+"'";
            Statement perintah = getKoneksiDB().createStatement();
            perintah.executeUpdate(SQL);
            
        }catch (Exception e){
            System.out.println(e.toString());
}
    }
    
    public String getFieldTabel(String[] FieldTabelnya){
        String hasilnya="";
       int deteksiIndexAkhir=FieldTabelnya.length-1;
        try {
           for (int i = 0; i < FieldTabelnya.length; i++){
               
               if (i==deteksiIndexAkhir){
                   hasilnya=hasilnya+FieldTabelnya[i];
               } else {
                    hasilnya=hasilnya+FieldTabelnya[i]+",";
               }
              
           }
        }catch (Exception e){
            System.out.println(e.toString());
}
    return "("+hasilnya+")";
    
}

       public String getIsiTabel(String[] IsiTabelnya){
           String hasilnya="";
       int DeteksiIndex=IsiTabelnya.length-1;
       try {
      for (int i = 0; i < IsiTabelnya.length; i++){
           if (i==DeteksiIndex){
                   hasilnya=hasilnya+"'"+IsiTabelnya[i]+"'";
               } else {
                   hasilnya=hasilnya+"'"+IsiTabelnya[i]+"',";
               }
      }
       }catch (Exception e){
           System.out.println(e.toString());
}
return "("+hasilnya+")";
}
       
         public String getFieldValueEdit(String[] Field, String[] value){
             String hasil="";
             int deteksi = Field.length-1;
           try {
              for (int i =0; i< Field.length; i++){
                 if (i ==deteksi){
                     hasil=hasil +Field[i]+" ='"+value[i]+"'";
                 } else{
                     hasil=hasil +Field[i]+" ='"+value[i]+"',";
                 } 
              }  
               
           }catch (Exception e){
           System.out.println(e.toString());
               
           }
        return hasil;
       }
         
             public void UbahDinamis(String NamaTabel, String PrimaryKey, String IsiPrimary, String[] Field, String[] value){
               try {
                   String SQLUbah = "UPDATE "+NamaTabel+" SET "+getFieldValueEdit(Field, value)+" WHERE "+PrimaryKey+"='"+IsiPrimary+"'";
                   Statement perintah=getKoneksiDB().createStatement();
                   perintah.executeUpdate(SQLUbah);
                   perintah.close();
                   System.out.println("Data Berhasil Diubah");
               }  catch (Exception e){
           System.out.println(e.toString());
             } 
}
       
       public void SimpanDinamis(String NamaTabel, String[] Fieldnya, String[] Isinya){
           try {
               String SQLSave = "INSERT INTO "+NamaTabel+" "+getFieldTabel(Fieldnya)+" VALUES "+getIsiTabel(Isinya);
               Statement perintah=getKoneksiDB().createStatement();
               perintah.executeUpdate(SQLSave);
               perintah.close();
               
               System.out.println("Data Berhasil Disimpan");
           } catch (Exception e){
           System.out.println(e.toString());
}
               
 }
       
       public void HapusDinamis(String NamaTabel, String PK, String isi){
           try {
               
               String SQL="DELETE FROM "+NamaTabel+" WHERE "+PK+"='"+isi+"'";
               Statement perintah =getKoneksiDB().createStatement();
               perintah.executeUpdate(SQL);
               perintah.close();
               JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    
                   
               
           } catch (Exception e){
           System.out.println(e.toString());
}
       }
       
       public void settingJudulTabel(JTable Tabelnya, String[] JudulKolom){
           try {
                Modelnya = new DefaultTableModel();
                Tabelnya.setModel(Modelnya);
                for (int i=0; i< JudulKolom.length;i++){
                    Modelnya.addColumn(JudulKolom[i]);
                }
                
           }catch (Exception e){
           System.out.println(e.toString());
}
         
       }
       
       public void settingLebarKolom(JTable Tabelnya, int[] Kolom){
           try {
               Tabelnya.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
               for (int i = 0; i < Kolom.length; i++){
                   Kolomnya =Tabelnya.getColumnModel().getColumn(i);
               Kolomnya.setPreferredWidth(Kolom[i]);
                   
               }
                 
                      
           }catch (Exception e){
           System.out.println(e.toString());
}
       }
         
       
       public Object[][] isiTabel(String SQL, int jumlah){
           Object[][] data = null;
           try {
               Statement perintah = getKoneksiDB().createStatement();
               ResultSet dataset = perintah.executeQuery(SQL);
               dataset.last();
               int baris = dataset.getRow();
               dataset.beforeFirst();
               int j=0;
               
               data = new Object[baris][jumlah];
               
               while (dataset.next()){
                   for (int i=0; i< jumlah; i++){
                       data[j][i]=dataset.getString(i+1);
                   }
                   j++;
               }              
           }catch (Exception e){
    
}
           return data;
       }
       
       public void tampilTabel(JTable Tabelnya, String SQL, String[] Judul){
      try {
        Tabelnya.setModel(new DefaultTableModel(isiTabel(SQL,Judul.length), Judul));
           }catch (Exception e){
           System.out.println(e.toString());
}
       }
       
       public void tampilLaporan(String laporanFile, String SQL){
       try{
           File file = new File(laporanFile);
           JasperDesign jasDes = JRXmlLoader.load(file);
           
           
           JRDesignQuery sqlQuery = new JRDesignQuery();
           sqlQuery.setText(SQL);
           jasDes.setQuery(sqlQuery);
           
           JasperReport JR = JasperCompileManager.compileReport(jasDes);
           JasperPrint JP = JasperFillManager.fillReport(JR,null,getKoneksiDB());
           JasperViewer.viewReport(JP);
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,e.toString());
       }
}

}