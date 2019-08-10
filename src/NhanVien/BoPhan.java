package NhanVien;

import java.util.Scanner;

public class BoPhan {
    private int dem = 0;
    private String tenBoPhan;
    private int maBoPhan;
    {dem++;
    setMaBoPhan(dem);}
    protected String MaBoPhan(){ return String.format("BP&d", this.getMaBoPhan()); }
    public BoPhan nhapBoPhan(Scanner scanner) {
        System.out.print("Nhập mã bộ phận: ");
        String tenBoPhan;
        tenBoPhan = scanner.nextLine();
        this.setTenBoPhan(tenBoPhan);
        return this;
    }
    public String getTenBoPhan() {
        return tenBoPhan;
    }

    public void setTenBoPhan(String tenBoPhan) {
        this.tenBoPhan = tenBoPhan;
    }

    public int getMaBoPhan() {
        return maBoPhan;
    }

    public void setMaBoPhan(int maBoPhan) {
        this.maBoPhan = maBoPhan;
    }
}
