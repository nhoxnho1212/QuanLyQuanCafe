package QuanLy;

import SanPhamQuan.ThucAn;

import java.util.ArrayList;

public class QuanLyThucAn  {
    private ArrayList <ThucAn> QuanLy;

    public QuanLyThucAn(ArrayList<ThucAn> quanLy) {
        this.QuanLy = quanLy;
    }

    public QuanLyThucAn(){
        this.QuanLy= new ArrayList<ThucAn>();
    }

    public void Them(ThucAn thucAn) {
        this.QuanLy.add(thucAn);
    }
    public void Xoa(ThucAn thucAn) {
        this.QuanLy.remove(thucAn);
    }
}
