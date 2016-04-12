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

import bean.AnswerBean;
import bean.LoginBean;
import bean.QuestionBean;
import bean.UserBean;
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
			pStmt = con.prepareStatement("select * from questions");
			rSet = pStmt.executeQuery();
			while (rSet.next()) {
				QuestionBean q = new QuestionBean();
				q.setId(rSet.getLong(1));
				q.setQuestion(rSet.getString(2));
				q.setUpvotes(rSet.getLong(3));
				q.setDownvotes(rSet.getLong(4));
				q.setTags(rSet.getString(5));
				q.setUsername(rSet.getString(6));
				al.add(q);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

	public ArrayList<AnswerBean> getAnswers(long qid) {
		ArrayList<AnswerBean> al = new ArrayList();
		try {
			pStmt = con
					.prepareStatement("select * from answers where questionid = ?");
			pStmt.setLong(1, qid);
			rSet = pStmt.executeQuery();
			while (rSet.next()) {
				AnswerBean a = new AnswerBean();
				a.setId(rSet.getLong(1));
				a.setAnswer(rSet.getString(2));
				a.setUpvotes(rSet.getLong(3));
				a.setDownvotes(rSet.getLong(4));
				a.setUsername(rSet.getString(5));
				a.setQuestionId(rSet.getLong(6));
				al.add(a);
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

	public long saveAnswer(AnswerBean a) {
		long aid = 0;
		try {
			pStmt = con.prepareStatement("select max(id) from answers");
			rSet = pStmt.executeQuery();
			if (rSet.next()) {
				aid = rSet.getLong(1) + 1;
			}

			pStmt = con
					.prepareStatement("insert into answers values(?,?,?,?,?,?)");
			pStmt.setInt(1, (int) aid);
			pStmt.setString(2, a.getAnswer());
			pStmt.setInt(3, (int) a.getUpvotes());
			pStmt.setInt(4, (int) a.getDownvotes());
			pStmt.setString(5, a.getUsername());
			pStmt.setInt(6, (int) a.getQuestionId());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aid;
	}

	public UserBean getReputation(String username) {
		UserBean u = new UserBean();
		try {
			pStmt = con
					.prepareStatement("select * from users where username = ?");
			pStmt.setString(1, username);
			rSet = pStmt.executeQuery();
			if (rSet.next()) {
				u.setUsername(rSet.getString(1));
				u.setJava(rSet.getLong(2));
				u.setCpp(rSet.getLong(3));
				u.setPython(rSet.getLong(4));
				u.setCsharp(rSet.getLong(5));
				u.setJavascript(rSet.getLong(6));
				u.setQuiz(rSet.getString(7));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
}
