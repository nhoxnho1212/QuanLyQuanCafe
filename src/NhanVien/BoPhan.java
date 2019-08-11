package NhanVien;

import java.util.Scanner;

public class BoPhan {
    private static int dem = 0;
    private String tenBoPhan;
    private int maBoPhan;
    {
        dem++;
        setMaBoPhan(dem);
    }

    public BoPhan(){};
    public BoPhan(int maBoPhan,String tenBoPhan) {
        this.tenBoPhan=tenBoPhan;
        this.maBoPhan=maBoPhan;
    }

    public String MaBoPhan(){ return String.format("BP%d", this.getMaBoPhan()); }

    public void nhapBoPhan(Scanner scanner) {
        System.out.print("Nhập tên bộ phận: ");
        String tenBoPhan;
        tenBoPhan = scanner.nextLine();
        this.setTenBoPhan(tenBoPhan);
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
