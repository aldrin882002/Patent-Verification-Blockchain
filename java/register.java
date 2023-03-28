/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Algorithm.AesEncryption;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	AesEncryption asc=new AesEncryption();
    	     Connection con=null;
      	      String url="jdbc:mysql://localhost:3306/patent";
	      String driver="com.mysql.jdbc.Driver";
	      Class.forName(driver);
	       con=(Connection)DriverManager.getConnection(url, "root","root");

                 Statement smt=con.createStatement(); Statement smt1=con.createStatement();
                 String fname =request.getParameter("fname");
                 String lname =request.getParameter("lname");
                 String uname =request.getParameter("uname");
                 String pwd =request.getParameter("pwd");
                 String contact =request.getParameter("contact");
                 String address =request.getParameter("address");
                 String email =request.getParameter("email");
                 String dob =request.getParameter("dob");
                 String gender =request.getParameter("gender");
                 String role =request.getParameter("role");
                 String ques1 ="E:\\"+request.getParameter("ques1");
                 String ques2 =request.getParameter("ques2");
                 String ques3 =request.getParameter("ques3");
                 
                 if(request.getParameter("add")!=null){
                     if((fname.equals(""))||(lname.equals(""))||(uname.equals(""))||(pwd.equals(""))||(contact.equals(""))||(address.equals(""))||(email.equals(""))||(dob.equals(""))||(ques1.equals(""))||(ques2.equals(""))||(ques3.equals(""))){
                          request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Please enter the value");
                     RequestDispatcher rs=request.getRequestDispatcher("i1.jsp");
                      rs.forward(request, response);
                    }else{
                          File file = new File(ques1);
                FileInputStream fis = new FileInputStream(file);
                long filesize = file.length();
		long filesizeInKB = filesize / 1024;
                   String vt=null;
                 	ResultSet rt=smt.executeQuery("select * from register where uname='"+uname+"' ");
                 if(rt.next()){
                      request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Username Already Exist");
                     RequestDispatcher rs=request.getRequestDispatcher("i1.jsp");
                      rs.forward(request, response); 
                  }else {
                	  int uid=0;
                	  ResultSet rt1=smt1.executeQuery("select max(uid) from register");
                      if(rt1.next()){
                    	  uid=Integer.parseInt(rt1.getString(1))+1;

                      }
                      String st=uid+"";
                      
                       PreparedStatement pstmt = con.prepareStatement("insert into register values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    pstmt.setString(1, st);
                pstmt.setString(2, asc.toEncrypt(fname.getBytes()));
                 pstmt.setString(3, asc.toEncrypt(lname.getBytes()));
                pstmt.setString(4, uname);
                pstmt.setString(5, pwd);
                pstmt.setString(6, asc.toEncrypt(contact.getBytes()));
                pstmt.setString(7, asc.toEncrypt(address.getBytes()));
                pstmt.setString(8, asc.toEncrypt(email.getBytes()));
                pstmt.setString(9, asc.toEncrypt(dob.getBytes()));
                pstmt.setString(10, asc.toEncrypt(gender.getBytes()));
                 pstmt.setBinaryStream(11, fis, file.length());
                pstmt.setString(12, file.getName());
                 pstmt.setString(13, asc.toEncrypt(ques2.getBytes()));
                    pstmt.setString(14, asc.toEncrypt(ques3.getBytes()));
                       pstmt.setString(15, " ");
                    
                      int v=  pstmt.executeUpdate();
  	    //  int v=smt.executeUpdate("insert into register values('"+st+"','"+asc.toEncrypt(fname.getBytes())+"','"+asc.toEncrypt(lname.getBytes())+"','"+uname+"','"+pwd+"','"+asc.toEncrypt(contact.getBytes())+"','"+asc.toEncrypt(address.getBytes())+"','"+asc.toEncrypt(email.getBytes())+"','"+asc.toEncrypt(dob.getBytes())+"','"+asc.toEncrypt(gender.getBytes())+"','"+asc.toEncrypt(ques2.getBytes())+"','"+asc.toEncrypt(ques3.getBytes())+"','')");
                  if(v==1){
                      request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Successfully stored");
                     RequestDispatcher rs=request.getRequestDispatcher("i1.jsp");
                      rs.forward(request, response);
                  }else{
                         request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Please enter the value");
                     RequestDispatcher rs=request.getRequestDispatcher("i1.jsp");
                      rs.forward(request, response);
                  }}}
               }else if(request.getParameter("cancel")!=null){
              
  			
  			RequestDispatcher requestdispatcher = request.getRequestDispatcher("i1.jsp");
  			requestdispatcher.forward(request, response);

  		
                  
                  }
                
  		
                
                  else{
                                          
                         request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Please enter the value");
                     RequestDispatcher rs=request.getRequestDispatcher("i1.jsp");
                      rs.forward(request, response);
                  }
        }catch (Exception e) {

            e.printStackTrace();

		}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
