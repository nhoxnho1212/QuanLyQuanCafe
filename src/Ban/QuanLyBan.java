package Ban;

import java.util.ArrayList;
import java.util.Scanner;


public class QuanLyBan {
    private ArrayList<Ban> ds = new ArrayList<>();
    public ArrayList<Ban> getDanhSachBan(){
        return ds;
    }
    public void themBan(Ban a){
        this.ds.add(a);
    }
    public void capNhat(String maBan) {
        for (Ban a: this.ds) {
            if(a.MaBan().toLowerCase().matches(maBan.toLowerCase()))
            {
                System.out.print("Bạn muốn sửa thông tin nào: \n" +
                        "1.Sức chứa.\n" +
                        "2.Tình trạng.\n" +
                        "Chọn 1 - 2: ");
                int choose = 0;
                Scanner s = new Scanner(System.in);
                choose = s.nextInt();
                switch (choose) {
                    case 1: {
                        System.out.print("Nhập sức chứa: ");
                        int succhua = s.nextInt();
                        String succhua1 = Integer.toString(succhua);
                        Ban b = new Ban(a.getMaBan(), succhua, a.isTinhTrang());
                        ds.set(ds.indexOf(a), b);
                        break;
                    }
                    case 2: {
                        System.out.print("Nhập tình trạng: ");
                        String tinhtrang = s.nextLine();
                        boolean boolTinhTrang = false;
                        if(tinhtrang.toLowerCase() == "trống" || tinhtrang.toLowerCase().trim() == "trong")
                            boolTinhTrang = true;
                        else
                            boolTinhTrang = false;
                        Ban b =  new Ban(a.getMaBan(),a.getSucChua(),a.isTinhTrang());
                        ds.set(ds.indexOf(a),b);
                        break;
                    }
                    }
                }
            else
                System.out.print("Bàn không có trong hệ thống");
            }
        }
    public void xoaBan(Ban b) {
        this.ds.remove(b);
    }
/*Tìm kiếm bàn theo sức chứa, tình trạng, mã bàn*/
    public QuanLyBan TimKiemBan(String maBan, String tinhTrang, String sucChua) {
        QuanLyBan kq = new QuanLyBan();
        for (Ban a: this.ds) {
            if(a.MaBan().toLowerCase().contains(maBan.toLowerCase()) ||
                    a.convertTinhTrang().toLowerCase().contains("trống") ||
                    a.convertTinhTrang().toLowerCase().contains("không trống") ||
                    a.SucChua().contains(sucChua))
                kq.themBan(a);
        }
       return kq;
    }
    public QuanLyBan DanhSachBanTrong() {
        QuanLyBan kq = new QuanLyBan();
        for(Ban b: this.ds)
            if (b.isTinhTrang() == true)
                kq.themBan(b);
        return kq;
    }

    @Override
    public String toString() {
        String kq= "";
        for(Ban b: this.ds)
            kq+= b;
        return kq;
    }
}
