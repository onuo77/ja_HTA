package com.sample.hr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sample.hr.vo.Location;
import com.sample.utils.ConnectionUtil;

/**
 * LOCATIONS 테이블에 대한 CRUD기능을 제공하는 클래스다.
 * @author user
 *
 */
public class LocationDao {

	/**
	 * 모든 부서 소재지 정보를 반환한다.
	 * @return 부서소재지 목록
	 * @throws SQLException
	 */
	public List<Location> getAllLocations() throws SQLException{
		List<Location> locations = new ArrayList<Location>();
		
		String sql = "select * "
					+ "from locations "
					+ "order by city asc ";
		
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql); //쿼리를 전송하여 실행되도록 하는 역할
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) { //지금 가르키는 위치가 null이 아닌 것
			Location location = new Location();
			location.setId(rs.getInt("location_id"));
			location.setStreetAddress(rs.getString("street_address"));
			location.setPostalCode(rs.getString("postal_code"));
			location.setCity(rs.getString("city"));
			location.setState(rs.getString("state_province"));
			location.setCountryId(rs.getString("country_id"));
			
			locations.add(location);
		}
		
		rs.close();
		pstmt.close();
		con.close();
		
		return locations;
	}
}
