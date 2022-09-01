package member;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

import config.DB;

public class MemberDAO {

	// sql 
	static String insertSql(Boolean crypto) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into member (code,userid,passwd,name,email) values(?,");
		if(crypto) sql.append("PACK_ENCRYPTION_DECRYPTION.FUNC_ENCRYPT(?),?,?,?,?,?,?)");
		else sql.append("?,?,?,?)");
		return sql.toString();
	}
	
	static String loginSql(Boolean crypto) {
		StringBuilder sql = new StringBuilder();
		sql.append("select name from member where userid=? and passwd=");
		if(crypto) sql.append("PACK_ENCRYPTION_DECRYPTION.FUNC_ENCRYPT(?)");
		else sql.append("?");
		return sql.toString();
	}
	
	// pw
	private String pw(MemberDTO dto) { return dto.getPw(); }
	private String pwSha(MemberDTO dto) { return sha256(dto.getPw()); }


	//
	private MemberDTO setDTO(MemberDTO dto, ResultSet rs) throws SQLException {
		dto.setUserid(rs.getString("userid"));
		dto.setPw(rs.getString("pw"));
		dto.setName(rs.getString("name"));
		dto.setEmail(rs.getString("email"));
		dto.setJoinDate(rs.getDate("joindate"));
		return dto;
	}
	
	public ArrayList<MemberDTO> memberList(){
		ArrayList<MemberDTO> items = new ArrayList<>();	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.getConn();//DB커넥션
			String sql="select * from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();//sql 실행
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto = setDTO(dto, rs);
				items.add(dto);//리스트에 dto 추가
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
		return items;
	}

	// insert
	public void insert(MemberDTO dto) { insertCON(dto, insertSql(false), pw(dto)); }
	public void insertCrypto(MemberDTO dto) { insertCON(dto, insertSql(true), pw(dto)); }
	public void insertSha256(MemberDTO dto) { insertCON(dto, insertSql(false), pwSha(dto)); }

		// insert crypto or not
	private void insertCON(MemberDTO dto, String sql, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, pw);
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			pstmt.executeUpdate();//select문을 제외한 나머지 DML문 처리용도
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
	}

	// detail
	public MemberDTO memberDetail(String userid) {
		MemberDTO dto = new MemberDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=DB.getConn();
			String sql="select * from member where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = setDTO(dto, rs);
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
		return dto;
	}

	public void update(MemberDTO dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = DB.getConn();
			String sql = """
				update member 
				set passwd=?, name=?, email=?, hp=?, zipcode=?, address1=?, address2=? 
				where userid=?
					""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(8, dto.getUserid());
			pstmt.executeUpdate();
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
	}

	public void delete(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.getConn();
			String sql="delete from member where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
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
	}

	// 로그인 crypto or not (nomal, oracle crypto, sha256)
	private String loginCON (MemberDTO dto, String sql, String pw) {
		String result = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.getConn();//DB커넥션
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, pw);

			rs = pstmt.executeQuery();//sql 실행
			if (rs.next()) {
				result = rs.getString("name") + "님 환영합니다.";
			} else result = "로그인 실패";
			
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

    public String loginCheck(MemberDTO dto) { return loginCON(dto, loginSql(true), pw(dto)); }
    public String loginCheckOracle(MemberDTO dto) { return loginCON(dto, loginSql(false), pw(dto)); }
	public String loginCheckSha256(MemberDTO dto) { return loginCON(dto, loginSql(false), pwSha(dto)); }

	// 로그인 Bcrypt
	public String loginCheckBcrypt(MemberDTO dto) {
		String result = "로그인 실패";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.getConn();//DB커넥션
			String sql = "select name, passwd from member where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserid());
			rs = pstmt.executeQuery();//sql 실행

			if ( rs.next() ) {
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
		return result;
	}


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

	// Bcrypt

}
