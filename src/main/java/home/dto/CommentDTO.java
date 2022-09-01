package home.dto;

public class CommentDTO {
    private String code;
    private int num;
    private String commen;
    private String userid;

    public CommentDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCommen() {
        return commen;
    }

    public void setCommen(String commen) {
        this.commen = commen;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
