

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class removeCart
 */
@WebServlet("/removeCart")
public class removeCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String id=request.getParameter("id");
		String q=request.getParameter("q");
		String email=session.getAttribute("email")+"";
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
			Statement st=con.createStatement();
		    st.executeUpdate("delete  from cart where product_id='"+id+"' and email='"+email+"' and address is NULL");
		    response.sendRedirect("myCart.jsp?msg=removed");
			
			}
		catch (Exception e) {
			System.out.println(e);
			response.sendRedirect("home.jsp?msg=invalid");
		}
		
       
		
	}
}
