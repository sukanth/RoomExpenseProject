package com.room.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import com.room.EMS.Beans.email_bean;


public class EmailExtractor {

	public Object extractData(ResultSet rs) throws SQLException,
	DataAccessException {
	// TODO Auto-generated method stub
	email_bean ie=new email_bean();
	ie.setToAddress(rs.getString("email"));
		return ie;
	}

}
