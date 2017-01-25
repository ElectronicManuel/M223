package com.ubs.main.shop.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ubs.main.shop.annotations.Column;
import com.ubs.main.shop.annotations.Table;
import com.ubs.main.shop.beans.Attribute;

public class DynamicDAO<T> {
	
	private static Map<Class<?>, Integer> sqlTypes;
	public static  Map<Class<?>, Integer> getSqlTypes() {
		if(sqlTypes == null) {
			sqlTypes = new HashMap<Class<?>, Integer>();
			sqlTypes.put(String.class, Types.VARCHAR);
			sqlTypes.put(Integer.class, Types.INTEGER);
			sqlTypes.put(int.class, Types.INTEGER);
		}
		return sqlTypes;
	}
	
	private String table;
	
	private List<Attribute> persistentFields;

	public DynamicDAO(Class<T> classType) {
		persistentFields = new ArrayList<Attribute>();
		
		for(Annotation a : classType.getDeclaredAnnotations()) {
			if(a instanceof Table) {
				table = ((Table)a).name();
			}
		}
		for(Field field : classType.getDeclaredFields()) {
			if(field.getDeclaredAnnotation(Column.class) != null) {
				Attribute a = new Attribute(field);
				persistentFields.add(a);
			}
		}
	}
	
	/* INSERT */
	
	public int insert(T toInsert) throws IllegalArgumentException, IllegalAccessException, SQLException {
		// Generate SQL String
		
		String sql = getInsertString();
		
		// Open Connection
		Connection con = DBConnector.openConnection(Connection.TRANSACTION_REPEATABLE_READ);
		
		PreparedStatement pstmt = fillPlaceHolders(con, sql, toInsert, true);
		
		int result = pstmt.executeUpdate();
		con.commit();
		
		pstmt.close();
		con.close();
		
		return result;
	}
	
	private String getInsertString() {
		String sql = "INSERT INTO " + table + " (";
		sql = sql + getColumnsCommaSeparated(true);
		sql = sql + ") VALUES (";
		sql = sql + getPlaceholdersCommaSeparated(true);
		
		sql = sql + ");";
		
		System.out.println(sql);
		return sql;
	}
	
	/* UPDATE */
	
	
	
	/* DELETE */
	
	
	
	/* SELECT */
	
	
	
	/* Useful methods */
	private String getColumnsCommaSeparated(boolean includeIds) {
		int counter = 0;
		String s = "";
		// Add Column names
		for (Attribute a : persistentFields) {
			counter++;
			if(a.isId() && includeIds || !a.isId()) {
				if (counter < persistentFields.size()) {
					s = s + a.getColumn() + ", ";
				} else {
					s = s + a.getColumn();
				}
			}
		}
		return s;
	}
	
	private String getPlaceholdersCommaSeparated(boolean includeIds) {
		int counter = 0;
		String s = "";
		// Add Column names
		for (Attribute a : persistentFields) {
			counter++;
			if(a.isId() && includeIds || !a.isId()) {
				if (counter < persistentFields.size()) {
					s = s + "?, ";
				} else {
					s = s + "?";
				}
			}
		}
		return s;
	}
	
	private PreparedStatement fillPlaceHolders(Connection con, String sql, T toFilWith, boolean includeIds) throws IllegalArgumentException, IllegalAccessException, SQLException {
		PreparedStatement pstmt = con.prepareStatement(sql);
		int counter = 1;
		for(Attribute a : persistentFields) {
			a.calcValue(toFilWith);
			Object o = a.getValue();
			if(o == null) {
				pstmt.setNull(counter, getSqlTypes().get(a.getType()));
			}
			else {
				pstmt.setObject(counter, o, getSqlTypes().get(a.getType()));
			}
			
			counter++;
		}
		return pstmt;
	}
	
}
