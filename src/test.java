import GiaoDien.GiaoDienQuanLyBan;
import GiaoDien.GiaoDienQuanLyNhanVien;
import GiaoDien.GiaoDienQuanLySanPham;
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
                    "\t4)Thoát.");
            int chon = -1;
            while (!(chon >= 1 && chon <= 4)) {
                System.out.print("\t\t chọn: ");
                Scanner scanner = new Scanner(System.in);
                chon = scanner.nextInt();
                if (!(chon >= 1 && chon <= 4)) {
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
                    isDung=true;
                    break;
                }
            }

        }
    }
}
