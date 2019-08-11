package GiaoDien;
import NhanVien.NhanVien;
import QuanLy.QuanLySanPham;
import SQLServerDB.ConnectionUtils;
import SanPhamQuan.ThucAn;
import SanPhamQuan.ThucUong;

import java.sql.*;
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
            System.out.println("===========Menu: Quản lý Sản phẩm============\n" +
                    "\t1) Xem danh sách sản phẩm\n" +
                    "\t2) tìm kiếm sản phẩm\n" +
                    "\t3) thêm sản phẩm\n" +
                    "\t4) sắp xếp \n" +
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
                    byte loai=-1;
                    Scanner scanner= new Scanner(System.in);
                    System.out.println("==================tìm kiếm sản phẩm==================");
                    while (loai==-1) {
                        System.out.println("Các loại sản phẩm:\n" +
                                "\t1) tìm kiếm theo tên.\n" +
                                "\t2) tìm kiếm theo khoảng giá. ");
                        System.out.print("\t\tChọn : ");
                        loai= scanner.nextByte();
                        if (loai==1 || loai ==2) {
                            break;
                        }else {
                            loai=-1;
                            System.out.println("NHẬP SAI !!!");
                        }
                    }
                    QuanLySanPham ketQuatimKiem=new QuanLySanPham();
                        scanner.nextLine();
                        if (loai==1) {
                            System.out.print("Tên san phẩm: ");
                            String ten= scanner.nextLine();
                        ketQuatimKiem=quanLySanPham.timKiem(ten);
                    }
                    if (loai==2) {
                        System.out.print("giá thấp nhất: ");
                        double giaMin=scanner.nextDouble();
                        System.out.print("giá thấp nhất: ");
                        double giaMax=scanner.nextDouble();
                        ketQuatimKiem=quanLySanPham.timKiem(giaMin,giaMax);
                    }

                    System.out.println("==Kết quả tìm kiếm==");
                    System.out.println(ketQuatimKiem);
                    break;
                }
                case 3: {
                    Scanner scanner= new Scanner(System.in);
                    quanLySanPham.nhap(scanner);
                    break;
                }
                case 4: {
                    byte loai=-1;
                    Scanner scanner= new Scanner(System.in);
                    System.out.println("==================Sắp xếp sản phẩm==================");
                    while (loai==-1) {
                        System.out.println("chọn kiểu sắp xếp:\n" +
                                "\t1) tăng dần.\n" +
                                "\t2) giảm dần. ");
                        System.out.print("\t\tChọn : ");
                        loai= scanner.nextByte();
                        if (loai==1 || loai ==2) {
                            break;
                        }else {
                            loai=-1;
                            System.out.println("NHẬP SAI !!!");
                        }
                    }

                    if (loai==1) {
                        quanLySanPham.sapXep(true);
                    }
                    if (loai==2) {
                        quanLySanPham.sapXep(false);
                    }
                    System.out.println("sắp xếp thành công!!!");
                    break;
                }
                case 5: {
                    Scanner scanner= new Scanner(System.in);
                    System.out.print("Tên sản phẩm: ");
                    String ten= scanner.nextLine();
                    QuanLySanPham ketQuaTimKiem=quanLySanPham.timKiem(ten);
                    try {
                        for (ThucAn thucAn : ketQuaTimKiem.getListThucAn()) {
                            quanLySanPham.xoa(thucAn);

                            //delete database
                            Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
                            String query="delete from ThucAn where CONVERT(VARCHAR,MaSanPham)='"+thucAn.getMaSP()+"'";
                            PreparedStatement pstmt = connection.prepareStatement(query);
                            pstmt.executeUpdate();

                        }
                        for (ThucUong thucUong: ketQuaTimKiem.getListThucUong()) {
                            quanLySanPham.xoa(thucUong);

                            //delete database
                            Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
                            String query="delete from ThucUong where CONVERT(VARCHAR,MaSanPham)='"+thucUong.getMaSP()+"'";
                            PreparedStatement pstmt = connection.prepareStatement(query);
                            pstmt.executeUpdate();

                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
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