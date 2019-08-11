import GiaoDien.GiaoDienQuanLyNhanVien;
import GiaoDien.GiaoDienQuanLySanPham;
import QuanLy.QuanLySanPham;
import SanPhamQuan.SanPham;
import SanPhamQuan.ThucAn;
import SanPhamQuan.ThucUong;

import java.text.ParseException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
//        QuanLySanPham quanLy= new QuanLySanPham();
//        ThucAn thucAn1;
//        ThucAn thucAn2;
//        ThucAn thucAn3;
//        ThucUong thucUong1;
//        ThucUong thucUong2;
//        ThucUong thucUong3;
//        try {
//
//            thucAn1= new ThucAn("thức ăn 1",100000,true,"13:20 15/3/2019",true);
//            thucAn2= new ThucAn("thức ăn 2",300000,false,"19:20 1/3/2019",true);
//            thucAn3= new ThucAn("thức ăn 3",200000,true,"11 15/4/2019",false);
//
//            thucUong1= new ThucUong("thức Uống 1",100002,true,"3:20 15/3/2019",true);
//            thucUong2= new ThucUong("thức Uống 2",300001,false,"9:20 1/3/2013",true);
//            thucUong3= new ThucUong("thức uống 3",200003,true,"1:20 15/4/2014",false);
//
//            quanLy.them(thucAn1);
//            quanLy.them(thucAn2);
//            quanLy.them(thucAn3);
//            quanLy.them(thucUong1);
//            quanLy.them(thucUong2);
//            quanLy.them(thucUong3);
//
//            QuanLySanPham ketQuanTim1=quanLy.timKiem("ăn");
//            QuanLySanPham ketQuanTim2=quanLy.timKiem(100001,200000);
//
//
//            System.out.println(quanLy);
//            System.out.println(ketQuanTim1);
//            System.out.println(ketQuanTim2);
//
//            quanLy.sapXep(true);
//
//            System.out.println("thuc an");
//            System.out.println(quanLy.getThucAn());
//
//            System.out.println("thuc uống");
//            System.out.println(quanLy.getThucUong());
//
//
//        } catch (ParseException pe){
//            System.out.println("lỗi định dạng ngày tháng");
//        }
//
//        quanLy.nhap(new Scanner(System.in));
//
//        System.out.println(quanLy);
        try {
            GiaoDienQuanLySanPham.getInstance();
        }catch (Exception e) {
            System.out.println(e);
        }

    }
}
