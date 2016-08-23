package com.room.EMS.DataAccess;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.room.EMS.Beans.IndividualExpense;
import com.room.EMS.Beans.User_bean;
import com.room.EMS.Service.user_service;
import com.room.mappers.CalculationRowMapper;
import com.room.mappers.IndividualExpenseRowMapper;
import com.room.mappers.UserRowMapper;
import com.room.mappers.YourRowMapper;

@Repository
public class User_dao {

	@Autowired
	JdbcTemplate jdbc;
	@Autowired
	user_service user_service;

	public int addexpense(User_bean expense) {

		Object params[] = new Object[] { expense.getUsername(),
				expense.getName(), expense.getDate(),expense.getApplicableto(),expense.getAmount(),expense.getDatta(),expense.getKiran(),expense.getGunda(),expense.getVamsi(),expense.getChowdary(),expense.getUday(),expense.getVihar(),expense.getZubair(),expense.getMurali(),expense.getSudheer(),"ACTIVE",expense.getId()};

		String sql = "insert into expense values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int i = jdbc.update(sql, params);
		return i;
	}
	@SuppressWarnings("unchecked")
	public List viewall(String username) {
		// TODO Auto-generated method stub
		List<User_bean> l = jdbc.query("select * from expense where status_cd='ACTIVE' AND amount <> 0 AND applicableto LIKE('%"+username+"%')",
				new UserRowMapper());
		return l;
	}

	public List viewyour(String username) {
		// TODO Auto-generated method stub
		List<User_bean> l1=jdbc.query("select * from expense where username=? AND status_cd='ACTIVE' AND amount <> 0",new Object[]{username},new YourRowMapper());
		return l1;
	}

	public List fetchUsers() {
		List ulist=jdbc.queryForList("select username from admin where role='user' AND STATUS_CD='A'", String.class);
		return ulist;
	}

	public List<IndividualExpense> getCalculations(String username) {
		// TODO Auto-generated method stub
		
		List<IndividualExpense> cal=jdbc.query("select sum(datta) datta,sum(gunda) gunda,sum(kiran) kiran,sum(uday) uday,sum(chowdary) chowdary,sum(vamsi) vamsi,sum(vihar) vihar,sum(zubair) zubair,sum(murali) murali,sum(sudheer) sudheer,username from expense where status_cd='ACTIVE'  AND username=? GROUP BY username",new Object[]{username}, new CalculationRowMapper());
		return cal;
	}

	public List<IndividualExpense> getIndividualExpense(String username) {
		// TODO Auto-generated method stub
		
		String sql="select sum("+username+") expense,username from expense where status_cd='ACTIVE' and username <> ? GROUP BY username";
		List<IndividualExpense> cal=jdbc.query(sql,new Object[]{username}, new IndividualExpenseRowMapper());
		return cal;
	}
	public boolean changePassword(String changedpassword,String username) {
		// TODO Auto-generated method stub
		boolean b = false;
		String sql = "update admin set password=?,modified_dt=sysdate() where username=?";
		Object params[] = new Object[] { changedpassword,username };
		int i = jdbc.update(sql, params);
		if (i == 1) {
			b = true;
		} else
			b = false;
		return b;
	}
	public int configureAlerts(User_bean expense) {

		Object params[] = new Object[] {expense.getUsername(),expense.getIsAlertRequired() };
		String sql = "update admin SET isAlertRequired=? where username=?";
		int i = jdbc.update(sql, params);
		return i;
	}
	public List<Map<String, Object>> getBankAccountDetails() {
	    List<Map<String, Object>> list = jdbc.queryForList("select username , BankAccount from admin where status_cd='A' and role <> 'admin'");
	    return list;
	}

}
