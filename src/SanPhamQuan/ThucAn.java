package SanPhamQuan;

import java.text.ParseException;

public class ThucAn extends SanPham{
    private boolean CoAnChay;

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
}
