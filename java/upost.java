/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Algorithm.AesEncryption;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
public class upost extends HttpServlet {

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
                 HttpSession so = request.getSession(true);
            AesEncryption asc = new AesEncryption();
            Connection con = null;
            String url = "jdbc:mysql://localhost:3306/patent";
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(url, "root", "root");

            Statement smt = con.createStatement();
            Statement smt1 = con.createStatement();
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String contact ="E:\\"+ request.getParameter("contact");
            String qual = request.getParameter("address");

            String ques1 = "E:\\"+request.getParameter("ques1");
            String inspection = request.getParameter("ques3");
            String ques4 ="E:\\"+ request.getParameter("ques4");
            if (request.getParameter("add") != null) {
                if ((fname.equals("")) || (lname.equals("")) || (contact.equals("")) || (qual.equals("")) || (ques1.equals("")) ) {
                    request.setAttribute("ok", "1");
                    request.setAttribute("msg", "Please enter the value");
                    RequestDispatcher rs = request.getRequestDispatcher("s3.jsp");
                    rs.forward(request, response);
                } else {
                    String vt = null;
                    ResultSet rt = smt.executeQuery("select * from patentapply where pname='" + fname + "' ");
                    if (rt.next()) {
                      
                     
          
                            request.setAttribute("ok", "1");
                            request.setAttribute("msg", "Please enter the value");
                            RequestDispatcher rs = request.getRequestDispatcher("s3.jsp");
                            rs.forward(request, response);

                    } else{
                        int uid = 0;
                        ResultSet rt1 = smt1.executeQuery("select max(pid) from patentapply");
                        if (rt1.next()) {
                            uid = Integer.parseInt(rt1.getString(1)) + 1;

                        }
                        String st = uid + "";
                  File pro = new File(contact);
                FileInputStream product = new FileInputStream(pro);
                
                  File qua = new File(ques1);
                FileInputStream quality = new FileInputStream(qua);
                File ins = new File(ques4);
                FileInputStream inspect = new FileInputStream(ins);
               java.util.Date st11 = new java.util.Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String f = formatter.format(st11);
                  PreparedStatement pstmt = con.prepareStatement("insert into patentapply values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pstmt.setString(1, st);
                pstmt.setString(2, fname);
                pstmt.setString(3, asc.toEncrypt(lname.getBytes()));
                pstmt.setString(4, pro.getName());
                pstmt.setBinaryStream(5, product, pro.length());
                pstmt.setString(6, asc.toEncrypt(qual.getBytes()));
                pstmt.setString(7, qua.getName());
                pstmt.setBinaryStream(8, quality, qua.length());
                pstmt.setString(9, asc.toEncrypt(inspection.getBytes()));
                pstmt.setString(10, ins.getName());
                pstmt.setBinaryStream(11, inspect, ins.length());
                    pstmt.setString(12, "");
                 pstmt.setString(13, "");
              pstmt.setString(14, "");
              pstmt.setString(15, "");
                 pstmt.setString(16, "");
              pstmt.setString(17, "");
                 pstmt.setString(18, "");
                 pstmt.setString(19, "");
              pstmt.setString(20, "");
              pstmt.setString(21, so.getAttribute("un")+"");
                 pstmt.setString(22, f);
            int i=  pstmt.executeUpdate();
                          if (i == 1) {
                            request.setAttribute("ok", "1");
                            request.setAttribute("msg", "Successfully stored");
                            RequestDispatcher rs = request.getRequestDispatcher("s3.jsp");
                            rs.forward(request, response);
                        } else {
                            request.setAttribute("ok", "1");
                            request.setAttribute("msg", "Please enter the value");
                            RequestDispatcher rs = request.getRequestDispatcher("s3.jsp");
                            rs.forward(request, response);
                        }
                    }
                        
                    
                }
            } else if (request.getParameter("cancel") != null) {

                RequestDispatcher requestdispatcher = request.getRequestDispatcher("s3.jsp");
                requestdispatcher.forward(request, response);

            } else {

                request.setAttribute("ok", "1");
                request.setAttribute("msg", "Please enter the value");
                RequestDispatcher rs = request.getRequestDispatcher("s3.jsp");
                rs.forward(request, response);
            }
        } catch (Exception e) {

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
