package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RankDB {
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	String sql;
	StringBuilder sb = new StringBuilder();
	String url = "jdbc:mysql://127.0.0.1:3306/test";
	String user = "root";
	String pw = "kim111";

	public void UpdateDB(String name,boolean set){

		if(set){
			sql = sb.append("update fortress set" ).append(" win = win + 1")
					.append(" where name = ").append("'"+name+"';").toString();
		}
		else if(!set){
			sql = sb.append("update fortress set" ).append(" lose = lose + 1")
					.append(" where name = ").append("'"+name+"';").toString();
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pw);
			if(conn==null){
				System.out.println("fail");
			}
			else{
				System.out.println("success");
			}
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
			System.out.println("update Success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<RankVO> selectRank(){
		RankVO vo;
		ArrayList<RankVO> list = new ArrayList<RankVO>();			
		String sql = "select name,win,lose from fortress";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pw);
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while(rs.next()){
				vo = new RankVO();
				vo.setName(rs.getString(1));
				vo.setWin(rs.getInt(2));
				vo.setLose(rs.getInt(3));
				list.add(vo);
			}
			System.out.println("select Success");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
