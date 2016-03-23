/*
 * Copyright 2012 Wipro Technologies All Rights Reserved
 *
 *Customer specific copyright notice     : Pizza 2 Home.
 *
 * File Name			:	LoginDao.java
 *
 * Short Description	:	Used to perform login operation on database.
 *
 * Version Number	:	1.0.0
 *
 * Created Date		:	15 may 2012
 *
 * Modification History	:	Kulvir,15-MAY-2012
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.LoginBean;
import bean.QuestionBean;
import connection.Connect;

/**
 * class LoginDao for Login Details
 *
 * @version 1.0
 * @author Kulvir
 */
public class Dao {

	private Connection con;// Connection Object
	private PreparedStatement pStmt;// Prepared Statement object
	private ResultSet rSet;// ResultSet Object

	public Dao() {
		con = Connect.myConnection().getConnect();
	}

	public ArrayList<QuestionBean> getQuestions() {
		ArrayList<QuestionBean> al = new ArrayList();
		try {
			pStmt = con
					.prepareStatement("select * from questions");
			rSet = pStmt.executeQuery();
			while (rSet.next()){
				QuestionBean q = new QuestionBean();
				q.setId(rSet.getLong(1));
				q.setQuestion(rSet.getString(2));
				q.setUpvotes(rSet.getLong(3));
				q.setDownvotes(rSet.getLong(4));
				q.setTags(rSet.getString(5));
				al.add(q);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

	public boolean userLogin(LoginBean lBean) {
		return true;
		// try {
		// pStmt = con
		// .prepareStatement("select ud_password from opos_user_details_tb  where pk_ud_user_id = ?");
		// pStmt.setString(1, lBean.getUserId());
		// rSet = pStmt.executeQuery();
		// if (rSet.next())
		// if (rSet.getString(1).equals(lBean.getPassword()))
		// return true;
		//
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		//
		// return false;
		//
	}
}
