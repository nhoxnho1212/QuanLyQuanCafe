import GiaoDien.GiaoDienQuanLyBan;
import GiaoDien.GiaoDienQuanLyNhanVien;
import GiaoDien.GiaoDienQuanLySanPham;
import GiaoDien.GiaoDienThongKe;
import QuanLy.QuanLyNhanVien;
import QuanLy.QuanLySanPham;
import SanPhamQuan.SanPham;
import SanPhamQuan.ThucAn;
import SanPhamQuan.ThucUong;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        boolean isDung=false;
        while (!isDung) {
            System.out.println("==================Quản Lý quán Cafe===============\n" +
                    "\t1)Quản Lý nhân viên\n" +
                    "\t2)Quản lý sản phẩm\n" +
                    "\t3)Quản lý bàn\n" +
                    "\t4)Thống kê\n" +
                    "\t5)Thoát.");
            int chon = -1;
            while (!(chon >= 1 && chon <= 5)) {
                System.out.print("\t\t chọn: ");
                Scanner scanner = new Scanner(System.in);
                chon = scanner.nextInt();
                if (!(chon >= 1 && chon <= 5)) {
                    System.out.println("NHẬP SAI!!");
                }
            }

            switch (chon) {
                case 1: {
                    try {
                        GiaoDienQuanLyNhanVien.getInstance();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                }
                case 2: {
                    try {
                        GiaoDienQuanLySanPham.getInstance();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                }
                case 3: {
                    try {
                        GiaoDienQuanLyBan.getInstance();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                }
                case 4: {
                    System.out.print("=============thống kê================\n" +
                            "\t1) theo quý\n" +
                            "\t2) theo tháng\n");
                    chon = -1;
                    while (chon == -1) {
                        System.out.print("\t\t chọn:");
                        Scanner scanner = new Scanner(System.in);
                        chon = scanner.nextByte();
                        if (chon >= 1 && chon <= 2) {
                            break;
                        } else {
                            System.out.println("NHẬP SAI!!");
                            chon = -1;
                        }
                    }
                    switch (chon) {
                        case 1: {
                            int quy = -1;
                            while (!(quy >= 1 && quy <= 4)) {
                                System.out.print("nhập quý :");
                                Scanner scanner = new Scanner(System.in);
                                quy = scanner.nextInt();
                                if (!(quy >= 1 && quy <= 4)) System.out.println("NHẬP SAI!!");
                            }
                            GiaoDienThongKe.hongKeTheoQuy(quy);
                            break;

                        }
                        case 2: {
                            int thang = -1;
                            while (!(thang >= 1 && thang <= 12)) {
                                System.out.print("nhập tháng :");
                                Scanner scanner = new Scanner(System.in);
                                thang = scanner.nextInt();
                                if (!(thang >= 1 && thang <= 12)) System.out.println("NHẬP SAI!!");
                            }
                            GiaoDienThongKe.hongKeTheoThang(thang);
                            break;
                        }
                    }
                    break;
                }
                case 5: {
                     isDung=true;
                     break;
                    }
            }

        }
    }
}
