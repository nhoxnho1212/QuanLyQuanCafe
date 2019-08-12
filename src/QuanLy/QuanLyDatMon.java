package QuanLy;

import SQLServerDB.ConnectionUtils;
import SanPhamQuan.SanPham;
import SanPhamQuan.ThucAn;
import SanPhamQuan.ThucUong;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QuanLyDatMon {
    private QuanLySanPham quanLySanPham;
    private String maBan;
    private double tongTien=0;
    public QuanLyDatMon() {
        quanLySanPham= new QuanLySanPham();
        maBan="";
    }
    public QuanLyDatMon(String maBan) {
        quanLySanPham= new QuanLySanPham();
        this.maBan=maBan;
    }

    public double getTongTien() {
        return tongTien;
    }

    public boolean datMon(String tenSanPham,String ngayBan,int soLuong,QuanLySanPham DanhSachSanPhamHienCo)throws ParseException {
        QuanLySanPham themMon =DanhSachSanPhamHienCo.timKiem(tenSanPham);
        int soMon=0;
        SanPham monThem= new SanPham();
        for (ThucAn thucAn: themMon.getListThucAn() ) {
            thucAn.setNgayBan(ngayBan);
            quanLySanPham.them(thucAn);
            monThem=thucAn;
            soMon++;
        }

        for (ThucUong thucUong: themMon.getListThucUong()) {
            thucUong.setNgayBan(ngayBan);
            quanLySanPham.them(thucUong);
            monThem=thucUong;
            soMon++;
        }
        if (soMon==0) {
            return false;
        }
        tongTien+=(soLuong*monThem.getGiaBan());
        //write database
        try {
            Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
            Statement statement = connection.createStatement();
            SimpleDateFormat f= new SimpleDateFormat("hh:mm dd/MM/yyyy");

            String query="INSERT into ChiTietHoaDon (MaBan,MaSanPham,SoLuong,GiaBan,ThoiDiemBan) " +
                    "values ('"+maBan+"','"+monThem.getMaSP()+"',"+soLuong+","+
                    monThem.getGiaBan()+",CONVERT(datetime,'"+f.format(f.parse(ngayBan))+"',131))";
            int result=statement.executeUpdate(query);
            connection.close();

        } catch (Exception e){
            System.out.println(e);
        }
        return true;
    }

}
