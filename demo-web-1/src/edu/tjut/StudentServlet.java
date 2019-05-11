package edu.tjut;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.javafaker.Faker;

@WebServlet(name = "aaa", urlPatterns = { "/student" })
public class StudentServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  List<Student> students = new LinkedList<Student>();
  
  public StudentServlet() {
  }

  public void init(ServletConfig config) throws ServletException {
    Faker faker = new Faker(new Locale("zh-CN"));
    for (int i = 1; i <= 10; i++) {
      students.add(new Student(i, faker.name().fullName(), faker.address().city()));
    }
    System.out.println(students);  ////////////
  }

  public void destroy() {
    students.clear();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("allstudents", students);
    request.getRequestDispatcher("/student.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

}
