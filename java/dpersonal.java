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
public class dpersonal extends HttpServlet {

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
                
                 String a1 =request.getParameter("a1");
                 String a2 =request.getParameter("a2");
               String a3 =request.getParameter("a3");
                 String a4 =request.getParameter("a4");
                  String a5 =request.getParameter("a5");
                 String a6 =request.getParameter("a6");
                  String a7 =request.getParameter("a7");
                 String a8 =request.getParameter("a8");
                  String a9 =request.getParameter("a9");
                 String a10 =request.getParameter("a10"); 
                 String a11 =request.getParameter("a11");
                 String a12 =request.getParameter("a12");
                 
                 
                 
                 if(request.getParameter("add")!=null){
                     if((a1.equals(""))||(a2.equals(""))||(a3.equals(""))||(a4.equals(""))||(a5.equals(""))||(a6.equals(""))||(a7.equals(""))||(a8.equals(""))||(a9.equals(""))||(a10.equals(""))){
                          request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Please enter the value");
                     RequestDispatcher rs=request.getRequestDispatcher("d2.jsp");
                      rs.forward(request, response);
                    }else{
                   String vt=null;
                 	ResultSet rt=smt.executeQuery("select * from employee where eid='"+a1+"' ");
                 if(rt.next()){
                        int v=   smt.executeUpdate("update employee set ename='"+asc.toEncrypt(a2.getBytes())+"',pass='"+a3+"',designation='"+asc.toEncrypt(a4.getBytes())+"',experience='"+asc.toEncrypt(a5.getBytes())+"',contactno='"+asc.toEncrypt(a6.getBytes())+"',address='"+asc.toEncrypt(a7.getBytes())+"',email='"+asc.toEncrypt(a8.getBytes())+"' ,dob='"+asc.toEncrypt(a9.getBytes())+"',doj='"+asc.toEncrypt(a8.getBytes())+"' where eid='"+a1+"'");
                  if(v==1){
                      request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Updated Successfully");
                     RequestDispatcher rs=request.getRequestDispatcher("d2.jsp");
                      rs.forward(request, response);
                  }else{
                         request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Please enter the value");
                     RequestDispatcher rs=request.getRequestDispatcher("d2.jsp");
                      rs.forward(request, response);
                  }
                     
                  }else {
                 request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Username Already Exist");
                     RequestDispatcher rs=request.getRequestDispatcher("d2.jsp");
                      rs.forward(request, response); 	 
                	
                      
  	   }}
               }else if(request.getParameter("cancel")!=null){
              
  			
  			RequestDispatcher requestdispatcher = request.getRequestDispatcher("d2.jsp");
  			requestdispatcher.forward(request, response);

  		
                  
                  }
                
  		
                
                  else{
                                          
                         request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Please enter the value");
                     RequestDispatcher rs=request.getRequestDispatcher("d2.jsp");
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
