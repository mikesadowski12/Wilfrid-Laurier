package stats.ejb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.persistence.EntityNotFoundException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.jpa.model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.toString());
	
	@EJB
	private UserEJBStateless userEJBStateless;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		try {
			User user = userEJBStateless.getUserByName(username);
			
			if (user != null) {
				out.write(username + " found <br>");
				
				if (password.equals(user.getPassword())) {
					LOGGER.info(user.toString());
					
					Integer role = user.getRole();
					switch(role) {
						case 1:
							RequestDispatcher adminView = req.getRequestDispatcher("/admin-view.html");
							adminView.forward(req, resp);
							break;
						case 2:
							RequestDispatcher standardView = req.getRequestDispatcher("/standard-view.html");
							standardView.forward(req, resp);
							break;
						default:
							out.write("Role for " + username + " is unknown <br>");
							break;
					}
				} else {
					out.write("Password for " + username + " is incorrect <br>");
				}
			} 
		} catch (Exception e) {
			out.write(username + " not found <br>");
			throw e;
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
