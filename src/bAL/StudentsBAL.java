package bAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import DBConnect.OrclConnect;
import bean.StudentsBean;

public class StudentsBAL {

	public static List<StudentsBean> getStudents(){
		List<StudentsBean> list=new LinkedList<>();
		Connection connection=OrclConnect.getConnection();
		String sql=" SELECT\n" + 
				"    id,\n" + 
				"    s.name,\n" + 
				"    fathername,\n" + 
				"    email,\n" + 
				"    address,\n" + 
				"    dob,\n" + 
				"    b.name,\n" + 
				"    pic\n" + 
				"FROM\n" + 
				"    students s,\n" + 
				"    batches b\n" + 
				"WHERE\n" + 
				"    s.bid = b.bid ";
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				StudentsBean studentsBean=new StudentsBean(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6),0,rs.getBytes(8));
				studentsBean.setBatchName(rs.getString(7));
				list.add(studentsBean);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public static boolean deleteStd(int id) {
		boolean bool=false;
		String sql="DELETE FROM students WHERE id =? ";
		PreparedStatement prepareStatement;
		try {
			prepareStatement = OrclConnect.getConnection().prepareStatement(sql);
			prepareStatement.setInt(1, id);
			bool=!(prepareStatement.execute());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}
	public static boolean addStudent(StudentsBean studentsBean) {
		//id,name,fathersname,email,address,dob,bid,pic
		boolean isAdded=false;
		String sql="";
		if (studentsBean.getPic().length==0) {
			sql=" INSERT INTO students (name,fathername,email,address,dob,bid) VALUES (?,?,?,?,?,?) ";
		}else {
			sql=" INSERT INTO students (name,fathername,email,address,dob,bid,pic) VALUES (?,?,?,?,?,?,?) ";
		}
		
		try {
			PreparedStatement prepareStatement = OrclConnect.getConnection().prepareStatement(sql);
			
			prepareStatement.setString(1, studentsBean.getName());
			prepareStatement.setString(2, studentsBean.getFathersName());
			prepareStatement.setString(3, studentsBean.getEmail());
			prepareStatement.setString(4, studentsBean.getAddress());
			prepareStatement.setDate(5,studentsBean.getDob());
			prepareStatement.setInt(6, studentsBean.getBID());
			if (studentsBean.getPic().length>0) {
				prepareStatement.setBytes(7, studentsBean.getPic());
			}
			
			isAdded=!prepareStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isAdded;
	}
	public static boolean updateStudents(StudentsBean studentsBean) {
		boolean isUpdated=false;
		String sql="";
		if (studentsBean.getPic().length==0) {
			sql=" UPDATE students SET name =?,fathername =?,email =?,address =?,dob =?,bid =? WHERE id =? ";
		}else {
			 sql=" UPDATE students SET name =?,fathername =?,email =?,address =?,dob =?,bid =?,pic =? WHERE id =? ";
		}
		
		
		try {
			PreparedStatement ps = OrclConnect.getConnection().prepareStatement(sql);
			ps.setString(1,studentsBean.getName());
			ps.setString(2,studentsBean.getFathersName());
			ps.setString(3,studentsBean.getEmail());
			ps.setString(4,studentsBean.getAddress());
			ps.setDate(5,studentsBean.getDob());
			ps.setInt(6, studentsBean.getBID());
			if (studentsBean.getPic().length==0) {
				ps.setInt(7, studentsBean.getId());
			}else {
				ps.setBytes(7, studentsBean.getPic());
				ps.setInt(8, studentsBean.getId());
			}
			
			isUpdated=!(ps.execute());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isUpdated;
	}
}
