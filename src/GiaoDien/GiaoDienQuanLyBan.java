package GiaoDien;


import Ban.QuanLyBan;
import Ban.Ban;
import NhanVien.BoPhan;
import NhanVien.NhanVien;
import SQLServerDB.ConnectionUtils;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GiaoDienQuanLyBan {

    private static GiaoDienQuanLyBan ourInstance = new GiaoDienQuanLyBan();
    private static QuanLyBan quanLyBan = new QuanLyBan();
    public static GiaoDienQuanLyBan getInstance() throws ClassNotFoundException, ParseException, SQLException{
        loadDataFormDatabase();
        byte chon = -1;
        boolean isExit = false;
        while (!isExit) {
            chon = -1;
            System.out.println("===========Menu: Quản lý bàn============\n" +
                    "\t1) Xem danh sách bàn\n" +
                    "\t2) tra cứu bàn\n" +
                    "\t3) thêm bàn\n" +
                    "\t4) cập nhập bàn\n" +
                    "\t5) xóa bàn\n" +
                    "\t6} xem danh sách bàn trống\n" +
                    "\t7) trở về\n");

        while (chon == -1) {
            System.out.print("\t\t chọn:");
            Scanner scanner = new Scanner(System.in);
            chon = scanner.nextByte();
            if (chon >= 1 && chon <= 7) {
                break;
            } else {
                System.out.println("NHẬP SAI!!");
                chon = -1;
            }
        }
        switch (chon) {
            case 1: {
                System.out.println(quanLyBan);
                break;
            }
            case 2: {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Mã bàn: ");
                String maBan = scanner.nextLine();

                System.out.print("Tình trạng: ");
                String tinhTrang = scanner.nextLine();

                System.out.print("Sức chứa: ");
                String sucChua = scanner.nextLine();

                QuanLyBan ketQuaTimKiem = quanLyBan.TimKiemBan(maBan, tinhTrang, sucChua);
                System.out.println("\t==kết quả tìm kiếm==");
                System.out.println(ketQuaTimKiem);
                break;
            }
            case 3: {
                Ban a = new Ban();
                a.nhapBan(new Scanner(System.in));
                quanLyBan.themBan(a);
                try {
                    Connection connection = ConnectionUtils.getSQLServerConnection("QuanLyCafe");
                    Statement statement = connection.createStatement();
                    String sqlQueryBan = "INSERT into Ban (MaBan,SucChua,TinhTrang) " +
                            "values (\'" + a.MaBan() + "\',\'" + a.SucChua() + "\',\'" + a.convertTinhTrang() + "\')";
                    int rs = statement.executeUpdate(sqlQueryBan);
                } catch (Exception e){};
                break;
            }
            case 4: {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Mã bàn: ");
                String maBan = scanner.nextLine();
                quanLyBan.capNhat(maBan);
                break;
            }
            case 5: {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Mã bàn: ");
                String maBan = scanner.nextLine();

                for (Ban ban : quanLyBan.getDanhSachBan()) {
                    if(ban.MaBan().toLowerCase().trim().contains(maBan.toLowerCase()))
                    //delete
                        try {
                            Connection connection = ConnectionUtils.getSQLServerConnection("QuanLyCafe");
                            String queryBan = "delete from Ban where MaBan='" + ban.MaBan() + "'";
                            PreparedStatement pstmt = connection.prepareStatement(queryBan);
                            pstmt.executeUpdate();
                        }catch(Exception e){System.out.print(e);}
                        quanLyBan.xoaBan(ban);
                }
                break;
            }
            case 6: {
                QuanLyBan ketqua = new QuanLyBan();
                ketqua = quanLyBan.DanhSachBanTrong();
                System.out.print("============Danh sách bàn trống============\n");
                System.out.print(ketqua);
                break;
            }
            case 7:
                isExit = true;
                break;
        }
    }
        return ourInstance;
    }
    private static void loadDataFormDatabase()throws SQLException, ParseException,ClassNotFoundException {
        Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
        Statement statement = connection.createStatement();
        String sqlQuery="SELECT * from Ban;";

        ResultSet rs = statement.executeQuery(sqlQuery);

        while (rs.next()) {
            String maBan=rs.getString("MaBan");
            maBan=maBan.trim();
            int realMaBan = 0;
            realMaBan = Integer.parseInt(maBan.substring(1,4));
            String succhua=rs.getString("SucChua");
            succhua = succhua.trim();
            int realSucChua = Integer.parseInt(succhua);
            String tinhtrang=rs.getString("TinhTrang");
            tinhtrang=tinhtrang.trim();
            boolean realTinhTrang = true;
            if (tinhtrang.trim().toLowerCase().matches("trong||trống"))
                realTinhTrang = true;
            else
                realTinhTrang = false;
            Ban ban=new Ban(realMaBan,realSucChua,realTinhTrang);
            quanLyBan.themBan(ban);
        }

    }



    private GiaoDienQuanLyBan() {
    }
}


