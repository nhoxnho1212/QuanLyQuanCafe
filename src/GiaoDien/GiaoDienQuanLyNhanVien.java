package GiaoDien;

import NhanVien.*;
import QuanLy.QuanLyNhanVien;
import SQLServerDB.ConnectionUtils;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GiaoDienQuanLyNhanVien {
    private static GiaoDienQuanLyNhanVien ourInstance = new GiaoDienQuanLyNhanVien();
    private static QuanLyNhanVien quanLyNhanVien= new QuanLyNhanVien();
    public static GiaoDienQuanLyNhanVien getInstance()throws ClassNotFoundException,ParseException,SQLException {
        loadDataFormDatabase();
        byte chon=-1;
        boolean isExit=false;
        while (!isExit) {
            chon=-1;
            System.out.println("===========Menu: Quản lý nhân viên============\n" +
                    "\t1) Xem danh sách nhân viên\n" +
                    "\t2) tra cứu nhân viên\n" +
                    "\t3) thêm nhân viên\n" +
                    "\t4) cập nhập nhân viên\n" +
                    "\t5) xóa nhân viên\n"+
                    "\t6) trở về\n");

            while (chon ==-1) {
                System.out.print("\t\t chọn:");
                Scanner scanner = new Scanner(System.in);
                chon=scanner.nextByte();
                if (chon >=1 && chon <=6) {
                    break;
                }else {
                    System.out.println("NHẬP SAI!!");
                    chon =-1;
                }
            }

            switch (chon) {
                case 1:{
                    System.out.println(quanLyNhanVien);
                    break;}
                case 2:{
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("tên nhân viên: ");
                    String ten=scanner.nextLine();

                    System.out.print("quê quán nhân viên: ");
                    String queQuan=scanner.nextLine();

                    System.out.print("giới tính nhân viên: ");
                    String gioiTinh=scanner.nextLine();

                    QuanLyNhanVien ketQuaTimKiem=quanLyNhanVien.timNV(ten,queQuan,gioiTinh);
                    System.out.println("\t==kết quả tìm kiếm==");
                    System.out.println(ketQuaTimKiem);
                    break;}
                case 3:{
                    NhanVien nhanVien=new NhanVien();
                    nhanVien.NhapNhanVien(new Scanner(System.in));
                    quanLyNhanVien.themNhanVien(nhanVien);

                    //write database
                    Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
                    Statement statement = connection.createStatement();
                    SimpleDateFormat f= new SimpleDateFormat("dd/MM/yyyy");
                    String sqlQueryNhanVien="INSERT into NhanVien (MaNhanVien,HoTenNhanVien,GioiTinh,QueQuan,NgaySinh,NgayVaoLam,boPhan) " +
                            "values (\'"+nhanVien.MaNhanVien()+"\',\'"+nhanVien.getHtNV()+"\',\'"+nhanVien.getgTinh()+"\',\'"+nhanVien.getQueQuan()+"\',\'"+f.format(nhanVien.getNgaySinh())+
                        "\',\'"+f.format(nhanVien.getNgayVaoLam())+"\',\'"+nhanVien.getBoPhan().MaBoPhan()+"\')";
                    int rs=statement.executeUpdate(sqlQueryNhanVien);

                    String queryBoPhan="insert into BoPhan (MaBoPhan,TenBoPhan)" +
                            "values (\'"+nhanVien.getBoPhan().getMaBoPhan()+"\',\'"+nhanVien.getBoPhan().getTenBoPhan()+"\')";
                    int rsBoPhan=statement.executeUpdate(queryBoPhan);

                    break;}
                case 4:{
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("tên nhân viên: ");
                    String ten=scanner.nextLine();

                    System.out.print("quê quán nhân viên: ");
                    String queQuan=scanner.nextLine();

                    System.out.print("giới tính nhân viên: ");
                    String gioiTinh=scanner.nextLine();
                    quanLyNhanVien.capNhat(ten,queQuan,gioiTinh);

                    //update database

                    break;}
                case 5:{
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("tên nhân viên: ");
                    String ten=scanner.nextLine();

                    System.out.print("quê quán nhân viên: ");
                    String queQuan=scanner.nextLine();

                    System.out.print("giới tính nhân viên: ");
                    String gioiTinh=scanner.nextLine();

                    QuanLyNhanVien ketQuaTimKiem=quanLyNhanVien.timNV(ten,queQuan,gioiTinh);

                    for (NhanVien nhanVien: ketQuaTimKiem.getDanhSach()) {
                        //delete
                        Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
                        String queryNhanVien="delete from NhanVien where MaNhanVien='"+nhanVien.MaNhanVien()+"'";
                        PreparedStatement pstmt = connection.prepareStatement(queryNhanVien);
                        pstmt.executeUpdate();

                        quanLyNhanVien.xoaNhanVien(nhanVien);

                    }
                    break;}
                case 6:
                    isExit=true;
                    break;
            }
        }
        return ourInstance;
    }

    private static void loadDataFormDatabase()throws SQLException, ParseException,ClassNotFoundException {
        Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
        Statement statement = connection.createStatement();
        String sqlQuery="SELECT * from NhanVien;";

        ResultSet rs = statement.executeQuery(sqlQuery);

        while (rs.next()) {
            String maNhanVien=rs.getString("MaNhanVien");
            maNhanVien=maNhanVien.trim();
            String tenNhanVien=rs.getString("HoTenNhanVien");
            tenNhanVien=tenNhanVien.trim();
            String gioiTinh=rs.getString("GioiTinh");
            gioiTinh=gioiTinh.trim();
            String queQuan=rs.getString("QueQuan");
            queQuan =queQuan.trim();
            String maBoPhan=rs.getString("boPhan");
            maBoPhan=maBoPhan.trim();
            Date ngaySinh=rs.getDate("NgaySinh");
            Date ngayVaoLam=rs.getDate("NgayVaoLam");
            SimpleDateFormat f=new SimpleDateFormat("dd/MM/yyyy");
            String sqlQueryBoPhan="SELECT * from BoPhan WHERE MaBoPhan ='"+maBoPhan.substring(2)+"';";

            Statement statement1 = connection.createStatement();
            ResultSet rsBoPhan = statement1.executeQuery(sqlQueryBoPhan);
            BoPhan boPhan=new BoPhan();
            while (rsBoPhan.next()) {
                boPhan = new BoPhan(Integer.parseInt(maBoPhan.substring(2)), rsBoPhan.getString("TenBoPhan").trim());
            }
            NhanVien nhanVien=new NhanVien(maNhanVien,tenNhanVien,gioiTinh,queQuan,f.format(ngaySinh),f.format(ngayVaoLam),boPhan);
            quanLyNhanVien.themNhanVien(nhanVien);
        }

    }

    private GiaoDienQuanLyNhanVien() {
    }
}
