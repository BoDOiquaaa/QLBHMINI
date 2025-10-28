/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author khaid
 */
public class TaiKhoan {
private int maTK;
private String tenDangNhap;
private String matKhau;
private String hoTen;
private String vaiTro;
private boolean trangThai;
private Timestamp ngayTao;
    public TaiKhoan() {
    }

    public TaiKhoan(int maTK, String tenDangNhap, String matKhau, String hoTen, String vaiTro, boolean trangThai) {
        this.maTK = maTK;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
    }

    public TaiKhoan(String tenDangNhap, String matKhau, String hoTen, String vaiTro) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.vaiTro = vaiTro;
    }

    // Getter và Setter
    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "maTK=" + maTK +
                ", tenDangNhap='" + tenDangNhap + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", vaiTro='" + vaiTro + '\'' +
                ", trangThai=" + trangThai +
                '}';
    }
}
