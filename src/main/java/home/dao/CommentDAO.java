package home.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import config.DB;
import home.dto.CommentDTO;

public class CommentDAO {
    
    public List<CommentDTO> getComm(int num) {
        List<CommentDTO> commList = new ArrayList<>();
        Connection conn = null;
		PreparedStatement pstmt = null;
        ResultSet rs = null;

		try {
			conn=DB.getConn();
			String sql="""
				select num,code, userid, commen, comdate
                from comments c
                join member m using (code)
                where num like ?
                ORDER BY comdate ASC
					""";;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
            while(rs.next()) {
                CommentDTO dto = new CommentDTO();
                dto.setNum(rs.getInt("num"));
                dto.setCode(rs.getString("code"));
                dto.setUserid(rs.getString("userid"));
                dto.setCommen(rs.getString("commen"));
                commList.add(dto);
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
        return commList;
    }

    public int insert(CommentDTO dto) {
        int result = 0;
        String code = codeMatch(dto.getUserid());
        Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = DB.getConn();
			String sql = """
				insert into comments
                (code, num, commen) 
                values (?,?,?)
					""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setInt(2, dto.getNum());
			pstmt.setString(3, dto.getCommen());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Objects.requireNonNull(pstmt).close();
				Objects.requireNonNull(conn).close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
        return result;
    }

    private String codeMatch(String userid) {
        String result = "";
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=DB.getConn();
			String sql="""
				select code
                from member
                where userid like ?
					""";
			pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
            if(rs.next()) result = rs.getString("code");
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
