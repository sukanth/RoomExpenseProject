package com.room.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.room.EMS.Beans.User_bean;

public class UserResultsetExtractor implements ResultSetExtractor{

	@Override
	public Object extractData(ResultSet r) throws SQLException,
			DataAccessException {
		User_bean ub=new User_bean();
		ub.setUsername(r.getString("username"));
		ub.setName(r.getString("expname"));
		ub.setDate(r.getString("expdate"));
		ub.setApplicableto(r.getString("applicableto"));
		ub.setAmount(r.getDouble("amount"));
		ub.setId(r.getInt("id"));
		ub.setStatus(r.getString("status_cd"));
		return ub;
	}

}
