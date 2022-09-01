package signup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import signup.dao.SignUpDAO;
import signup.dto.SignUpDTO;

@WebServlet("/signup_servlet/*")
public class SignUpController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // servlet 실행 체크(지우기)
        System.out.println("servlet signup 실행");

        String[] uri = request.getRequestURI().split("/");
        String code = uri[uri.length - 1];
        PrintWriter writer = response.getWriter();
        SignUpDAO dao = new SignUpDAO();

        // code 체크 (지우기)
        System.out.println(code);

        // 이메일 중복 체크
        if(code.equals("emailchk.do")) {
            // 받은 데이터에서 email 추출
            BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String jsonData = input.readLine();
            String[] splitData = jsonData.split("\"");
            String inputMail = splitData[3];
            
            // result가 1이면 중복
            int result = dao.emailchk(inputMail);
            JSONObject json = new JSONObject();
            json.put("result", result);
            writer.print(json);
        }

        
        // // // 아이디 중복 체크
        else if(code.equals("userNameChk.do")) {
            // 받은 데이터에서 email 추출
            BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String jsonData = input.readLine();
            String[] splitData = jsonData.split("\"");
            String inputName = splitData[3];

            // result가 1이면 중복
            int result = dao.userNameChk(inputName);
            JSONObject json = new JSONObject();
            json.put("result", result);
            writer.print(json);
        }
        
        // // 이메일 전송
        
        // // db insert
        else if(code.equals("insert.do")) {
            BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder build = new StringBuilder();
            String nextStr = "";
            while ((nextStr = input.readLine()) != null) {
                build.append(nextStr);
            }

            JSONParser jsonParser = new JSONParser();
            JSONObject json = null;
            try {json = (JSONObject) jsonParser.parse(build.toString());}
            catch (ParseException e) {e.printStackTrace();}

            String EMAIL = json.get("email").toString();
            String NAME = json.get("fullname").toString();
            String USERID = json.get("userid").toString();
            String PW = json.get("pw").toString();
            int BIRTH = Integer.parseInt(json.get("birth").toString());
            
            SignUpDTO dto = new SignUpDTO();
            dto.setEmail(EMAIL);
            dto.setFullName(NAME);
            dto.setUserName(USERID);
            dto.setPw(PW);
            dto.setBirth(BIRTH);
            
            int result = dao.insertSha256(dto);

            json.put("pw", result);
            writer.print(json);
        }

        
        // login 체크
        else if(code.equals("login.do")) {
            BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder build = new StringBuilder();
            String nextStr = "";
            while ((nextStr = input.readLine()) != null) {
                build.append(nextStr);
            }

            JSONParser jsonParser = new JSONParser();
            JSONObject json = null;
            try {json = (JSONObject) jsonParser.parse(build.toString());}
            catch (ParseException e) {e.printStackTrace();}

            Boolean mailChk = json.get("id").toString().contains("@");
            Boolean result = false;
            String id = "";

            SignUpDTO dto = new SignUpDTO();
            if(mailChk) {
            	id = json.get("id").toString();
            	dto.setEmail(id);
            }
            else {
            	id = json.get("id").toString();
            	dto.setUserName(id);
            }
            dto.setPw(json.get("pw").toString());

            result = dao.loginCheckSha256(dto, mailChk);

            if(result) {
                // 세션 객체 생성
                HttpSession session = request.getSession();
                
                // 세션에 값 저장
                Map<String,String> member = new HashMap<>();
                member.put("id", id);
                member.put("pw",dto.getPw());
                Member loginMember = new Member(member);
                session.setAttribute("loginMember", loginMember);
            }
            
            json.clear();
            json.put("result", result);
            writer.print(json);
        }

        else if(code.equals("home.go")) {
            HttpSession session = request.getSession();
            Object loginMember = session.getAttribute("loginMember");
            if(loginMember == null) {
                String page = request.getContextPath() + "/acounts/login/index.jsp";
                response.sendRedirect(page);
            } else {
                String page = request.getContextPath() + "/home/index.jsp";
                response.sendRedirect(page);
			}
        }

        // logout
        else if(code.equals("logout.do")) {
            HttpSession session = request.getSession();
            session.invalidate();
            String page = request.getContextPath() + "/acounts/login/index.jsp";
            response.sendRedirect(page);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}