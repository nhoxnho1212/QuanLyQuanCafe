package SanPhamQuan;

import java.text.ParseException;
import java.util.Scanner;

public class ThucUong extends SanPham {
    private boolean coDa;

    public ThucUong() {}

    public ThucUong(String ten, double giaBan, boolean tinhTrang, String ngayBan, boolean coDa) throws ParseException {
        super(ten, giaBan, tinhTrang, ngayBan);
        this.setCoDa(coDa);
    }

    public boolean isCoDa() {
        return coDa;
    }

    public void setCoDa(boolean coDa) {
        this.coDa = coDa;
    }

    @Override
    public String toString() {
        return super.toString() +
                (isCoDa()? "Có đá": "không có đá") +"\n";
    }

    @Override
    public void nhap(Scanner scanner) {
        super.nhap(scanner);

        String coDa="";
        while (!coDa.contains("co") && !coDa.contains("khong")) {
            System.out.print("có đá (có/không) : ");
            coDa=scanner.nextLine();
            coDa=coDa.toLowerCase().trim();
            if (coDa.contains("có")) {
                coDa= "co";
            }
            if (coDa.contains("không")) {
                coDa= "khong";
            }
            if (coDa.contains("co") || coDa.contains("khong")) {
                break;
            }else {
                System.out.println("NHẬP SAI!!!");
            }
        }

        if (coDa.contains("co")) {
            this.setCoDa(true);
        }
        if (coDa.contains("het")) {
            this.setCoDa(false);
        }

    }

}
