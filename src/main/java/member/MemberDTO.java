package member;

import java.sql.Date;

public class MemberDTO {
    private String code;
    private String userid;
    private String pw;
    private String name;
    private String email;
    private String admin;
    private Date joinDate;

    public MemberDTO() {
    }

    public MemberDTO(String code, String userid, String pw, String name, String email, String admin, Date joinDate) {
        this.code = code;
        this.userid = userid;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.admin = admin;
        this.joinDate = joinDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "code='" + code + '\'' +
                ", userid='" + userid + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", admin='" + admin + '\'' +
                ", joinDate=" + joinDate +
                '}';
    }
}
