package SanPhamQuan;

import java.text.ParseException;
import java.util.Scanner;

public class ThucAn extends SanPham{
    private boolean CoAnChay;
    public ThucAn() {
        super();
    }
    public ThucAn(String ten, double giaBan, boolean tinhTrang, String ngayBan, boolean coAnChay) throws ParseException {
        super(ten, giaBan, tinhTrang, ngayBan);
        this.setCoAnChay(coAnChay);
    }

    public boolean isCoAnChay() {
        return CoAnChay;
    }

    public void setCoAnChay(boolean coAnChay) {
        CoAnChay = coAnChay;
    }

    @Override
    public String toString() {
        return super.toString() +
                (isCoAnChay()?"Có thể ăn chay":"Không thề ăn chay")+"\n";
    }

    @Override
    public void nhap(Scanner scanner) {
        super.nhap(scanner);

        String anChay="";
        while (!anChay.contains("co") && !anChay.contains("khong")) {
            System.out.print("ăn chay (có/không) : ");
            anChay=scanner.nextLine();
            anChay=anChay.toLowerCase().trim();
            if (anChay.contains("có")) {
                anChay= "co";
            }
            if (anChay.contains("không")) {
                anChay= "khong";
            }
            if (anChay.contains("co") || anChay.contains("khong")) {
                break;
            }else {
                System.out.println("NHẬP SAI!!!");
            }
        }

        if (anChay.contains("co")) {
            this.setCoAnChay(true);
        }
        if (anChay.contains("khong")) {
            this.setCoAnChay(false);
        }

    }
}
