package edu.tjut;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

@WebServlet("/check")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    private JdbcTemplate dao;
    
    public void init(ServletConfig config) throws ServletException {
      try {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/school");
        dao = new JdbcTemplate(ds);
      } catch (NamingException e) {
        e.printStackTrace();
      }
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		//List<User> users = dao.query("select* from user where username ='"+name+"'and password = '"+pwd+"' ",(rst, rowNum) -> new User(rst.getString("username"), rst.getString("password")));
		List<User> users = dao.query("select* from user where username ='"+name+"'",(rst, rowNum) -> new User(rst.getString("username"), rst.getString("password")));
		//boolean flag = false;
		if(users.get(0).getPassword().equals(pwd)) {
			request.setAttribute("allusers", users);
			request.getRequestDispatcher("/user.jsp").forward(request, response);
		}else {
			System.out.println("登录失败!!");
			request.getRequestDispatcher("/error.html").forward(request, response);
		}
/*		for (User user1 : users) {
			String name1 = user1.getUsername();
			String pwd1 = user1.getPassword();
			if(name1.equals(name)&&pwd1.equals(pwd)) {
				flag = true;
				break;
			}
		}*/
/*		if(flag) {
			request.setAttribute("allusers", users);
			request.getRequestDispatcher("/user.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/error.html").forward(request, response);
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
