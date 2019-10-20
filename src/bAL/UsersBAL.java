package bAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBConnect.OrclConnect;
import bean.UsersBean;

public class UsersBAL {

	public static UsersBean getUser(String email, String password) {

		String sql = " SELECT name,email,password,pic FROM users where email=? and password=? ";

		PreparedStatement preparedStatement;
		UsersBean usersBean=null;
		try {
			preparedStatement = OrclConnect.getConnection().prepareStatement(sql);

			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString(1);
				email = resultSet.getString(2);
				password = resultSet.getString(3);
				byte[] pic = resultSet.getBytes(4);
				usersBean = new UsersBean(name, email, password, pic);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usersBean;
	}
	public static boolean updateUser(UsersBean usersBean) {
		boolean isUpdate=false;
		String sql="";
		if (usersBean.getPic().length==0) {
			sql="UPDATE users SET NAME=?,PASSWORD=? WHERE email =? ";
		}else {
			sql="UPDATE users SET NAME=?,PASSWORD=?,PIC=? WHERE email =? ";
		}
		
		 try {
			PreparedStatement ps = OrclConnect.getConnection().prepareStatement(sql);
			ps.setString(1,usersBean.getName());
			ps.setString(2,usersBean.getPassword());
			if (usersBean.getPic().length==0) {
				ps.setString(3, usersBean.getEmail());
			}else {
				ps.setBytes(3, usersBean.getPic());
				ps.setString(4, usersBean.getEmail());
			}
			isUpdate=!ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return isUpdate;
	}
}
