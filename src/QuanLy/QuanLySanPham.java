package QuanLy;

import SanPhamQuan.SanPham;
import SanPhamQuan.ThucAn;
import SanPhamQuan.ThucUong;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLySanPham {
    private ArrayList<ThucAn> QuanLyThucAn;
    private ArrayList<ThucUong> QuanLyThucUong;

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

    public static void clrscr() throws IOException,InterruptedException {
        //Clears Screen in java
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
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
            }
            if (loai ==2) {
                ThucUong thucUong = new ThucUong();
                thucUong.nhap(scan);
                them(thucUong);
            }
        } catch (Exception e) {
            System.out.println("NHẬP THẤT BẠI!!!");
        }

    }


}
