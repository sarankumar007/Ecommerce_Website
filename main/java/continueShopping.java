

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class continueShopping
 */
@WebServlet("/continueShopping")
public class continueShopping extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String email=session.getAttribute("email")+"";
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
			Statement st=con.createStatement();
			st.executeUpdate("update cart set status='processing' where email='"+email+"' and status='bill'");
			response.sendRedirect("home.jsp");
			
		       
		} catch (Exception e) {
			System.out.println(e);
			response.sendRedirect("home.jsp?msg=invalid"); 
		}
		
       
		
	}
}

