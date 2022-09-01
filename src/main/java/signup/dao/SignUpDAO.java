package signup.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import config.DB;
import signup.dto.SignUpDTO;

public class SignUpDAO{

	public int emailchk(String email) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=DB.getConn();
			String sql="""
				select count(email)
				from member
				where email LIKE ?
					""";;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt("count(email)");
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

	public int userNameChk(String id) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=DB.getConn();
			String sql="""
				select count(userid)
      			from member
      			where userid LIKE ?
					""";;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt("count(userid)");
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
	
	public int insertSha256(SignUpDTO dto) { return insert(dto, pwSha(dto)); }
	private int insert(SignUpDTO dto, String pw) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.getConn();
			String sql = """
				insert into member
				(code, userid, pw, name, email, birth) 
				values
				( (select rpad(lpad(substr(max(code),1,3)+1,3,'0'),4,'a') from member)
				,?,?,?
				,?,? )
					""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserName());
			pstmt.setString(2, pw);
			pstmt.setString(3, dto.getFullName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setInt(5, dto.getBirth());
			result = pstmt.executeUpdate();//select문을 제외한 나머지 DML문 처리용도
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

	// 로그인
	public Boolean loginCheckSha256(SignUpDTO dto, Boolean mailChk) { return login(dto, pwSha(dto), mailChk); }
	private Boolean login (SignUpDTO dto, String pw, Boolean mailChk) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.getConn();//DB커넥션
			if( mailChk ) {
				String sql = """
					select email
					from member
					where email like ? and pw like ?
						""";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getEmail());
				pstmt.setString(2, pw);
			} else {
				String sql = """
					select userid
	    		  	from member
    	  			where userid like ? and pw like ?
						""";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getUserName());
				pstmt.setString(2, pw);
			}
			rs = pstmt.executeQuery();//sql 실행
			if (rs.next()) { result = true; }
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
    
	// pw sha
	private String pwSha(SignUpDTO dto) { return sha256(dto.getPw()); }

	// sha256
	private static String sha256(String msg) {
		StringBuilder sb = new StringBuilder();
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("SHA-256");
			byte[] result = mDigest.digest(msg.getBytes());
			for (byte b : result) {
				sb.append(String.format("%02x", b));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}