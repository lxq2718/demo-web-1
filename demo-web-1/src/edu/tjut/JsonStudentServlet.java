package edu.tjut;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.github.javafaker.Faker;

@WebServlet(urlPatterns = { "/jsonstudent" }, loadOnStartup = 1)
public class JsonStudentServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private List<Student> students = new LinkedList<Student>();

  public JsonStudentServlet() {
  }

/*  public void init(ServletConfig config) throws ServletException {
    Faker faker = new Faker(new Locale("zh-CN"));
    for(int i=1; i<=10; i++) {
      students.add(new Student(i, faker.name().fullName(), faker.address().city()));
    }
    System.out.println(students);  /////////
  }*/

  public void destroy() {
    students.clear();
    System.out.println("Student Servlet is dead");
  }

  // GET /jsonstudent?id=1
  // GET /jsonstudent
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setCharacterEncoding("utf-8");
    PrintWriter out = response.getWriter();
    String p = request.getParameter("id");
    if (p != null) {
      int id = Integer.parseInt(p);
      try {
        Student student = students.stream().filter(s -> s.getId() == id).findFirst().get();
        out.print(JSON.toJSONString(student));
      } catch(NoSuchElementException e) {
        out.print(JSON.toJSONString(null));
      }
    } else { // 取所有学生
      out.print(JSON.toJSONString(students));
    }
  }

  // POST /jsonstudent?id=1&name=张三&address=天津市
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    String address = request.getParameter("address");
    students.add(new Student(id, name, address));
    request.setAttribute("allstudents", students);
    request.getRequestDispatcher("/student.jsp").forward(request, response);
  }

  
  
  
  
  
  // PUT /jsonstudent?id=1&name=张三&address=天津市
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    int id = Integer.parseInt(request.getParameter("id"));
    try {
      Student student = students.stream().filter(s -> s.getId() == id).findFirst().get();
      Enumeration<String> paras = request.getParameterNames();
      while(paras.hasMoreElements()) {
        String p = paras.nextElement();
        if ("name".equals(p)) {
          student.setName(request.getParameter("name"));
        } 
        if ("address".equals(p)) {
          student.setAddress(request.getParameter("address"));
        }
      }
      out.print(0);
    } catch(NoSuchElementException e) {
      out.print(1);
    }
  }

  // DELETE /jsonstudent?id=1
  // DELETE /jsonstudent
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    String p = request.getParameter("id");
    if (p != null) {
      int id = Integer.parseInt(p);
      boolean isRemoved = students.removeIf(s -> s.getId() == id);
      if (isRemoved) {
        out.print(0);
      } else {
        out.print(1);
      }
    } else { // 删除全部学生
      students.clear();
      out.print(0);
    }
  }

}
