

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class messageUs
 */
@WebServlet("/messageUs")
public class messageUs extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String email=session.getAttribute("email")+"";
		String subject=request.getParameter("subject");
		String body =request.getParameter("body");
			try {
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
				 PreparedStatement pstmt = con.prepareStatement("insert into message (email,subject,body)values(?,?,?)");
				 pstmt.setString(1,email);
				 pstmt.setString(2,subject);
				 pstmt.setString(3,body);
				 pstmt.executeUpdate();
				 response.sendRedirect("messageUs.jsp?msg=done");
				
			
			}
			catch(Exception e) {
				System.out.println(e);
				response.sendRedirect("messageUs.jsp?msg=invalid");
				
			}
		}
	}
