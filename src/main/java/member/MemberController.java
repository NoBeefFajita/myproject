package member;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member_servlet/*")
public class MemberController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] uri = request.getRequestURI().split("/");
        String code = uri[uri.length - 1];
        String context = request.getContextPath();  // 웹프로젝트 식별자
        
        String CODE = request.getParameter("code");
        String USERID = request.getParameter("userid");
        String PW = request.getParameter("pw");
        String NAME = request.getParameter("name");
        String EMAIL = request.getParameter("email");
        String ADMIN = request.getParameter("admin");
        String JOINDATE = request.getParameter("joinDate");
        MemberDAO dao = new MemberDAO();
        
        // 전체 표시
        if(code.equals("list.do")) {
            Map<String,Object> map = new HashMap<>();
            List<MemberDTO> list = dao.memberList();
            map.put("list", list);
            map.put("count", list.size());
            request.setAttribute("map",map);
            String page = "/test/memberList.jsp";

            RequestDispatcher rd = request.getRequestDispatcher(page);
            rd.forward(request, response);
        }

        // insert
        else if(code.equals("join.do")) {
            MemberDTO dto=new MemberDTO();//MemberDTO 생성
			dto.setUserid(CODE);
			dto.setUserid(USERID);
			dto.setPw(PW);
			dto.setName(NAME);
			dto.setEmail(EMAIL);
			dto.setEmail(ADMIN);
			dto.setEmail(JOINDATE);

            dao.insert(dto);
		}

        // 개인 표시
        else if(code.equals("view.do")) {
			MemberDTO dto = dao.memberDetail(USERID);
			request.setAttribute("dto", dto);
			String page="/ch06/member_view.jsp";
			RequestDispatcher rd=request.getRequestDispatcher(page);
			rd.forward(request, response);
		}

        // update
        else if(code.equals("update.do")) {
			MemberDTO dto=new MemberDTO();
			dto.setUserid(USERID);
			dto.setPw(PW);
			dto.setName(NAME);
			dto.setEmail(EMAIL);

			//레코드 수정 처리
			dao.update(dto);
			//페이지 이동
			response.sendRedirect(context+"/ch06/member.jsp");
		}

        else if(code.equals("delete.do")) {
			//레코드 삭제 처리
			dao.delete(USERID);
			//페이지 이동
			response.sendRedirect(context+"/ch06/member.jsp");
		}

        else if(code.equals("login.do") || code.equals("login_oracle_secret.do") || code.equals("login_sha.do") || code.equals("login_bcrypt.do")) {
            MemberDTO dto = new MemberDTO();
            String result;
            dto.setUserid(USERID);
            dto.setPw(PW);

            if(code.equals("login_oracle_secret.do")) result = dao.loginCheckOracle(dto);
            else if(code.equals("login_sha.do")) result = dao.loginCheckSha256(dto);
            else if(code.equals("login_bcrypt.do")) result = dao.loginCheckBcrypt(dto);
            else result = dao.loginCheck(dto);
            
            request.setAttribute("result", result);
			String page="/ch06/loginResult.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}