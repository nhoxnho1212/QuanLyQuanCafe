package QuanLy;

import SQLServerDB.ConnectionUtils;
import SanPhamQuan.SanPham;
import SanPhamQuan.ThucAn;
import SanPhamQuan.ThucUong;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class QuanLySanPham {
    private ArrayList<ThucAn> QuanLyThucAn;
    private ArrayList<ThucUong> QuanLyThucUong;

    public ArrayList<ThucAn> getListThucAn(){
        return this.QuanLyThucAn;
    }
    public ArrayList<ThucUong> getListThucUong(){
        return this.QuanLyThucUong;
    }

    public QuanLySanPham() {
        this.QuanLyThucAn = new ArrayList<ThucAn>();
        this.QuanLyThucUong = new ArrayList<ThucUong>();
    }

    public QuanLySanPham getThucAn() {
        QuanLySanPham thucAnClone=new QuanLySanPham();
        for (ThucAn thucAn: QuanLyThucAn) {
            thucAnClone.them(thucAn);
        }
        return thucAnClone;
    }

    public QuanLySanPham getThucUong() {
        QuanLySanPham thucUongnClone=new QuanLySanPham();
        for (ThucUong thucUong: QuanLyThucUong) {
            thucUongnClone.them(thucUong);
        }
        return thucUongnClone;
    }

    public void them(ThucAn thucAn) {
        this.QuanLyThucAn.add(thucAn);
    }

    public void them(ThucUong thucUong) {
        this.QuanLyThucUong.add(thucUong);
    }

    public void xoa(ThucAn thucAn) {
        this.QuanLyThucAn.remove(thucAn);

        //delete row databasse
        try {
            Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
            Statement statement = connection.createStatement();
            SimpleDateFormat f= new SimpleDateFormat("hh:mm dd/MM/yyyy");

            String query="INSERT into ThucAn (MaSanPham,TenSanPham,GiaBan,TinhTrang,NgayBan,AnChay) " +
                    "values ('"+thucAn.getMaSP()+"','"+thucAn.getTen()+"',"+thucAn.getGiaBan()+","+
                    (thucAn.isTinhTrang()?1:0)+",'"+thucAn.getNgayBan()+"',"+(thucAn.isCoAnChay()?1:0)+")";
            int result=statement.executeUpdate(query);
            connection.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void xoa(ThucUong thucUong) {
        this.QuanLyThucUong.remove(thucUong);
    }

    public QuanLySanPham timKiem(String tenSanPham) {
        QuanLySanPham ketQuaTimKiemSanPham = new QuanLySanPham();
        for (ThucAn thucAn : QuanLyThucAn) {
            if (thucAn.getTen().contains(tenSanPham)) {
                ketQuaTimKiemSanPham.them(thucAn);
            }
        }

        for (ThucUong thucUong : QuanLyThucUong) {
            if (thucUong.getTen().contains(tenSanPham)) {
                ketQuaTimKiemSanPham.them(thucUong);
            }
        }

        return ketQuaTimKiemSanPham;
    }

    public QuanLySanPham timKiem(double giaBatDau,double giaKetThuc) {
        QuanLySanPham ketQuaTimKiemSanPham = new QuanLySanPham();
        for (ThucAn thucAn : QuanLyThucAn) {
            boolean isnamTrongKhoangGia=(thucAn.getGiaBan() <= giaKetThuc) && (thucAn.getGiaBan() >=giaBatDau);
            if (isnamTrongKhoangGia) {
                ketQuaTimKiemSanPham.them(thucAn);
            }
        }
        for (ThucUong thucUong : QuanLyThucUong) {
            boolean isnamTrongKhoangGia=(thucUong.getGiaBan() <= giaKetThuc) && (thucUong.getGiaBan() >=giaBatDau);
            if (isnamTrongKhoangGia) {
                ketQuaTimKiemSanPham.them(thucUong);
            }
        }

        return ketQuaTimKiemSanPham;
    }

    private int soSanhGiaBan(double sanPham1,double sanPham2) {
        return (int)(sanPham1 - sanPham2);
    }

    public void sapXep(boolean ascending) {
        if (ascending){
            QuanLyThucAn.sort((a,b) -> soSanhGiaBan(a.getGiaBan(),b.getGiaBan()));
            QuanLyThucUong.sort((a,b) -> soSanhGiaBan(a.getGiaBan(),b.getGiaBan()));
        } else {
            QuanLyThucAn.sort((a,b) -> -soSanhGiaBan(a.getGiaBan(),b.getGiaBan()));
            QuanLyThucUong.sort((a,b) -> -soSanhGiaBan(a.getGiaBan(),b.getGiaBan()));
        }
    }

    @Override
    public String toString() {
        String result= "=======Danh Sach San Pham========\n";

        for (ThucAn thucAn: QuanLyThucAn) {
            result+=(thucAn+"\n");
        }

        for (ThucUong thucUong: QuanLyThucUong) {
            result+=(thucUong+"\n");
        }

        return result;
    }

    public void nhap(Scanner scan){
        try {
            byte loai=-1;
            System.out.println("==================Thêm sản phẩm==================");
            while (loai==-1) {
                System.out.println("Các loại sản phẩm:\n" +
                        "\t1) Thức ăn.\n" +
                        "\t2) Thức uống. ");
                System.out.print("\t\tChọn lọai: ");
                loai= scan.nextByte();
                if (loai==1 || loai ==2) {
                    break;
                }else {
                    loai=-1;
                    System.out.println("NHẬP SAI !!!");
                }
            }
            scan.nextLine();
            if (loai==1) {
                ThucAn thucAn= new ThucAn();
                thucAn.nhap(scan);
                them(thucAn);

                //write databasse
                try {
                    Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
                    Statement statement = connection.createStatement();
                    SimpleDateFormat f= new SimpleDateFormat("hh:mm dd/MM/yyyy");

                    String query="INSERT into ThucAn (MaSanPham,TenSanPham,GiaBan,TinhTrang,NgayBan,AnChay) " +
                            "values ('"+thucAn.getMaSP()+"','"+thucAn.getTen()+"',"+thucAn.getGiaBan()+","+
                            (thucAn.isTinhTrang()?1:0)+",CONVERT(datetime,'"+f.format(f.parse(thucAn.getNgayBan()))+"',131),"+(thucAn.isCoAnChay()?1:0)+")";
                    int result=statement.executeUpdate(query);
                    connection.close();
                } catch (Exception e){
                    System.out.println(e);
                }

            }
            if (loai ==2) {
                ThucUong thucUong = new ThucUong();
                thucUong.nhap(scan);
                them(thucUong);

                //write databasse
                try {
                    Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
                    Statement statement = connection.createStatement();
                    SimpleDateFormat f= new SimpleDateFormat("hh:mm dd/MM/yyyy");

                    String query="INSERT into ThucUong (MaSanPham,TenSanPham,GiaBan,TinhTrang,NgayBan,Da) " +
                            "values ('"+thucUong.getMaSP()+"','"+thucUong.getTen()+"',"+thucUong.getGiaBan()+","+
                            (thucUong.isTinhTrang()?1:0)+",CONVERT(datetime,'"+f.format(f.parse(thucUong.getNgayBan()))+"',131),"+(thucUong.isCoDa()?1:0)+")";
                    int result=statement.executeUpdate(query);
                    connection.close();
                } catch (Exception e){
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println("NHẬP THẤT BẠI!!!");
            System.out.println(e);
        }

    }


}
