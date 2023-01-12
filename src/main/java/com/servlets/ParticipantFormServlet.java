package com.servlets;


import com.DAO.JDBC.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ParticipantFormServlet
 */
public class ParticipantFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParticipantFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<form method='post' action='ParticipantFormServlet'>");
        response.getWriter().println("<div class='form-group'>");
        response.getWriter().println("<label for='name'>Participant Name:</label>");
        response.getWriter().println("<input type='text' class='form-control' name='name' id='name' required>");
        response.getWriter().println("<label for='batchID'>BatchID:</label>");
        response.getWriter().println("<input type='text' class='form-control' name='batchID' id='batchID' required>");
        response.getWriter().println("</div>");
        response.getWriter().println("<button type='submit' class='btn btn-primary'>Submit</button>");
        response.getWriter().println("</form>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String batchID = request.getParameter("batchID");

        // Now you can process the received name value, e.g. by storing it in a database
        ParticipantService particpantService = new ParticipantService();
        particpantService.addParticipant(new Participant(name, Integer.parseInt(batchID)));
    }

}
