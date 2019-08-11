package QuanLy;

import java.sql.Connection;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import NhanVien.*;
import SQLServerDB.ConnectionUtils;

public class QuanLyNhanVien {
    private ArrayList<NhanVien> ds = new ArrayList<>();

    public ArrayList<NhanVien> getDanhSach(){
        return ds;
    }

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
                            s.nextLine();
                            hoten = s.nextLine();
                            if(hoten.matches("[^a-z0-9A-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂư" +
                                    "ăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]"))
                                System.out.print("Chuỗi bạn nhập không phải tên! Vui lòng nhập lại!\n");
                        }while(hoten.matches("[^a-z0-9A-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂư" +
                                "ăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]"));
                        SimpleDateFormat f= new SimpleDateFormat("dd/MM/yyyy");
                        NhanVien a = new NhanVien(String.format("%d",nv.getMaNV()),hoten, nv.getgTinh(),nv.getQueQuan(),f.format(nv.getNgaySinh()),
                                f.format(nv.getNgayVaoLam()),nv.getBoPhan());
                        ds.set(ds.indexOf(nv),a);
                        //update
                        try {
                            Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
                            Statement statement = connection.createStatement();
                            String sql="update NhanVien SET HoTenNhanVien='"+hoten+"' where MaNhanVien='"+nv.MaNhanVien()+"'";
                            int rs=statement.executeUpdate(sql);
                            connection.close();
                        } catch (Exception e){};

                        break;
                    }
                    case 2: {
                        System.out.print("Nhập quê quán nhân viên: ");
                        s.nextLine();
                        String queQuan = s.nextLine();
                        NhanVien a = new NhanVien(String.format("%d",nv.getMaNV()),nv.getHtNV(), nv.getgTinh(),queQuan,nv.getNgaySinh().toString(),
                                nv.getNgayVaoLam().toString(),nv.getBoPhan());
                        ds.set(ds.indexOf(nv),a);

                        //update
                        try {
                            Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
                            Statement statement = connection.createStatement();
                            String sql="update NhanVien SET QueQuan='"+queQuan+"' where MaNhanVien='"+nv.MaNhanVien()+"'";
                            int rs=statement.executeUpdate(sql);
                            connection.close();
                        } catch (Exception e){};
                        break;
                    }
                    case 3: {
                        System.out.print("Nhập ngày sinh nhân viên: ");
                        s.nextLine();
                        String ngay = s.nextLine();
                        SimpleDateFormat f= new SimpleDateFormat("dd/MM/yyyy");
                        NhanVien a = new NhanVien(String.format("%d",nv.getMaNV()),nv.getHtNV(), nv.getgTinh(),nv.getQueQuan(),ngay,
                                f.format(nv.getNgayVaoLam()),nv.getBoPhan());
                        ds.set(ds.indexOf(nv),a);

                        //update
                        try {
                            Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
                            Statement statement = connection.createStatement();
                            String sql="update NhanVien SET NgaySinh='"+ngay+"' where MaNhanVien='"+nv.MaNhanVien()+"';";
                            int rs=statement.executeUpdate(sql);
                            connection.close();
                        } catch (Exception e){
                            System.out.println(e);
                        };
                        break;
                    }
                    case 4: {
                        System.out.print("Nhập giới tính nhân viên: ");
                        s.nextLine();
                        String gTinh = s.nextLine();
                        SimpleDateFormat f= new SimpleDateFormat("dd/MM/yyyy");
                        NhanVien a = new NhanVien(String.format("%d",nv.getMaNV()),nv.getHtNV(),gTinh ,nv.getQueQuan(),f.format(nv.getNgaySinh()),
                                f.format(nv.getNgayVaoLam()),nv.getBoPhan());
                        ds.set(ds.indexOf(nv),a);
                        //update
                        try {
                            Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
                            Statement statement = connection.createStatement();
                            String sql="update NhanVien SET GioiTinh='"+gTinh+"' where MaNhanVien='"+nv.MaNhanVien()+"'";
                            int rs=statement.executeUpdate(sql);
                            connection.close();
                        } catch (Exception e){
                            System.out.println(e);
                        };
                        break;
                    }
                    default: break;
                }
            }

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
