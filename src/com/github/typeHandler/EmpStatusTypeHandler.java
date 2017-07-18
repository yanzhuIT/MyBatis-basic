package com.github.typeHandler;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.github.bean.EmpStatus;

public class EmpStatusTypeHandler implements TypeHandler<EmpStatus>{

	@Override
	public void setParameter(PreparedStatement ps, int i, EmpStatus parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.getCode().toString());
		
	}

	@Override
	public EmpStatus getResult(ResultSet rs, String columnName) throws SQLException {
		Integer code = rs.getInt(columnName);
		return EmpStatus.getStatusByCode(code);
	}

	@Override
	public EmpStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
		Integer code = rs.getInt(columnIndex);
		return EmpStatus.getStatusByCode(code);
	}

	@Override
	public EmpStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
		Integer code = cs.getInt(columnIndex);
		return EmpStatus.getStatusByCode(code);
	}

}
