/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhanVien;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class QuanLyNhanVien {
    private ArrayList<NhanVien> ds = new ArrayList<>();
    
    public void themNhanVien(NhanVien nv){
        this.ds.add(nv);
    }
    public void xoaNhanVien(NhanVien nv){
        this.ds.remove(nv);
    }
    public void capNhat(String ht, String QueQuan ,String gioitinh)throws ParseException {
        for (NhanVien nv:this.ds) {
            if((nv.getHtNV().toLowerCase().contains(ht.toLowerCase()) ||
                    nv.getQueQuan().toLowerCase().contains(QueQuan.toLowerCase())||
                    nv.getgTinh().toLowerCase().contains(gioitinh.toLowerCase())))
            {
                System.out.print("Bạn muốn sửa thông tin nào: \n" +
                        "1.Họ tên.\n" +
                        "2.Quê quán.\n" +
                        "3.Ngày sinh.\n" +
                        "4.Giới tính.\n" +
                        "Chọn 1 -5: ");
                int choose = 0;
                Scanner s = new Scanner(System.in);
                choose = s.nextInt();
                switch (choose) {
                    case 1: {
                        String hoten;
                        do {
                            System.out.print("Nhập Tên Nhân Viên: ");
                            hoten = s.nextLine();
                            if(hoten.matches("[^a-z0-9A-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂư" +
                                    "ăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]"))
                                System.out.print("Chuỗi bạn nhập không phải tên! Vui lòng nhập lại!");
                        }while(hoten.matches("[^a-z0-9A-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂư" +
                                "ăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]"));
                        NhanVien a = new NhanVien(hoten, nv.getgTinh(),nv.getQueQuan(),nv.getNgaySinh().toString(),
                                nv.getNgayVaoLam().toString(),nv.getBoPhan());
                        ds.set(ds.indexOf(nv),a);
                        break;
                    }
                    case 2: {
                        System.out.print("Nhập quê quán nhân viên: ");
                        String queQuan = s.nextLine();
                        NhanVien a = new NhanVien(nv.getHtNV(), nv.getgTinh(),queQuan,nv.getNgaySinh().toString(),
                                nv.getNgayVaoLam().toString(),nv.getBoPhan());
                        ds.set(ds.indexOf(nv),a);
                        break;
                    }
                    case 3: {
                        System.out.print("Nhập ngày sinh nhân viên: ");
                        String ngay = s.nextLine();
                        NhanVien a = new NhanVien(nv.getHtNV(), nv.getgTinh(),nv.getQueQuan(),ngay,
                                nv.getNgayVaoLam().toString(),nv.getBoPhan());
                        ds.set(ds.indexOf(nv),a);
                    }
                    case 4: {
                        System.out.print("Nhập giới tính nhân viên: ");
                        String gTinh = s.nextLine();
                        NhanVien a = new NhanVien(nv.getHtNV(),gTinh ,nv.getQueQuan(),nv.getNgaySinh().toString(),
                                nv.getNgayVaoLam().toString(),nv.getBoPhan());
                        ds.set(ds.indexOf(nv),a);
                    }
                    default: break;
                }
            }
            else
                System.out.print("Nhân viên không tồn tại");
        }
    }
    public QuanLyNhanVien timNV(String ht, String QueQuan ,String gioitinh){
        QuanLyNhanVien kq = new QuanLyNhanVien();
        for (NhanVien nv:this.ds) {
            if(nv.getHtNV().toLowerCase().contains(ht.toLowerCase()) ||
                    nv.getQueQuan().toLowerCase().contains(QueQuan.toLowerCase())||
            nv.getgTinh().toLowerCase().contains(gioitinh.toLowerCase()))
                kq.themNhanVien(nv);
        }
        return kq;
    }

    @Override
    public String toString() {
        String kq = "";
        for (NhanVien nv: ds) {
            kq +=nv;
        }
        return kq;
    }
}
