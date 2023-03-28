/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Algorithm.AesEncryption;
import Algorithm.Block;
import Algorithm.Transaction;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class pverify extends HttpServlet {

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

            String url1;

            HttpSession so = request.getSession(true);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/patent", "root", "root");
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
            Statement st2 = con.createStatement();
            Statement st3 = con.createStatement();
            Statement st4 = con.createStatement();
            Statement st5 = con.createStatement();
            Statement st6 = con.createStatement();
            int size;
            int flag=0;
            String mail="";
            String a11=request.getParameter("a1");
                        String a12=request.getParameter("a2");
            long mob = 0;
            String b = request.getParameter("b");
            String b1 = request.getParameter("b1");
            String a1="",a2="",a3="",a4="",bt1="",phone="",phone1=""; 
            AesEncryption asc=new AesEncryption();
              System.out.println("select * from patentapply where pid='"+b+"'");
   ResultSet rs=st.executeQuery("select * from patentapply where pid='"+b+"'");
       if(rs!=null){
          
            if (request.getParameter("t1") != null) {
                String v = null;
                java.util.Date st11 = new java.util.Date();
                // Formatting date into  yyyy-MM-dd HH:mm:ss e.g 2008-10-10 11:21:10
     
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String f = formatter.format(st11);
                System.out.println("update patentapply set ack1 ='Accept' where pid='"+b+"'");

              
                    
                 int v1=st.executeUpdate("update patentapply set ack2 ='Accept',name='admin',pdates='"+f+"' where pid='"+b+"'");
                 if(v1==1){
                        Transaction transaction1 = new Transaction(b, so.getAttribute("un").toString(), 1000L);
  Transaction transaction2 = new Transaction(b, so.getAttribute("un").toString(), 10000L);
//        Transaction transaction3 = new Transaction(vname, vpass, 1000L);
//        Transaction transaction4 = new Transaction(vname, vpass, 150L);

        Block firstBlock = new Block(0, Arrays.asList(transaction1, transaction2));
         Date date = new Date();
          String f11="";
        
        SimpleDateFormat sdf;

sdf = new SimpleDateFormat("yyyy-M-dd");
 f11=sdf.format(date);
    int sts=st1.executeUpdate("insert into pblock values('"+firstBlock.hashCode()+"','"+transaction1+"','Accept','"+b+"','"+so.getAttribute("un")+"','"+f11+"','Product Check')");

                         request.setAttribute("ok", "1");
                          request.setAttribute("msg", "Accepted Succesfully");
                              
                       RequestDispatcher rs2 = request.getRequestDispatcher("a5.jsp");
                rs2.forward(request, response);
                 }else{
                 
                         request.setAttribute("ok", "1");
                          request.setAttribute("msg", "Accepted failed");
                              
                       RequestDispatcher rs2 = request.getRequestDispatcher("a5.jsp");
                rs2.forward(request, response);
                 }
                     
                     
                    } else   if (request.getParameter("t3") != null) {
               String v = null;
                java.util.Date st11 = new java.util.Date();
                // Formatting date into  yyyy-MM-dd HH:mm:ss e.g 2008-10-10 11:21:10
     
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String f = formatter.format(st11);
                System.out.println("Formatted date is ==>" + f);

                    ResultSet rs1=st1.executeQuery("select * from patentapply where pid='"+b+"'");
			if(rs1.next())
			{
                            byte[] byteArray = new byte[1048576];
				InputStream Asset = rs1.getBinaryStream(11);
				//request.setAttribute("Asset", Asset);
				response.reset();
				//response.setContentType("image/jpeg");
				response.setContentType("application/pdf");
				while((size=Asset.read(byteArray))!=-1)
				{
					response.getOutputStream().write(byteArray, 0, size);	
				}
                                 RequestDispatcher rs2 = request.getRequestDispatcher("a5.jsp");
                rs2.forward(request, response);
                        }

                       else{
                             RequestDispatcher rs12 = request.getRequestDispatcher("a5.jsp");
                rs12.forward(request, response);
                        }   
                     
                    } else  if (request.getParameter("t2") != null) {
                String v = null;
                java.util.Date st11 = new java.util.Date();
                // Formatting date into  yyyy-MM-dd HH:mm:ss e.g 2008-10-10 11:21:10
     
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String f = formatter.format(st11);
                System.out.println("Formatted date is ==>" + f);

            
                    
                 int v1=st.executeUpdate("update patentapply  set ack2='Denied',name='admin',pdates='"+f+"' where pid='"+b+"'");
                 if(v1==1){
                                             Transaction transaction1 = new Transaction(b, so.getAttribute("un").toString(), 1000L);
  Transaction transaction2 = new Transaction(b, so.getAttribute("un").toString(), 10000L);
//        Transaction transaction3 = new Transaction(vname, vpass, 1000L);
//        Transaction transaction4 = new Transaction(vname, vpass, 150L);

        Block firstBlock = new Block(0, Arrays.asList(transaction1, transaction2));
         Date date = new Date();
          String f11="";
        
        SimpleDateFormat sdf;

sdf = new SimpleDateFormat("yyyy-M-dd");
 f11=sdf.format(date);
    int sts=st1.executeUpdate("insert into pblock values('"+firstBlock.hashCode()+"','"+transaction1+"','Denied','"+b+"','"+so.getAttribute("un")+"','"+f11+"','Product Check')");
                         request.setAttribute("ok", "1");
                          request.setAttribute("msg", "Denied Succesfully");
                              
                       RequestDispatcher rs2 = request.getRequestDispatcher("a5.jsp");
                rs2.forward(request, response);
                 }else{
                 
                         request.setAttribute("ok", "1");
                          request.setAttribute("msg", "Denied failed");
                              
                       RequestDispatcher rs2 = request.getRequestDispatcher("a5.jsp");
                rs2.forward(request, response);
                 }
                     
                     
                    } else{
                         RequestDispatcher rs2 = request.getRequestDispatcher("a5.jsp");
                rs2.forward(request, response);
                    }

    

       }else{
                         RequestDispatcher rs2 = request.getRequestDispatcher("a5.jsp");
                rs2.forward(request, response);
                    }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {            
            out.close();
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
