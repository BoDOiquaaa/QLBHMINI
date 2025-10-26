package model;

import java.sql.Date;
import java.sql.Time;

public class HoaDon {
    private int maHD;
    private int maKH;
    private Date ngayLap;
    private Time gioLap;
    private double tongTien;
    
    // Thông tin khách hàng (để hiển thị)
    private String tenKH;
    private String sdt;      // ← ĐÃ THÊM
    private String diaChi;   // ← ĐÃ THÊM

    // Constructor không tham số
    public HoaDon() {
    }

    // Constructor đầy đủ
    public HoaDon(int maHD, int maKH, Date ngayLap, Time gioLap, double tongTien) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.ngayLap = ngayLap;
        this.gioLap = gioLap;
        this.tongTien = tongTien;
    }

    // Constructor khi tạo mới hóa đơn
    public HoaDon(int maKH) {
        this.maKH = maKH;
        this.tongTien = 0;
    }

    // Getter và Setter
    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public Time getGioLap() {
        return gioLap;
    }

    public void setGioLap(Time gioLap) {
        this.gioLap = gioLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    // ← THÊM 4 METHOD NÀY
    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHD=" + maHD +
                ", maKH=" + maKH +
                ", ngayLap=" + ngayLap +
                ", gioLap=" + gioLap +
                ", tongTien=" + tongTien +
                '}';
    }
}