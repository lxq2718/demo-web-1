package edu.tjut;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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

import com.github.javafaker.Faker;

@WebServlet("/dbstudent")
//@WebServlet(urlPatterns = {"/dbstudent"},loadOnStartup=1)
public class DBStudentServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  //jdbc模板
  private JdbcTemplate dao;

  public void init(ServletConfig config) throws ServletException {
    try {
      Context ctx = new InitialContext();
      DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/school");
      dao = new JdbcTemplate(ds);
    } catch (NamingException e) {
      e.printStackTrace();
    }
    dao.update("delete from student");
    Faker faker = new Faker(new Locale("zh-CN"));
    for (int i = 1; i <= 10; i++) {
      dao.update("insert into student(id, name, address) values(?,?,?)", i, faker.name().fullName(), faker.address().city());
    }
  }

  public void destroy() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Student> students = dao.query("select * from student", (rst, rowNum) -> new Student(rst.getInt("id"), rst.getString("name"), rst.getString("address")));
    request.setAttribute("allstudents", students);
    request.getRequestDispatcher("/student.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  doGet(request, response);
  }

  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

}
