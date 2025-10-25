/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import database.DatabaseConnection;
import model.HoaDon;
import model.ChiTietHD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author khaid
 */
public class HoaDonDAO {
    private DatabaseConnection dbConnection;
    
    public HoaDonDAO() {
        dbConnection = new DatabaseConnection();
    }
    
    // Tạo hóa đơn mới (trả về mã hóa đơn)
    public int taoHoaDon(int maKH) {
        String sql = "INSERT INTO HoaDon(MaKH) VALUES (?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, maKH);
            pstmt.executeUpdate();
            
            // Lấy mã hóa đơn vừa tạo
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    // Thêm chi tiết hóa đơn
    public boolean themChiTietHD(int maHD, int maSP, int soLuong) {
        String sql = "{CALL sp_ThemChiTietHD(?, ?, ?)}";
        
        try (Connection conn = dbConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            
            cstmt.setInt(1, maHD);
            cstmt.setInt(2, maSP);
            cstmt.setInt(3, soLuong);
            
            return cstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Lấy tất cả hóa đơn
    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT hd.*, kh.TenKH FROM HoaDon hd " +
                     "LEFT JOIN KhachHang kh ON hd.MaKH = kh.MaKH " +
                     "ORDER BY hd.MaHD DESC";
        
        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setMaKH(rs.getInt("MaKH"));
                hd.setNgayLap(rs.getDate("NgayLap"));
                hd.setGioLap(rs.getTime("GioLap"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTenKH(rs.getString("TenKH"));
                list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Lấy chi tiết hóa đơn
    public List<ChiTietHD> getChiTietHoaDon(int maHD) {
        List<ChiTietHD> list = new ArrayList<>();
        String sql = "SELECT ct.*, sp.TenSP FROM ChiTietHD ct " +
                     "INNER JOIN SanPham sp ON ct.MaSP = sp.MaSP " +
                     "WHERE ct.MaHD = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, maHD);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ChiTietHD ct = new ChiTietHD();
                ct.setMaCT(rs.getInt("MaCT"));
                ct.setMaHD(rs.getInt("MaHD"));
                ct.setMaSP(rs.getInt("MaSP"));
                ct.setSoLuong(rs.getInt("SoLuong"));
                ct.setDonGia(rs.getDouble("DonGia"));
                ct.setThanhTien(rs.getDouble("ThanhTien"));
                ct.setTenSP(rs.getString("TenSP"));
                list.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Lấy hóa đơn theo mã
    public HoaDon getHoaDonByMa(int maHD) {
        String sql = "SELECT hd.*, kh.TenKH, kh.SDT, kh.DiaChi FROM HoaDon hd " +
                     "LEFT JOIN KhachHang kh ON hd.MaKH = kh.MaKH WHERE hd.MaHD = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, maHD);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setMaKH(rs.getInt("MaKH"));
                hd.setNgayLap(rs.getDate("NgayLap"));
                hd.setGioLap(rs.getTime("GioLap"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTenKH(rs.getString("TenKH"));
                return hd;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Báo cáo doanh thu theo ngày
    public List<Object[]> baoCaoDoanhThuTheoNgay(Date tuNgay, Date denNgay) {
        List<Object[]> list = new ArrayList<>();
        String sql = "{CALL sp_DoanhThuTheoNgay(?, ?)}";
        
        try (Connection conn = dbConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            
            cstmt.setDate(1, tuNgay);
            cstmt.setDate(2, denNgay);
            ResultSet rs = cstmt.executeQuery();
            
            while (rs.next()) {
                Object[] row = new Object[3];
                row[0] = rs.getDate("Ngay");
                row[1] = rs.getInt("SoHoaDon");
                row[2] = rs.getDouble("DoanhThu");
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Báo cáo doanh thu theo tháng
    public List<Object[]> baoCaoDoanhThuTheoThang(int nam) {
        List<Object[]> list = new ArrayList<>();
        String sql = "{CALL sp_DoanhThuTheoThang(?)}";
        
        try (Connection conn = dbConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            
            cstmt.setInt(1, nam);
            ResultSet rs = cstmt.executeQuery();
            
            while (rs.next()) {
                Object[] row = new Object[3];
                row[0] = rs.getInt("Thang");
                row[1] = rs.getInt("SoHoaDon");
                row[2] = rs.getDouble("DoanhThu");
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}