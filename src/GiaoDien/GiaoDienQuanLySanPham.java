package GiaoDien;
import QuanLy.QuanLySanPham;
import SQLServerDB.ConnectionUtils;
import SanPhamQuan.ThucAn;
import SanPhamQuan.ThucUong;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GiaoDienQuanLySanPham{
    private static GiaoDienQuanLySanPham ourInstance = new GiaoDienQuanLySanPham();
    private static QuanLySanPham quanLySanPham= new QuanLySanPham();
    private GiaoDienQuanLySanPham() { }

    public static GiaoDienQuanLySanPham getInstance()throws ClassNotFoundException,ParseException,SQLException {
        loadDataFormDatabase();
        byte chon = -1;
        boolean isExit = false;
        while (!isExit) {
            chon = -1;
            System.out.println("===========Menu: Quản lý nhân viên============\n" +
                    "\t1) Xem danh sách sản phẩm\n" +
                    "\t2) tìm kiếm sản phẩm\n" +
                    "\t3) thêm sản phẩm\n" +
                    "\t4) sắp xếp theo khoảng giá\n" +
                    "\t5) xóa sản phẩm\n" +
                    "\t6) trở về\n");

            while (chon == -1) {
                System.out.print("\t\t chọn:");
                Scanner scanner = new Scanner(System.in);
                chon = scanner.nextByte();
                if (chon >= 1 && chon <= 6) {
                    break;
                } else {
                    System.out.println("NHẬP SAI!!");
                    chon = -1;
                }
            }

            switch (chon) {
                case 1: {
                    System.out.println(quanLySanPham);
                    break;
                }
                case 2: {

                    break;
                }
                case 3: {
                    Scanner scanner= new Scanner(System.in);
                    quanLySanPham.nhap(scanner);
                    break;
                }
                case 4: {
                    break;
                }
                case 5: {
                    break;
                }
                case 6: {
                    isExit = true;
                    break;
                }
            }
        }
        return ourInstance;
    }

    private static void loadDataFormDatabase() throws SQLException, ParseException,ClassNotFoundException {
        Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");

        //load table ThucAn
        Statement statement = connection.createStatement();
        String sqlQuery="SELECT * from ThucAn;";
        ResultSet rs = statement.executeQuery(sqlQuery);

        while (rs.next()) {
            String maSanPham=rs.getString("MaSanPham");
            maSanPham=maSanPham.trim();
            String tenSanPham=rs.getString("TenSanPham");
            tenSanPham=tenSanPham.trim();
            double giaBan=rs.getDouble("GiaBan");
            boolean tinhTrang=rs.getBoolean("TinhTrang");
            boolean anChay=rs.getBoolean("AnChay");
            Date ngayBan=rs.getDate("NgayBan");

            SimpleDateFormat f=new SimpleDateFormat("hh:mm dd/MM/yyyy");

            ThucAn thucAn=new ThucAn(tenSanPham,giaBan,tinhTrang,f.format(ngayBan),anChay);
            thucAn.setMaSP(maSanPham);
            quanLySanPham.them(thucAn);
        }

        //load table ThucUong
        Statement statement1 = connection.createStatement();
        String sqlQuery1="SELECT * from ThucUong;";
        ResultSet rs1 = statement.executeQuery(sqlQuery1);

        while (rs1.next()) {
            String maSanPham=rs1.getString("MaSanPham");
            maSanPham=maSanPham.trim();
            String tenSanPham=rs1.getString("TenSanPham");
            tenSanPham=tenSanPham.trim();
            double giaBan=rs1.getDouble("GiaBan");
            boolean tinhTrang=rs1.getBoolean("TinhTrang");
            boolean Da=rs1.getBoolean("Da");
            Date ngayBan=rs1.getDate("NgayBan");

            SimpleDateFormat f=new SimpleDateFormat("hh:mm dd/MM/yyyy");

            ThucUong thucUong=new ThucUong(tenSanPham,giaBan,tinhTrang,f.format(ngayBan),Da);
            thucUong.setMaSP(maSanPham);
            quanLySanPham.them(thucUong);
        }
    }
};