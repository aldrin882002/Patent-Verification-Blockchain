/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Algorithm.AesEncryption;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class oregister extends HttpServlet {

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
        	
    	     Connection con=null;
      	      String url="jdbc:mysql://localhost:3306/charity";
	      String driver="com.mysql.jdbc.Driver";
	      Class.forName(driver);
	       con=(Connection)DriverManager.getConnection(url, "root","root");
AesEncryption asc=new AesEncryption();
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
                 String ques1 =request.getParameter("ques1");
                 String ques2 =request.getParameter("ques2");
                 String ques3 =request.getParameter("ques3");
                 
                 if(request.getParameter("add")!=null){
                     if((lname.equals(""))||(uname.equals(""))||(pwd.equals(""))||(contact.equals(""))||(address.equals(""))||(email.equals(""))||(dob.equals(""))||(ques1.equals(""))||(ques2.equals(""))||(ques3.equals(""))){
                          request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Please enter the value");
                     RequestDispatcher rs=request.getRequestDispatcher("i2.jsp");
                      rs.forward(request, response);
                    }else{
                   String vt=null;
                 	ResultSet rt=smt.executeQuery("select * from oregister where uname='"+uname+"' ");
                 if(rt.next()){
                      request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Username Already Exist");
                     RequestDispatcher rs=request.getRequestDispatcher("i2.jsp");
                      rs.forward(request, response); 
                  }else {
                	  int uid=0;
                	  ResultSet rt1=smt1.executeQuery("select max(oid) from oregister");
                      if(rt1.next()){
                    	  uid=Integer.parseInt(rt1.getString(1))+1;

                      }
                      String st=uid+"";
  	      int v=   smt.executeUpdate("insert into oregister values('"+st+"','"+asc.toEncrypt(lname.getBytes())+"','"+uname+"','"+pwd+"','"+asc.toEncrypt(contact.getBytes())+"','"+asc.toEncrypt(address.getBytes())+"','"+asc.toEncrypt(email.getBytes())+"','"+asc.toEncrypt(dob.getBytes())+"','"+asc.toEncrypt(ques2.getBytes())+"','"+asc.toEncrypt(ques3.getBytes())+"','Orphanage','','0')");
                  if(v==1){
                      request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Successfully stored");
                     RequestDispatcher rs=request.getRequestDispatcher("i2.jsp");
                      rs.forward(request, response);
                  }else{
                         request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Please enter the value");
                     RequestDispatcher rs=request.getRequestDispatcher("i2.jsp");
                      rs.forward(request, response);
                  }}}
               }else if(request.getParameter("cancel")!=null){
              
  			
  			RequestDispatcher requestdispatcher = request.getRequestDispatcher("i2.jsp");
  			requestdispatcher.forward(request, response);

  		
                  
                  }
                
  		
                
                  else{
                                          
                         request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Please enter the value");
                     RequestDispatcher rs=request.getRequestDispatcher("i2.jsp");
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
