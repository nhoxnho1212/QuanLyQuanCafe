package SanPhamQuan;

import java.text.ParseException;

public class ThucUong extends SanPham {
    private boolean coDa;

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

}
