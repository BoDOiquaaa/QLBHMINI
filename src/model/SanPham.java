/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author khaid
 */
public class SanPham {
    private int maSP;
    private String tenSP;
    private double donGia;
    private int tonKho;

    public SanPham() {
    }

    public SanPham(int maSP, String tenSP, double donGia, int tonKho) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.tonKho = tonKho;
    }

    public SanPham(String tenSP, double donGia, int tonKho) {
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.tonKho = tonKho;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getTonKho() {
        return tonKho;
    }

    public void setTonKho(int tonKho) {
        this.tonKho = tonKho;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "maSP=" + maSP +
                ", tenSP='" + tenSP + '\'' +
                ", donGia=" + donGia +
                ", tonKho=" + tonKho +
                '}';
    }
}