package Ban;

public class Ban {
    private int maBan;
    private int sucChua;
    private boolean tinhTrang;
    
    public Ban(int maBan, int sucChua, boolean tinhTrang){
        this.setMaBan(maBan);
        this.setSucChua(sucChua);
        this.setTinhTrang(tinhTrang);
    }

    Ban() {
    }
    /*Trả về mã bàn là môt chuỗi bắt đầu bằng B + mã bàn*/
    protected String MaBan(){
        return "Ma ban: B" + this.getMaBan();
    }
    /*Convert suc chua thanh chuoi thuan tien cho viec chon ban*/
    protected String SucChua(){return String.format("&f",this.sucChua);}
    /*Trả về tình trạng của bàn dưới dạng chuỗi, nếu maBan = False thì trả về không trống, nếu maBan = True thì trả về
    * trống */
    protected String convertTinhTrang(){
        if(this.isTinhTrang() == true)
            return "Trống.";
        else
            return "Không trống.";
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
