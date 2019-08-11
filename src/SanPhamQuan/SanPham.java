package SanPhamQuan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class SanPham {
    private static int thuTuSP=0;
    private String maSP;
    private String ten;
    private double giaBan;
    private boolean tinhTrang;
    private Date NgayBan;
    private String thoiDiemBan;
    {
        thuTuSP++;
        setMaSP(String.format("SP%05d",thuTuSP));
    }

    public SanPham() {

    }

    public SanPham(String ten, double giaBan, boolean tinhTrang, String ngayBan) throws ParseException {
        this.setTen(ten);
        this.setGiaBan(giaBan);
        this.setTinhTrang(tinhTrang);
        setNgayBan(ngayBan);
    }


    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        thuTuSP=Integer.parseInt(getMaSP().substring(2));
        this.maSP = maSP;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getNgayBan() {
        SimpleDateFormat f= new SimpleDateFormat("HH:mm dd/MM/yyyy");
        return f.format(this.NgayBan);
    }

    public void setNgayBan(String ngayBan)throws ParseException {
        SimpleDateFormat f= new SimpleDateFormat("HH:mm dd/MM/yyyy");
        this.NgayBan = f.parse(ngayBan);

        // set thoiDiemBan
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(this.NgayBan);
        int gioBan = calendar.get(Calendar.HOUR_OF_DAY);
        boolean laBuoiSang = gioBan <10;
        boolean laBuoiTrua = (gioBan >=10) && (gioBan < 19);
        boolean laBuoiToi = gioBan >=19;

        if (laBuoiSang) this.thoiDiemBan="sáng";
        if (laBuoiTrua) this.thoiDiemBan="trưa";
        if (laBuoiToi)  this.thoiDiemBan="tối";
    }

    public String getThoiDiemBan() {
        return this.thoiDiemBan;
    }

    @Override
    public String toString() {
        return String.format("------------Sản phầm--------------\n" +
                "Mã sản phẩm  : %s\n" +
                "Tên sản phẩm : %s\n" +
                "Giá bán      : %,.2f (Đồng)\n" +
                "Tình trạng   : %s\n" +
                "Thời điểm bán: %s\n" +
                "Ngày bán     : %s\n",
                getMaSP(),
                getTen(),
                getGiaBan(),
                (isTinhTrang() ? "còn":"hết"),
                getThoiDiemBan(),
                getNgayBan());
    }

    public void nhap(Scanner scanner) {
        System.out.print("Tên sản phẩm: ");
        String ten=scanner.nextLine();
        ten=ten.trim();
        this.setTen(ten);

        System.out.print("Giá bán: ");
        double giaBan= scanner.nextDouble();
        this.setGiaBan(giaBan);
        scanner.nextLine();
        String tinhTrang="";
        while (!tinhTrang.contains("con") && !tinhTrang.contains("het") ) {
            System.out.print("Tình trang (còn/hết) : ");
            tinhTrang=scanner.nextLine();

            tinhTrang=tinhTrang.toLowerCase().trim();
            if (tinhTrang.contains("còn")) {
                tinhTrang = "con";
            }
            if (tinhTrang.contains("hết")) {
                tinhTrang = "het";
            }
            if (tinhTrang.contains("con") || tinhTrang.contains("het")) {
                break;
            }else {
                System.out.println("NHẬP SAI!!!");
            }
        }
        if (tinhTrang.contains("con") ){
            this.setTinhTrang(true);
        }
        if (tinhTrang.contains("het")) {
            this.setTinhTrang(false);
        }

        String ngayBan="";
        while (ngayBan ==""){
            try {
                System.out.print("Ngày bán (hh:mm dd/mm/yyyy): ");
                ngayBan=scanner.nextLine();
                this.setNgayBan(ngayBan);
            } catch (ParseException pe) {
                System.out.println("\t\tNHẬP SAI ĐỊNH DẠNG NGÀY!!");
                ngayBan="";
            }
        }

    }
}
