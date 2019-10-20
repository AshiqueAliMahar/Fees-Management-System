package bAL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import DBConnect.OrclConnect;
import bean.FeesBean;;

public class FeesBAL {
	public static boolean addFees(FeesBean feesBean) {
		boolean isAdded = false;
		String sql = "INSERT INTO fees (paidfees,startdate,enddate,status,stdid) VALUES (?,?,?,?,(select max(id) from STUDENTS)) ";
		try {
			PreparedStatement ps = OrclConnect.getConnection().prepareStatement(sql);
			ps.setInt(1, feesBean.getPaidFees());
			ps.setDate(2, feesBean.getStartDate());
			ps.setDate(3, feesBean.getEndDate());
			ps.setString(4, feesBean.getStatus());
			isAdded = !ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAdded;
	}

	public static boolean updateFees(FeesBean feesBean) {
		
		boolean isUpdated = false;
		String sql = " update fees set paidfees=paidfees+?,startdate=?,enddate=?,status=? where stdid=? ";
		try {
			PreparedStatement ps = OrclConnect.getConnection().prepareStatement(sql);
			ps.setInt(1, feesBean.getPaidFees());
			ps.setDate(2, feesBean.getStartDate());
			ps.setDate(3, feesBean.getEndDate());
			ps.setString(4, feesBean.getStatus());
			ps.setInt(5, feesBean.getStdId());
			isUpdated = !ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isUpdated;
	}
	public static List<FeesBean> getStdsWithFes(){
		List<FeesBean>  list=new LinkedList<>();
		String sql="SELECT\n" + 
				"    id,\n" + 
				"    students.name,\n" + 
				"    batches.name,\n" + 
				"    fees.paidfees,\n" + 
				"    fees.startdate,\n" + 
				"    fees.enddate,\n" + 
				"    fees.status,\n" +
				"	 batches.fees,"
				+ "	 students.pic "	+
				" FROM " + 
				"    students,\n" + 
				"    fees,\n" + 
				"    batches\n" + 
				"WHERE\n" + 
				"    fees.stdid = students.id\n" + 
				"    AND batches.bid = students.bid ";
		try {
			ResultSet rSet = OrclConnect.getConnection().createStatement().executeQuery(sql);
			while (rSet.next()) {
				
				int id = rSet.getInt(1);
				String name = rSet.getString(2);
				String bName = rSet.getString(3);
				int fees = rSet.getInt(4);
				Date startDate = rSet.getDate(5);
				Date endDate = rSet.getDate(6);
				String status = rSet.getString(7);
				int totalBFees=rSet.getInt(8);
				byte [] pic=rSet.getBytes(9);
				//Object Values
				FeesBean feesBean=new FeesBean(fees, startDate, endDate, status,id);
				feesBean.setName(name);
				feesBean.setId(id);
				feesBean.setBatchName(bName);
				feesBean.setFees(totalBFees);
				feesBean.setPic(pic);
				
				list.add(feesBean);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
