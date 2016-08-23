package com.room.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.room.EMS.Beans.login_bean;
import com.room.EMS.util.EmsUtil;

public class LoginResultsetExtractor implements ResultSetExtractor{

	@Override
	public Object extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		// TODO Auto-generated method stub
		login_bean lb=new login_bean();
		lb.setUsername(rs.getString("username"));
		/*lb.setPassword(EmsUtil.decrypt(rs.getString("password")));*/
		lb.setPassword(rs.getString("password"));
		lb.setRole(rs.getString("role"));
		lb.setEmail(rs.getString("email"));
		lb.setBankAccount(rs.getString("BankAccount"));
		return lb;
	}
	
}
