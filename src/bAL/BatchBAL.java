package bAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import DBConnect.OrclConnect;
import bean.BatchBean;

public class BatchBAL {
	public static List<BatchBean> getBatches() {
		List<BatchBean> list=new LinkedList<>();
		String sql=" select bid,name,duration,fees FROM batches ";
		try {
			ResultSet rs = OrclConnect.getConnection().createStatement().executeQuery(sql);
			while (rs.next()) {
				BatchBean batchBean=new BatchBean(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				list.add(batchBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public static boolean deleteBatch(int id) {
		boolean bool=false;
		String sql="DELETE FROM batches WHERE bid =? ";
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
	public static boolean addBatch(BatchBean batchBean) {
		boolean bool=false;
		String sql=" INSERT INTO batches (name,duration,fees) VALUES (?,?,?) ";
		PreparedStatement prepareStatement;
		try {
			prepareStatement = OrclConnect.getConnection().prepareStatement(sql);
			prepareStatement.setString(1, batchBean.getBatchName());
			prepareStatement.setInt(2, batchBean.getDurationInMnths());
			prepareStatement.setInt(3, batchBean.getFees());
			bool=!(prepareStatement.execute());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}
	public static boolean updateBatch(BatchBean batchBean) {
		boolean bool=false;
		String sql=" UPDATE batches SET NAME=?,DURATION=?,FEES=? WHERE bid =? ";
		PreparedStatement prepareStatement;
		try {
			prepareStatement = OrclConnect.getConnection().prepareStatement(sql);
			prepareStatement.setString(1, batchBean.getBatchName());
			prepareStatement.setInt(2, batchBean.getDurationInMnths());
			prepareStatement.setInt(3, batchBean.getFees());
			prepareStatement.setInt(4, batchBean.getbId());
			bool=!(prepareStatement.execute());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}
}
