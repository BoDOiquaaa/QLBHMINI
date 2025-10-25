/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import database.DatabaseConnection;
import model.KhachHang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author khaid
 */
public class KhachHangDAO {
    private DatabaseConnection dbConnection;
    
    public KhachHangDAO() {
        dbConnection = new DatabaseConnection();
    }
    
    // Lấy tất cả khách hàng
    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang";
        
        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getInt("MaKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setSdt(rs.getString("SDT"));
                kh.setDiaChi(rs.getString("DiaChi"));
                list.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Tìm khách hàng theo mã
    public KhachHang getKhachHangByMa(int maKH) {
        String sql = "SELECT * FROM KhachHang WHERE MaKH = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, maKH);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getInt("MaKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setSdt(rs.getString("SDT"));
                kh.setDiaChi(rs.getString("DiaChi"));
                return kh;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Thêm khách hàng mới
    public boolean themKhachHang(KhachHang kh) {
        String sql = "INSERT INTO KhachHang(TenKH, SDT, DiaChi) VALUES (?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, kh.getTenKH());
            pstmt.setString(2, kh.getSdt());
            pstmt.setString(3, kh.getDiaChi());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Cập nhật khách hàng
    public boolean suaKhachHang(KhachHang kh) {
        String sql = "UPDATE KhachHang SET TenKH = ?, SDT = ?, DiaChi = ? WHERE MaKH = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, kh.getTenKH());
            pstmt.setString(2, kh.getSdt());
            pstmt.setString(3, kh.getDiaChi());
            pstmt.setInt(4, kh.getMaKH());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Xóa khách hàng
    public boolean xoaKhachHang(int maKH) {
        String sql = "DELETE FROM KhachHang WHERE MaKH = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, maKH);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Tìm kiếm khách hàng theo tên hoặc SĐT
    public List<KhachHang> timKiemKhachHang(String keyword) {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang WHERE TenKH LIKE ? OR SDT LIKE ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getInt("MaKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setSdt(rs.getString("SDT"));
                kh.setDiaChi(rs.getString("DiaChi"));
                list.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    // Tìm kiếm khách hàng theo địa chỉ
public List<KhachHang> timKiemKhachHangTheoDiaChi(String keyword) {
    List<KhachHang> list = new ArrayList<>();
    String sql = "SELECT * FROM KhachHang WHERE DiaChi LIKE ?";
    
    try (Connection conn = dbConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, "%" + keyword + "%");
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            KhachHang kh = new KhachHang();
            kh.setMaKH(rs.getInt("MaKH"));
            kh.setTenKH(rs.getString("TenKH"));
            kh.setSdt(rs.getString("SDT"));
            kh.setDiaChi(rs.getString("DiaChi"));
            list.add(kh);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
}