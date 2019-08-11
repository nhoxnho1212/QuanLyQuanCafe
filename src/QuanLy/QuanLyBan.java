package QuanLy;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import Ban.Ban;
import SQLServerDB.ConnectionUtils;
import SanPhamQuan.ThucAn;
import SanPhamQuan.ThucUong;
import org.omg.CORBA.TRANSACTION_MODE;

public class QuanLyBan {
    private ArrayList<Ban> ds = new ArrayList<>();
    private QuanLySanPham quanLySanPham=new QuanLySanPham();
    public ArrayList<Ban> getDanhSachBan(){
        return ds;
    }

    {
        try {
            loadDataFormDatabase();
        }catch (Exception e) {
            System.out.println(e);
        }

    }

    public void themBan(Ban a){
        this.ds.add(a);
    }

    public void capNhat(String maBan) throws ClassNotFoundException, ParseException, SQLException{
        boolean timThay=false;
        for (Ban a: this.ds) {
            if(a.MaBan().toLowerCase().trim().matches(maBan.toLowerCase()))
            {
                System.out.print("Bạn muốn sửa thông tin nào: \n" +
                        "1.Sức chứa.\n" +
                        "2.Tình trạng.\n" +
                        "Chọn 1 - 2: ");
                int choose = 0;
                Scanner s = new Scanner(System.in);
                choose = s.nextInt();
                switch (choose) {
                    case 1: {
                        System.out.print("Nhập sức chứa: ");
                        int succhua = s.nextInt();
                        String succhua1 = Integer.toString(succhua);

                        a.setSucChua(succhua);
                        try {
                            Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
                            Statement statement = connection.createStatement();
                            String sql="update Ban SET SucChua='"+succhua1+"' where MaBan='"+a.MaBan()+"'";
                            int rs=statement.executeUpdate(sql);
                            connection.close();
                        } catch (Exception e){
                            System.out.println(e);
                        };
                        break;
                    }
                    case 2: {
                        s.nextLine();
                        System.out.print("Nhập tình trạng: ");
                        String tinhtrang = s.nextLine();
                        boolean boolTinhTrang = false;
                        if(tinhtrang.toLowerCase().contains("trống") || tinhtrang.toLowerCase().trim().contains("trong"))
                            boolTinhTrang = true;
                        else
                            boolTinhTrang = false;

                        a.setTinhTrang(boolTinhTrang);
                        try {
                            Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
                            Statement statement = connection.createStatement();
                            String sql="update Ban SET TinhTrang='"+tinhtrang+"' where MaBan='"+a.MaBan()+"'";
                            int rs=statement.executeUpdate(sql);
                            connection.close();
                        } catch (Exception e){};
                        break;
                    }
                }
                timThay= true;
                break;
            }

        }
        if (!timThay){
            System.out.print("Bàn không có trong hệ thống");
        }
    }

    public void xoaBan(Ban b) throws ClassNotFoundException, ParseException, SQLException{
        this.ds.remove(b);
    }
    /*Tìm kiếm bàn theo sức chứa, tình trạng, mã bàn*/
    public QuanLyBan TimKiemBan(String maBan, String tinhTrang, String sucChua) throws ClassNotFoundException,
            ParseException{
        QuanLyBan kq = new QuanLyBan();
        for (Ban a: this.ds) {
            if(a.MaBan().toLowerCase().contains(maBan.toLowerCase()) ||
                    a.convertTinhTrang().toLowerCase().contains("trong") ||
                    a.convertTinhTrang().toLowerCase().contains("khong") ||
                    a.SucChua().contains(sucChua))
                kq.themBan(a);
        }
        return kq;
    }

    public QuanLyBan DanhSachBanTrong() throws ClassNotFoundException, ParseException, SQLException{
        QuanLyBan kq = new QuanLyBan();
        for(Ban b: this.ds)
            if (b.isTinhTrang() == true)
                kq.themBan(b);
        return kq;
    }

    public void datBan() throws ClassNotFoundException, ParseException, SQLException {
        Scanner scanner = new Scanner(System.in);
        QuanLyBan ketqua ;
        ketqua = this.DanhSachBanTrong();
        boolean datBanThanhCong=false;
        System.out.print("============Danh sách bàn trống============\n");
        System.out.print(ketqua);
        System.out.print("Nhập mã bàn bạn muốn chọn: ");
        String maban = scanner.nextLine();
        for(Ban a:this.ds) {
            if (a.MaBan().toLowerCase().trim().matches(maban.toLowerCase().trim())) {
                a.setTinhTrang(false);
                try {
                    Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");
                    Statement statement = connection.createStatement();
                    String sql="update Ban SET TinhTrang='"+a.convertTinhTrang()+"' where MaBan='"+a.MaBan()+"'";
                    int rs=statement.executeUpdate(sql);
                    connection.close();
                } catch (Exception e){
                    System.out.println(e);
                };
                datBanThanhCong=true;
            }
        }
        if (datBanThanhCong) {
            System.out.print("Đặt bàn thành công!\n");
            boolean isThemMonAn = true;
            String ngayDat="";
            if (isThemMonAn) {
                try {
                    System.out.println("Ngày đặt (hh:mm dd/MM/yyyy):");
                    SimpleDateFormat f = new SimpleDateFormat("hh:mm dd/MM/yyyy");
                    ngayDat = scanner.nextLine();
                    f.parse(ngayDat);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            QuanLyDatMon quanLyDatMon = new QuanLyDatMon(maban);
            while (isThemMonAn) {
                int chon = -1;
                System.out.print("===========Đặt Sản phẩm=============\n" +
                        "\t1)đặt thêm món\n" +
                        "\t2)tính tiền\n");
                while (chon != 1 && chon != 2) {
                    System.out.print("\t\tchọn: ");
                    chon = scanner.nextInt();
                    if (chon != 1 && chon != 2) {
                        System.out.println("NHxẬP SAI !!!");
                    } else {
                        break;
                    }
                }
                scanner.nextLine();
                if (chon == 1) {
                    System.out.print("nhap tên sản phẩm: ");
                    String tenSanPham=scanner.nextLine();
                    System.out.print("nhập số lượng:");
                    int soLuong=scanner.nextInt();
                    boolean rs=quanLyDatMon.datMon(tenSanPham,ngayDat,soLuong,quanLySanPham);
                    if (!rs) {
                        System.out.println("thêm món thất bại!!");
                    }else {
                        System.out.println("thêm món thành công!!");
                    }
                }
                if (chon==2) {
                    System.out.printf("Tổng tiền: %,.2f\n",quanLyDatMon.getTongTien());
                    isThemMonAn=false;
                }
            }
        }
        else {
            System.out.print("Đặt bàn thất bại!\n");
        }
    }

    @Override
    public String toString() {
        String kq= "";
        for(Ban b: this.ds)
            kq+= b;
        return kq;
    }

    private void loadDataFormDatabase() throws SQLException, ParseException,ClassNotFoundException {
        Connection connection= ConnectionUtils.getSQLServerConnection("QuanLyCafe");

        //load table ThucAn
        Statement statement = connection.createStatement();
        String sqlQuery="SELECT * from ThucAn;";
        ResultSet rs = statement.executeQuery(sqlQuery);

        while (rs.next()) {
            String maSanPham=rs.getString("MaSanPham");
            maSanPham=maSanPham.trim();
            String tenSanPham=rs.getString("TenSanPham");
            tenSanPham=tenSanPham.trim();
            double giaBan=rs.getDouble("GiaBan");
            boolean tinhTrang=rs.getBoolean("TinhTrang");
            boolean anChay=rs.getBoolean("AnChay");
            Date ngayBan=rs.getDate("NgayBan");

            SimpleDateFormat f=new SimpleDateFormat("hh:mm dd/MM/yyyy");

            ThucAn thucAn=new ThucAn(tenSanPham,giaBan,tinhTrang,f.format(ngayBan),anChay);
            thucAn.setMaSP(maSanPham);
            quanLySanPham.them(thucAn);
        }

        //load table ThucUong
        Statement statement1 = connection.createStatement();
        String sqlQuery1="SELECT * from ThucUong;";
        ResultSet rs1 = statement.executeQuery(sqlQuery1);

        while (rs1.next()) {
            String maSanPham=rs1.getString("MaSanPham");
            maSanPham=maSanPham.trim();
            String tenSanPham=rs1.getString("TenSanPham");
            tenSanPham=tenSanPham.trim();
            double giaBan=rs1.getDouble("GiaBan");
            boolean tinhTrang=rs1.getBoolean("TinhTrang");
            boolean Da=rs1.getBoolean("Da");
            Date ngayBan=rs1.getDate("NgayBan");

            SimpleDateFormat f=new SimpleDateFormat("hh:mm dd/MM/yyyy");

            ThucUong thucUong=new ThucUong(tenSanPham,giaBan,tinhTrang,f.format(ngayBan),Da);
            thucUong.setMaSP(maSanPham);
            quanLySanPham.them(thucUong);
        }
    }
}