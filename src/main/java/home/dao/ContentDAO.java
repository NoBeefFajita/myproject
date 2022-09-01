package home.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import config.DB;
import home.dto.ContentDTO;

public class ContentDAO {
    
    public List<ContentDTO> getPost(int from, int till ) {
        List<ContentDTO> postList = new ArrayList<>();
        int cnt = totalPost() + 1;
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=DB.getConn();
			String sql="""
				select num, code, img, content, uploadDate, userid, profileimg
                from content c
                join member m using (code)
                where num < ? and num > ?
                ORDER BY num desc
					""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnt-from);
			pstmt.setInt(2, cnt-till);

			rs = pstmt.executeQuery();
            while(rs.next()) {
                ContentDTO dto = new ContentDTO();
                dto.setNum(rs.getInt("num"));
                dto.setCode(rs.getString("code"));
                dto.setImg(rs.getString("img"));
                dto.setContent(rs.getString("content"));
                dto.setUploadDate(rs.getString("uploaddate"));
                dto.setUserid(rs.getString("userid"));
                dto.setProfileimg(rs.getString("profileimg"));
                postList.add(dto);
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Objects.requireNonNull(rs).close();
				Objects.requireNonNull(pstmt).close();
				Objects.requireNonNull(conn).close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
        }
        return postList;
    }

    public int totalPost() {
        int result = 0;
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=DB.getConn();
			String sql="""
				select count(num)
                from content
					""";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
            if(rs.next()) result = rs.getInt("count(num)");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Objects.requireNonNull(rs).close();
				Objects.requireNonNull(pstmt).close();
				Objects.requireNonNull(conn).close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
        }
        return result;
    }

}
