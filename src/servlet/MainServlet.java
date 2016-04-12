package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AnswerBean;
import bean.QuestionBean;
import bean.UserBean;

import com.google.gson.Gson;

import dao.Dao;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = (String) request.getParameter("type");
		Dao dao = new Dao();
		if(type.equals("getquestions")){
			ArrayList<QuestionBean> al = dao.getQuestions();
			String json = new Gson().toJson(al);
		    response.setContentType("text/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}
		if(type.equals("getanswers")){
			long qid = Long.parseLong(request.getParameter("qid"));
			ArrayList<AnswerBean> al = dao.getAnswers(qid);
			String json = new Gson().toJson(al);
		    response.setContentType("text/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}
		if(type.equals("getrep")){
			String username = (String) request.getParameter("username");
			UserBean u = dao.getReputation(username);
			String json = new Gson().toJson(u);
		    response.setContentType("text/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}
		if(type.equals("saveanswer")){
			AnswerBean a = new AnswerBean();
			a.setUsername((String) request.getParameter("username"));
			a.setAnswer((String) request.getParameter("answer"));
			a.setUpvotes(0);
			a.setDownvotes(0);
			a.setQuestionId(Integer.parseInt(request.getParameter("qid")));
			long aid  = dao.saveAnswer(a);
			String json = new Gson().toJson(aid);
		    response.setContentType("text/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
