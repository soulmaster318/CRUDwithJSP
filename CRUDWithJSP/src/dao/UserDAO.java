package dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.UserInfo;

public class UserDAO {
	
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ServletTest","root","3108");
		}
		catch(Exception e) {
			System.out.print(e);}
		return conn;
	}
	public void AddUser(UserInfo u) {
		
		try {
			Connection conn= UserDAO.getConnection();
			PreparedStatement pre=conn.prepareStatement("insert into UserInfo(email,password,firstname,lastname,country,phoneno) values (?,?,?,?,?,?)");
			pre.setString(1,u.getEmail());
			pre.setString(2,u.getPassword());
			pre.setString(3,u.getFirstname());
			pre.setString(4,u.getLastname());
			pre.setString(5,u.getCountry());
			pre.setString(6,u.getPhoneno());
			
			pre.executeUpdate();
			
			conn.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	public List<UserInfo> getAllUsers(){
		List<UserInfo> list = new ArrayList<UserInfo>();
		try {
			Connection conn= UserDAO.getConnection();
			PreparedStatement pre=conn.prepareStatement("select * from UserInfo");
			ResultSet rs=pre.executeQuery();
			while(rs.next()) {
				UserInfo u =new UserInfo();
				u.setId(rs.getInt(1));
				u.setEmail(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setFirstname(rs.getString(4));
				u.setLastname(rs.getString(5));
				u.setCountry(rs.getString(6));
				u.setPhoneno(rs.getString(7));
				list.add(u);
			}
			conn.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	public UserInfo SelectUserByID(int id) {
		UserInfo u =new UserInfo();
		try {
			Connection conn= UserDAO.getConnection();
			PreparedStatement pre=conn.prepareStatement("select * from UserInfo where id=?");
			pre.setInt(1,id);
			ResultSet rs=pre.executeQuery();
			if(rs.next()) {
				u.setId(rs.getInt(1));
				u.setEmail(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setFirstname(rs.getString(4));
				u.setLastname(rs.getString(5));
				u.setCountry(rs.getString(6));
				u.setPhoneno(rs.getString(7));
			}
			conn.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return u;
	}
	public void UpdateUser(UserInfo u) {
		
		try {
			Connection conn= UserDAO.getConnection();
			PreparedStatement pre=conn.prepareStatement("update UserInfo set password=?,firstname=?,lastname=?,country=?,phoneno=? where id=?");
			
			pre.setString(1, u.getPassword());
			pre.setString(2,u.getFirstname());
			pre.setString(3,u.getLastname());
			pre.setString(4,u.getCountry());
			pre.setString(5,u.getPhoneno());
			pre.setInt(6,u.getId());
			
			pre.executeUpdate();
			
			conn.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	public void DeleteUser(int id) {
		
		try {
			Connection conn= UserDAO.getConnection();
			PreparedStatement pre=conn.prepareStatement("delete from UserInfo where id=?");
			pre.setInt(1,id);
			pre.executeUpdate();
			
			conn.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	public static boolean validate(UserInfo u) {
		boolean status=false;
		try {
			Connection conn=UserDAO.getConnection();
			PreparedStatement pre=conn.prepareStatement("select from UserInfo where email=? and password=?");
			pre.setString(1,u.getEmail());
			pre.setString(2, u.getPassword());
			
			pre.executeUpdate();
			
			conn.close();
		}
		catch(Exception ex) {
			
		}
		return status;
	}
	public static UserInfo findUser(Connection conn, String email) throws SQLException {
		 
        String sql = "select * from UserInfo where email=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, email);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
        	 UserInfo u = new UserInfo();
             u.setId(rs.getInt(1));
 			u.setEmail(rs.getString(2));
 			u.setPassword(rs.getString(3));
 			u.setFirstname(rs.getString(4));
 			u.setLastname(rs.getString(5));
 			u.setCountry(rs.getString(6));
 			u.setPhoneno(rs.getString(7));
            return u;
        }
        return null;
    }
	public static UserInfo findUser(Connection conn, String email,String password) throws SQLException {
		 
        String sql = "select * from UserInfo where email=? and password=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, email);
        pstm.setString(2, password);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            
            
            UserInfo u = new UserInfo();
            u.setId(rs.getInt(1));
			u.setEmail(rs.getString(2));
			u.setPassword(rs.getString(3));
			u.setFirstname(rs.getString(4));
			u.setLastname(rs.getString(5));
			u.setCountry(rs.getString(6));
			u.setPhoneno(rs.getString(7));
            return u;
        }
        return null;
    }
}
