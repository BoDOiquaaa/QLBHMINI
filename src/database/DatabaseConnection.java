package database;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    
    public Connection getConnection(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyBanHang;user=sa;password=123456;encrypt=false";
            Connection con = DriverManager.getConnection(URL);
            return con;
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString(),"Lỗi",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = db.getConnection();
        
        if(con != null){
            System.out.println("✅ Kết nối thành công!");
            JOptionPane.showMessageDialog(null, "Kết nối thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("❌ Kết nối thất bại!");
        }
    }
}