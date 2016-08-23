package com.room.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.room.EMS.Beans.User_bean;

public class ActiveExpensesResultsetExtractor {
    public Object extractData(ResultSet r) throws SQLException{
	User_bean ub=new User_bean();
	ub.setUsername(r.getString("username"));
	ub.setName(r.getString("expname"));
	ub.setDate(r.getString("expdate"));
	ub.setApplicableto(r.getString("applicableto"));
	ub.setAmount(r.getDouble("amount"));
	return ub;
	
    }

}
