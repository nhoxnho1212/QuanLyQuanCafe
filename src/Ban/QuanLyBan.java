package Ban;

import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyBan {
    private ArrayList<Ban> ds = new ArrayList<>();
    private void themBan1(Ban a){
        this.ds.add(a);
    }
    public void themBan(Scanner scanner) {
        Ban kq = new Ban();
        String s;
        do {
            System.out.print("Nhập mã bàn: ");
            int maBan = scanner.nextInt();
            s = Integer.toString(kq.getMaBan());
            if(s.matches("\\D"))
                System.out.print("Bạn phải nhập một số!");
            kq.setMaBan(maBan);
        } while(s.matches("\\D"));
        do{
            System.out.print("Nhập sức chứa: ");
            int sucChua = scanner.nextInt();
            s = Integer.toString(kq.getSucChua());
            if(s.matches("\\D"))
                System.out.print("Bạn phải nhập một số!");
            kq.setSucChua(sucChua);
        }while(s.matches("\\D"));
        themBan1(kq);
    }
    public void capNhat(String maBan, String tinhTrang, String sucChua) {
        for (Ban a: this.ds) {
            if(a.MaBan().toLowerCase().contains(maBan.toLowerCase()) ||
                    a.convertTinhTrang().toLowerCase().contains("trống") ||
                    a.convertTinhTrang().toLowerCase().contains("không trống") ||
                    a.SucChua().contains(sucChua))
            {
                System.out.print("Bạn muốn sửa thông tin nào: \n" +
                        "1.Sức chứa.\n" +
                        "2.Tình trạng.\n" +
                        "Chọn 1 - 3: ");
                int choose = 0;
                Scanner s = new Scanner(System.in);
                choose = s.nextInt();
                switch (choose) {
                    case 1: {
                        System.out.print("Nhập sức chứa: ");
                        int succhua = s.nextInt();
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
                kq.themBan1(a);
        }
       return kq;
    }
}
