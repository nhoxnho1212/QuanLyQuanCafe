package Ban;

import java.util.Scanner;

public class Ban {
    private static int dem = 0;
    private int maBan;
    {
        dem++;
        maBan = dem;
    }
    private int sucChua;
    private boolean tinhTrang;

    public Ban(int maBan, int sucChua, boolean tinhTrang){
        this.setMaBan(maBan);
        this.setSucChua(sucChua);
        this.setTinhTrang(tinhTrang);
        dem=maBan;
    }

    public Ban() {
    }
    /*Trả về mã bàn là môt chuỗi bắt đầu bằng B + mã bàn*/
    public String MaBan(){
        String s = Integer.toString(this.maBan);
        if (s.matches("\\d{1}"))
            s = "B00" + s;
        else if (s.matches("\\d{2}"))
            s = "B0" + s;
        return s;
    }
    /*Convert suc chua thanh chuoi thuan tien cho viec chon ban*/
    public String SucChua(){
        String kq = Integer.toString(this.getSucChua());
        return kq;
    }
    /*Trả về tình trạng của bàn dưới dạng chuỗi, nếu maBan = False thì trả về không trống, nếu maBan = True thì trả về
     * trống */
    public String convertTinhTrang(){
        if(this.isTinhTrang() == true)
            return "trong";
        else
            return "khong";
    }
    /*Trả về chuỗi đại diện cho bàn*/
    @Override
    public String toString(){
        return String.format("Mã bàn: %s\n" +
                        "Sức chứa: %d\n" +
                        "Tình trạng: %s\n"
                , this.MaBan(),
                this.getSucChua(),
                this.convertTinhTrang());
    }

    public void nhapBan(Scanner scanner){
        System.out.print("Nhập sức chứa của bàn: ");
        this.setSucChua(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Nhập tình trạng của bàn(trống hoặc không trống): ");
        String tinhtrang = scanner.nextLine();
        if(tinhtrang.toLowerCase().trim().matches("trống") || tinhtrang.toLowerCase().trim().matches("trong"))
            this.setTinhTrang(true);
        else if(tinhtrang.toLowerCase().trim().contains("không trống") || tinhtrang.toLowerCase().contains("khong trong"))
            this.setTinhTrang(false);
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}