/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.DatabaseConnection;
import model.TaiKhoan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author khaid
 */
public class TaiKhoanDAO {
    private DatabaseConnection dbConnection;
    
    public TaiKhoanDAO() {
        dbConnection = new DatabaseConnection();
    }
    
    // Đăng nhập
    public TaiKhoan dangNhap(String tenDangNhap, String matKhau) {
        String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ? AND MatKhau = ? AND TrangThai = 1";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, tenDangNhap);
            pstmt.setString(2, matKhau);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setMaTK(rs.getInt("MaTK"));
                tk.setTenDangNhap(rs.getString("TenDangNhap"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setHoTen(rs.getString("HoTen"));
                tk.setVaiTro(rs.getString("VaiTro"));
                tk.setTrangThai(rs.getBoolean("TrangThai"));
                tk.setNgayTao(rs.getTimestamp("NgayTao"));
                return tk;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Lấy tất cả tài khoản
    public List<TaiKhoan> getAllTaiKhoan() {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan ORDER BY NgayTao DESC";
        
        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setMaTK(rs.getInt("MaTK"));
                tk.setTenDangNhap(rs.getString("TenDangNhap"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setHoTen(rs.getString("HoTen"));
                tk.setVaiTro(rs.getString("VaiTro"));
                tk.setTrangThai(rs.getBoolean("TrangThai"));
                tk.setNgayTao(rs.getTimestamp("NgayTao"));
                list.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Thêm tài khoản
    public boolean themTaiKhoan(TaiKhoan tk) {
        String sql = "INSERT INTO TaiKhoan(TenDangNhap, MatKhau, HoTen, VaiTro) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, tk.getTenDangNhap());
            pstmt.setString(2, tk.getMatKhau());
            pstmt.setString(3, tk.getHoTen());
            pstmt.setString(4, tk.getVaiTro());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Cập nhật tài khoản
    public boolean suaTaiKhoan(TaiKhoan tk) {
        String sql = "UPDATE TaiKhoan SET MatKhau = ?, HoTen = ?, VaiTro = ?, TrangThai = ? WHERE MaTK = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, tk.getMatKhau());
            pstmt.setString(2, tk.getHoTen());
            pstmt.setString(3, tk.getVaiTro());
            pstmt.setBoolean(4, tk.isTrangThai());
            pstmt.setInt(5, tk.getMaTK());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Xóa tài khoản
    public boolean xoaTaiKhoan(int maTK) {
        String sql = "DELETE FROM TaiKhoan WHERE MaTK = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, maTK);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Đổi mật khẩu
    public boolean doiMatKhau(int maTK, String matKhauCu, String matKhauMoi) {
        String sql = "UPDATE TaiKhoan SET MatKhau = ? WHERE MaTK = ? AND MatKhau = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, matKhauMoi);
            pstmt.setInt(2, maTK);
            pstmt.setString(3, matKhauCu);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}