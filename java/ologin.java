/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Algorithm.AESDecryption;
import Algorithm.AesEncryption;
import Algorithm.Block;
import Algorithm.Transaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class ologin extends HttpServlet {

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
      	      String url="jdbc:mysql://localhost:3306/patent";
	      String driver="com.mysql.jdbc.Driver";
	      Class.forName(driver);
	       con=(Connection)DriverManager.getConnection(url, "root","root");
AesEncryption asc=new AesEncryption();
            AESDecryption dsc=new AESDecryption();
                 Statement smt=con.createStatement(); Statement smt1=con.createStatement();
          
                 String uname =request.getParameter("uname");
                 String pwd =request.getParameter("password");
                 String role="";
                   HttpSession so = request.getSession(true);
                 if(request.getParameter("Submit")!=null){
                     if((uname.equals(""))||(pwd.equals(""))){
                          request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Please enter all the value");
                     RequestDispatcher rs=request.getRequestDispatcher("i5.jsp");
                      rs.forward(request, response);
                    }else{
                   String vt=null;
                   int f=0;
                 	ResultSet rt=smt.executeQuery("select * from employee where eid='"+uname+"'and status='Active' ");
                 if(rt.next()){
                     if((uname.equals(rt.getString(1)))&&(pwd.equals(rt.getString(3)))){
                          f=1;
                          role=dsc.toDeycrypt(rt.getString(4));
                          so.setAttribute("un", uname);
                        so.setAttribute("un1", role);
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
    int sts=smt.executeUpdate("insert into loginblock values('"+firstBlock.hashCode()+"','"+transaction1+"','"+uname+"','"+f11+"','Organization Valid')");

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
    int sts=smt.executeUpdate("insert into loginblock values('"+firstBlock.hashCode()+"','"+transaction1+"','"+uname+"','"+f11+"','Organization InValid')");
  
                     }
                     if((f==1)&&(role.equals("INSPECTION"))){
                         request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Welcome to Single User "+uname);
                     RequestDispatcher rs=request.getRequestDispatcher("n1.jsp");
                      rs.forward(request, response);
                     }else if((f==1)&&(role.equals("Quantity Check"))){
                         request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Welcome to Single User "+uname);
                     RequestDispatcher rs=request.getRequestDispatcher("d1.jsp");
                      rs.forward(request, response);
                     }else{
                         request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Invalid Username && Password");
                     RequestDispatcher rs=request.getRequestDispatcher("i5.jsp");
                      rs.forward(request, response);
                     }
                  }else {
                	 
                    request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Please Enter Username && Password");
                     RequestDispatcher rs=request.getRequestDispatcher("i5.jsp");
                      rs.forward(request, response);
                 }
                     }
               }else if(request.getParameter("cancel")!=null){
              
  			
  			RequestDispatcher requestdispatcher = request.getRequestDispatcher("i5.jsp");
  			requestdispatcher.forward(request, response);

  		
                  
                  }
                
  		
                
                  else{
                                          
                         request.setAttribute("ok", "1");
                      request.setAttribute("msg", "Please enter the value");
                     RequestDispatcher rs=request.getRequestDispatcher("i5.jsp");
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
