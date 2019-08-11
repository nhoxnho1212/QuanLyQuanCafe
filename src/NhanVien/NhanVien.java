package NhanVien;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class NhanVien {
    static private int dem = 0;
    private int maNV;
    {dem++;
        setMaNV(dem);}
    private String htNV;
    private String gTinh;
    private String queQuan;
    private Date ngaySinh;
    private Date ngayVaoLam;
    private BoPhan boPhan;
    public NhanVien(){
    }
    public String MaNhanVien(){ return String.format("%d",this.maNV); }
    public NhanVien(String maNV ,String hT, String gT, String qQ, String nS
            , String nVL, BoPhan bP) throws ParseException{
        this.setHtNV(hT);
        this.setgTinh(gT);
        this.setQueQuan(qQ);
        SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
        this.setNgaySinh(f.parse(nS));
        this.setNgayVaoLam(f.parse(nVL));
        this.setBoPhan(bP);
        setMaNV(Integer.parseInt(maNV));
        dem=Integer.parseInt(maNV);
    }


    public void NhapNhanVien(Scanner scanner) throws ParseException{
        System.out.print("Nhập Tên Nhân Viên: ");
        this.setHtNV(scanner.nextLine());
        System.out.print("Nhập giới tính nhân viên: ");
        this.setgTinh(scanner.nextLine());
        System.out.print("Nhập quê quán nhân viên: ");
        this.setQueQuan(scanner.nextLine());
        String ngay;
        boolean isNhapDung=false;
        while (isNhapDung==false)
        try {
            System.out.print("Nhập ngày sinh nhân viên: ");
            ngay= scanner.nextLine();

            SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
            this.setNgaySinh(f.parse(ngay));
            isNhapDung=true;
        } catch (ParseException e) {
            System.out.println("NHAP SAI!!!");
            isNhapDung=false;
        }

        isNhapDung=false;

        while (isNhapDung==false){
            try {
                System.out.print("Nhập ngày vào làm của nhân viên: ");
                String ngayVL = scanner.nextLine();
                SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
                this.setNgayVaoLam(f.parse(ngayVL));
                isNhapDung=true;
            } catch (ParseException e) {
                System.out.println("NHAP SAI!!!");
                isNhapDung=false;
            }
        }
        boPhan=new BoPhan();
        boPhan.nhapBoPhan(scanner);
    }


    @Override
    public String toString() {
        SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");

        String m = String.format("Ma Nhan Vien: %d\n" +
                        "Ho ten Nhan Vien: %s\n"
                        +"Gioi Tinh: %s\nQue Quan: %s\nNgay sinh: %s\nNgay vao lam: %s"
                        +"\nMa bo phan: %s\nTen bo phan: %s\n", this.getMaNV()
                , this.getHtNV(), this.getgTinh(), this.getQueQuan(),f.format(getNgaySinh())
                ,f.format(getNgayVaoLam()), this.boPhan.MaBoPhan(), this.boPhan.getTenBoPhan());
        return m + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n";
    }


    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHtNV() {
        return htNV;
    }

    public void setHtNV(String htNV) {
        this.htNV = htNV;
    }

    public String getgTinh() {
        return gTinh;
    }

    public void setgTinh(String gTinh) {
        this.gTinh = gTinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public BoPhan getBoPhan() {
        return boPhan;
    }

    public void setBoPhan(BoPhan boPhan) {
        this.boPhan = boPhan;
    }
}