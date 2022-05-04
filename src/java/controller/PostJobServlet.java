/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CategoryDAO;
import dal.JobDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Category;

/**
 *
 * @author thinh
 */
@WebServlet(name = "PostJobServlet", urlPatterns = {"/postjob"})
public class PostJobServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        CategoryDAO CategoryDAO = new CategoryDAO();
        List<Category> listC = CategoryDAO.getAllCategory();
        request.setAttribute("listC", listC);
        request.getRequestDispatcher("PostJob.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        String name = request.getParameter("jName");
        String company = request.getParameter("company");
        String logo = request.getParameter("logo");
        String location = request.getParameter("location");
        String CategoryID = request.getParameter("CategoryID");
        String desc = request.getParameter("desc");
        String jd = request.getParameter("jd");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String closingDate = request.getParameter("closingDate");
        String type = request.getParameter("type");
        String salary = request.getParameter("salary");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String publicDate = dtf.format(now);
        JobDAO dao = new JobDAO();
        dao.add(a.getId(), name, company, logo, CategoryID, type, salary, desc, jd, location, publicDate, closingDate, phone, email);
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/JavaScript\">");
        out.println("alert(\"Post Job successful\")");
        out.println("location=\"postjob\";");
        out.println("</script>");
//        response.sendRedirect("postjob");
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
