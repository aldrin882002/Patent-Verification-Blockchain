/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Algorithm.AesEncryption;
import Algorithm.Block;
import Algorithm.Transaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class alogin extends HttpServlet {

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
        	        String url = "jdbc:mysql://localhost:3306/patent";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "root");
            PreparedStatement ps;
            Statement st = con.createStatement(); 
                 String uname =request.getParameter("uname");
                 String pwd =request.getParameter("password");
                 
                 
                 if(request.getParameter("Submit")!=null){
                     if((uname.equals(""))||(pwd.equals(""))){
                          request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Please enter all the value");
                     RequestDispatcher rs=request.getRequestDispatcher("i7.jsp");
                      rs.forward(request, response);
                    }else{
                   String vt=null;
                   int f=0;
                 	
                     if((uname.equals("admin"))&&(pwd.equals("admin"))){
                          f=1;
                           Transaction transaction1 = new Transaction(uname, pwd, 100L);
  Transaction transaction2 = new Transaction(uname, pwd, 1000L);
//        Transaction transaction3 = new Transaction(vname, vpass, 1000L);
//        Transaction transaction4 = new Transaction(vname, vpass, 150L);

        Block firstBlock = new Block(0, Arrays.asList(transaction1, transaction2));
         Date date = new Date();
          String f11="";
        
        SimpleDateFormat sdf;

sdf = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
 f11=sdf.format(date);
    int sts=st.executeUpdate("insert into loginblock values('"+firstBlock.hashCode()+"','"+transaction1+"','"+uname+"','"+f11+"','Admin Valid')");

                     }else{
                        Transaction transaction1 = new Transaction(uname, pwd, 100L);
  Transaction transaction2 = new Transaction(uname, pwd, 1000L);
//        Transaction transaction3 = new Transaction(vname, vpass, 1000L);
//        Transaction transaction4 = new Transaction(vname, vpass, 150L);

        Block firstBlock = new Block(0, Arrays.asList(transaction1, transaction2));
         Date date = new Date();
          String f11="";
        
        SimpleDateFormat sdf;

sdf = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
 f11=sdf.format(date);
    int sts=st.executeUpdate("insert into loginblock values('"+firstBlock.hashCode()+"','"+transaction1+"','"+uname+"','"+f11+"','Admin InValid')");
  
                     }
                     if(f==1){
                         request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Welcome to Single User "+uname);
                     RequestDispatcher rs=request.getRequestDispatcher("a1.jsp");
                      rs.forward(request, response);
                     }else{
                         request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Invalid Username && Password");
                     RequestDispatcher rs=request.getRequestDispatcher("i7.jsp");
                      rs.forward(request, response);
                     }
               
                     }
               }else if(request.getParameter("cancel")!=null){
              
  			
  			RequestDispatcher requestdispatcher = request.getRequestDispatcher("i7.jsp");
  			requestdispatcher.forward(request, response);

  		
                  
                  }
                
  		
                
                  else{
                                          
                         request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Please enter the value");
                     RequestDispatcher rs=request.getRequestDispatcher("i7.jsp");
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
