package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class MartDAO {
	public Vector searchMart(String num) {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.dbConn();
			String sql="select num, place, name, company, date, "
					+ " price, amount, money"
					+ "from mart where num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				row.add(rs.getString("num"));
				row.add(rs.getString("place"));
				row.add(rs.getString("name"));
				row.add(rs.getString("company"));
				row.add(rs.getString("date"));
				row.add(rs.getInt("price"));
				row.add(rs.getInt("amount"));
				row.add(rs.getInt("money"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return items;
	}
	
	public int insertMart(MartDTO dto) {
	int result=0;
	Connection conn=null;
	PreparedStatement pstmt=null;
	try {
		conn=DB.dbConn();
		StringBuilder sb=new StringBuilder();
		sb.append("insert into mart values(?,?,?,?,?,?,?,?)");
		pstmt=conn.prepareStatement(sb.toString());
		pstmt.setString(1, dto.getNum());
		pstmt.setString(2, dto.getPlace());
		pstmt.setString(3, dto.getName());
		pstmt.setString(4, dto.getCompany());
		pstmt.setString(5, dto.getDate());
		pstmt.setInt(6, dto.getPrice());
		pstmt.setInt(7, dto.getAmount());
		pstmt.setInt(8, dto.getMoney());
		result=pstmt.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	return result;
	
	
	}
	
	public int updateMart(MartDTO dto) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=DB.dbConn();
			StringBuilder sb=new StringBuilder();
			sb.append("update mart ");
			sb.append(" set place=?, name=?, company=?, date=? ");
			sb.append(" , price=?, amount=?, money=?" );
			sb.append(" where num=?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getPlace());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getCompany());
			pstmt.setString(4, dto.getDate());
			pstmt.setInt(5, dto.getPrice());
			pstmt.setInt(6, dto.getAmount());
			pstmt.setInt(7, dto.getMoney());
			pstmt.setString(8, dto.getNum());
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public Vector listMart() {
		Vector items = new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=DB.dbConn();
			StringBuilder sb=new StringBuilder();
			sb.append("select num, place, name, company, date, ");
			sb.append(" price, amount, money ");
			sb.append(" from mart ");
			pstmt=conn.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row = new Vector();
				row.add(rs.getString("num"));
				row.add(rs.getString("place"));
				row.add(rs.getString("name"));
				row.add(rs.getString("company"));
				row.add(rs.getString("date"));
				row.add(rs.getInt("price"));
				row.add(rs.getInt("amount"));
				row.add(rs.getInt("money"));
				items.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return items;
	}
	
	public int deleteMart(String num) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.dbConn();
			StringBuilder sb=new StringBuilder();
			sb.append("delete from mart where num=? ");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, num);
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	
	
}
