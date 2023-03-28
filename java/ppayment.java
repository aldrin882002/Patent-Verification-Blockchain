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
public class ppayment extends HttpServlet {

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
            String url = "jdbc:mysql://localhost:3306/charity";
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(url, "root", "root");

            Statement smt = con.createStatement();
            Statement smt1 = con.createStatement();
            String pid = request.getParameter("pid");
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String contact = request.getParameter("contact");
            String address = request.getParameter("address");
            String ques1 = request.getParameter("ques3");
            String amt = request.getParameter("amt");
            String cd = request.getParameter("cd");
            String cd1 = request.getParameter("cd1");
            String cd2 = request.getParameter("cd2");
            String unames = request.getParameter("unames");
            String vt2 = request.getParameter("vt2");
            if (request.getParameter("add") != null) {
                if ((pid.equals("")) || (fname.equals("")) || (lname.equals("")) || (contact.equals("")) || (address.equals("")) || (ques1.equals("")) || (cd.equals("")) || (cd1.equals("")) || (cd2.equals(""))) {
                    request.setAttribute("ok", "1");
                    request.setAttribute("msg", "Please enter the value");
                    RequestDispatcher rs = request.getRequestDispatcher("i8.jsp");
                    rs.forward(request, response);
                } else {
                    String vt = null, vt1 = "",vt3="";
                    int amt1 = 0, amt2 = 0, v = 0;

                    int uid = 0;
                    ResultSet rt1 = smt1.executeQuery("select max(oid) from payment");
                    if (rt1.next()) {
                        uid = Integer.parseInt(rt1.getString(1)) + 1;

                    }
                    String st = uid + "";
                    ResultSet rt = smt1.executeQuery("select * from ppost where pid='" + pid + "'");
                    if (rt.next()) {
                        amt1 = Integer.parseInt(rt.getString(9)) + Integer.parseInt(amt);
                        vt = rt.getString(10);
                        vt1 = rt.getString(14);
                        vt3 = rt.getString(13);
                    }
                    if (vt1.equals("Organization")) {
                        ResultSet rt2 = smt1.executeQuery("select * from oregister where uname='" + vt + "'");
                        if (rt2.next()) {
                            amt2 = Integer.parseInt(rt2.getString(14)) + Integer.parseInt(amt);
                            v = smt.executeUpdate("update oregister set amount='" + amt2 + "' where uname='" + vt + "'");
                        }

                    } else if (vt1.equals("single")) {
                        ResultSet rt2 = smt1.executeQuery("select * from register where uname='" + vt + "'");
                        if (rt2.next()) {
                            amt2 = Integer.parseInt(rt2.getString(14)) + Integer.parseInt(amt);

                            v = smt.executeUpdate("update register set amount='" + amt2 + "' where uname='" + vt + "'");
                        }

                    }
                    java.util.Date st11 = new java.util.Date();
                // Formatting date into  yyyy-MM-dd HH:mm:ss e.g 2008-10-10 11:21:10

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String f = formatter.format(st11);

                    System.out.println("insert into payment values('" + st + "','" + asc.toEncrypt(fname.getBytes()) + "','" + asc.toEncrypt(lname.getBytes()) + "','" + asc.toEncrypt(contact.getBytes()) + "','" + asc.toEncrypt(address.getBytes()) + "','" + asc.toEncrypt(ques1.getBytes()) + "','" + asc.toEncrypt(ques1.getBytes()) + "','','0','','','','')");
                    v = smt.executeUpdate("insert into payment values('" + st + "','" + pid + "','" + asc.toEncrypt(fname.getBytes()) + "','" + asc.toEncrypt(lname.getBytes()) + "','" + asc.toEncrypt(contact.getBytes()) + "','" + asc.toEncrypt(address.getBytes()) + "','" + asc.toEncrypt(ques1.getBytes()) + "','" + asc.toEncrypt(ques1.getBytes()) + "','" + amt + "','" + so.getAttribute("un") + "','" + vt2+ "','" + f + "','" + vt1 + "','" + vt+ "')");
                    v = smt.executeUpdate("update ppost set amount='" + amt1 + "' where pid='" + pid + "'");
 Transaction transaction1 = new Transaction(fname, lname, 100L);
  Transaction transaction2 = new Transaction(fname, lname, 1000L);
//        Transaction transaction3 = new Transaction(vname, vpass, 1000L);
//        Transaction transaction4 = new Transaction(vname, vpass, 150L);

        Block firstBlock = new Block(0, Arrays.asList(transaction1, transaction2));
         Date date = new Date();
          String f11="";
        
        SimpleDateFormat sdf;

sdf = new SimpleDateFormat("yyyy-M-dd");
 f11=sdf.format(date);
 System.out.println("insert into pblock values('"+firstBlock.hashCode()+"','"+transaction1+"','"+pid+"','"+fname+"','"+lname+"','"+amt+"','"+vt+"','"+vt2+"','"+f11+"','"+vt1+"')");
    int sts=smt.executeUpdate("insert into pblock values('"+firstBlock.hashCode()+"','"+transaction1+"','"+pid+"','"+fname+"','"+lname+"','"+amt+"','"+vt+"','"+vt2+"','"+f11+"')");

                    if (v == 1) {
                        request.setAttribute("ok", "1");
                        request.setAttribute("msg", "Successfully stored");
                        RequestDispatcher rs = request.getRequestDispatcher("i8.jsp");
                        rs.forward(request, response);
                    } else {
                        request.setAttribute("ok", "1");
                        request.setAttribute("msg", "Please enter the value");
                        RequestDispatcher rs = request.getRequestDispatcher("i8.jsp");
                        rs.forward(request, response);
                    }

                }
            } else if (request.getParameter("cancel") != null) {

                RequestDispatcher requestdispatcher = request.getRequestDispatcher("i8.jsp");
                requestdispatcher.forward(request, response);

            } else {

                request.setAttribute("ok", "1");
                request.setAttribute("msg", "Please enter the value");
                RequestDispatcher rs = request.getRequestDispatcher("i8.jsp");
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
