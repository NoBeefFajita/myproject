package home;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import home.dao.CommentDAO;
import home.dao.ContentDAO;
import home.dto.CommentDTO;
import home.dto.ContentDTO;
import signup.Member;

@WebServlet("/home_servlet/*")
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("servlet home 실행");

        String[] uri = request.getRequestURI().split("/");
        String code = uri[uri.length - 1];
        

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        
        // code 체크 (지우기)
        System.out.println(code);
        
        // 포스트 페이지 로드
        if(code.equals("post.load")) {

            String fromStr = request.getParameter("from");
            String tillStr = request.getParameter("till");
            int from = Integer.parseInt(fromStr);
            int till = Integer.parseInt(tillStr);

            ContentDAO postDao = new ContentDAO();
            CommentDAO commDao = new CommentDAO();
            
            ContentDTO postDto = new ContentDTO();
            CommentDTO commDto = new CommentDTO();

            List<ContentDTO> postList = new ArrayList<>();
            List<CommentDTO> commList = new ArrayList<>();
            
            postList = postDao.getPost(from,till); // num cod img content update userid profileimg

            // ServletContext servletContext = request.getSession().getServletContext();
            // String realPath = servletContext.getRealPath("/WEB-INF/classes");
            // realPath = realPath.replaceAll("\\\\", "/");
            String realPath = "../";
            StringBuilder build = new StringBuilder();

            for(int i = 0; i < postList.size(); i++) {

                StringBuilder sectionBuild = new StringBuilder();
                ContentDTO cDto = postList.get(i);
                commList = commDao.getComm(cDto.getNum());

                String resp1 = """
                    <section>
                    <article class="profile_post">
                        <a href="" >
                            <div>
                                <img class="profile_img post" src="%s" alt="">
                            </div>
                            <div class="userid_post">
                                <span>%s</span>
                            </div>
                        </a>
                        <div></div>
                    </article>
                    <article class="post_img">
                        <img src="%s" alt="%d" width="500px" draggable="false">
                    </article>
                    <article class="lcwd">
                        <div class="like_post">
                            <button type="button">
                                <svg aria-label="좋아요" class="_ab6-" color="#8e8e8e" fill="#8e8e8e" height="24" role="img" viewBox="0 0 24 24" width="24"><path d="M16.792 3.904A4.989 4.989 0 0121.5 9.122c0 3.072-2.652 4.959-5.197 7.222-2.512 2.243-3.865 3.469-4.303 3.752-.477-.309-2.143-1.823-4.303-3.752C5.141 14.072 2.5 12.167 2.5 9.122a4.989 4.989 0 014.708-5.218 4.21 4.21 0 013.675 1.941c.84 1.175.98 1.763 1.12 1.763s.278-.588 1.11-1.766a4.17 4.17 0 013.679-1.938m0-2a6.04 6.04 0 00-4.797 2.127 6.052 6.052 0 00-4.787-2.127A6.985 6.985 0 00.5 9.122c0 3.61 2.55 5.827 5.015 7.97.283.246.569.494.853.747l1.027.918a44.998 44.998 0 003.518 3.018 2 2 0 002.174 0 45.263 45.263 0 003.626-3.115l.922-.824c.293-.26.59-.519.885-.774 2.334-2.025 4.98-4.32 4.98-7.94a6.985 6.985 0 00-6.708-7.218z"></path></svg>
                            </button>
                            <div class="like_post cnt">+10</div>
                        </div>
                        <div class="comment_post a">
                            <span>%s</span>
                            <div>%s</div>
                        </div>
                        """.formatted(realPath + cDto.getProfileimg(),
                                      cDto.getUserid(),
                                      realPath + cDto.getImg(),
                                      cDto.getNum(),
                                      cDto.getUserid(),
                                      cDto.getContent());

                // 댓글
                for(int j = 0; j < commList.size(); j++) {
                    CommentDTO dto = commList.get(j);
                    String resp = """
                            <div class="comment_post b">
                                <span>%s</span>
                                <div>%s</div>
                            </div>
                            """.formatted( dto.getUserid(),
                                           dto.getCommen());
                    sectionBuild.append(resp);
                }
                        
                // 댓 쓰기
                String resp2 = """
                    <div class="date_post">%s</div>
                    </article>
                    <article class="write_comment">
                        <textarea placeholder="댓글 쓰기"></textarea>
                        <button class="write_btn" type="button" disabled>쓰기</button>
                    </article>
                    </section>
                        """.formatted(cDto.getUploadDate());
                build.append(resp1).append(sectionBuild.toString()).append(resp2);
            }
            writer.print(build.toString());
        }

        // 댓글 insert
        else if(code.equals("comment.upload")) {
            String comment = request.getParameter("comment");
            String numStr = request.getParameter("num");
            int num = Integer.parseInt(numStr);
            // 현재 사용자 확인
            HttpSession session = request.getSession();
            Member loginMember = (Member)session.getAttribute("loginMember");
            String userid = loginMember.getId();

            CommentDAO dao = new CommentDAO();
            CommentDTO dto = new CommentDTO();
            dto.setNum(num);
            dto.setUserid(userid);
            dto.setCommen(comment);
            int result = dao.insert(dto);
            System.out.println(result);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}