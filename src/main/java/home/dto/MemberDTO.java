package home.dto;

public class MemberDTO {
    private String email;
    private String fullName;
    private String userName;
    private String pw;
    private int birth;

    public MemberDTO() {
    }

    public MemberDTO(String email, String fullName, String userName, String pw, int birth) {
        this.email = email;
        this.fullName = fullName;
        this.userName = userName;
        this.pw = pw;
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }
}
