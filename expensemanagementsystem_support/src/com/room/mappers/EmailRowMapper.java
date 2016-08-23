package com.room.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class EmailRowMapper implements RowMapper {

    @Override
    public String mapRow(ResultSet rs, int arg1) throws SQLException {
		
		return rs.getString("email");
	}

}
