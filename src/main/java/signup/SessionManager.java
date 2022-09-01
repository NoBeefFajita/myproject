package signup;

public class SessionManager {
    private static SessionManager instance;
    private String SESSIONID;

    private SessionManager(String sessionid) {
        SESSIONID = sessionid;
    }

    public static SessionManager getInstance(String sessionid){
        if(instance == null){
            instance = new SessionManager(sessionid);
        }
        return instance;
    }

    public String getSESSIONID() { return SESSIONID; }
}
