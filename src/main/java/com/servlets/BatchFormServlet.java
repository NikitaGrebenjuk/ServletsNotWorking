package com.servlets;

import com.DAO.JDBC.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class BatchFormServlet
 */
public class BatchFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BatchFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<form method='post' action='BatchFormServlet'>");
        response.getWriter().println("<div class='form-group'>");
        response.getWriter().println("<label for='batchName'>Batch Name:</label>");
        response.getWriter().println("<input type='text' class='form-control' name='batchName' id='batchName' required>");
        response.getWriter().println("</div>");
        response.getWriter().println("<div class='form-group'>");
        response.getWriter().println("<label for='batchId'>Batch ID:</label>");
        response.getWriter().println("<input type='text' class='form-control' name='batchId' id='batchId' required>");
        response.getWriter().println("</div>");
        response.getWriter().println("<button type='submit' class='btn btn-primary'>Submit</button>");
        response.getWriter().println("</form>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String batchName = request.getParameter("batchName");
        String batchId = request.getParameter("batchId");
        // Now you can process the received batchName and batchId values, e.g. by storing it in a database

        BatchService batchService = new BatchService();
        batchService.addBatch(new Batch( Integer.parseInt(batchId), batchName));
    }

}
