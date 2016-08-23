package com.room.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ActiveExpensesRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int arg1) throws SQLException {
	ActiveExpensesResultsetExtractor activeExpensesResultsetExtractor=new ActiveExpensesResultsetExtractor();
	Object ob=activeExpensesResultsetExtractor.extractData(rs);
	return ob;
    }

}
