package GiaoDien;

import SQLServerDB.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GiaoDienThongKe {
    public static void hongKeTheoQuy(int quarter) {
        try {
            Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
            Statement statement=connection.createStatement();
            String query="SELECT MaSanPham,(SoLuong*GiaBan) as TongTien,MONTH(ThoiDiemBan) as Thang From ChiTietHoaDon where datepart(quarter,ThoiDiemBan)="+quarter;
            ResultSet result=statement.executeQuery(query);
            while (result.next()) {
                System.out.println("-------------------------------------");
                System.out.printf("Mã sản Phẩm: %s \n" +
                        "Tổng tiền: %,.2f\n" +
                        "Tháng: %d\n", result.getString("MaSanPham"),result.getDouble("TongTien"),result.getInt("Thang"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void hongKeTheoThang(int month) {
        try {
            Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
            Statement statement=connection.createStatement();
            String query="SELECT MaSanPham,(SoLuong*GiaBan) as TongTien, YEAR(ThoiDiemBan) as Nam" +
                    " From ChiTietHoaDon " +
                    "where MONTH(ThoiDiemBan)="+month;
            ResultSet result=statement.executeQuery(query);
            while (result.next()) {
                System.out.println("-------------------------------------");
                System.out.printf("Mã sản Phẩm: %s \n" +
                        "Tổng tiền: %,.2f\n" +
                        "Năm: %d\n", result.getString("MaSanPham"),result.getDouble("TongTien"),result.getInt("Nam"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
