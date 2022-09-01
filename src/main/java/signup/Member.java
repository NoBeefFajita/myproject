package signup;

import java.util.Map;

public class Member {
    private String id;
    private String pw;

    public Member(Map member) {
    	this.id = member.get("id").toString();
    	this.pw = member.get("pw").toString();
    }

    public String getId() {
        return id;
    }
}
