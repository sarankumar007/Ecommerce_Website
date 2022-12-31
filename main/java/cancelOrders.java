

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
 * Servlet implementation class cancelOrders
 */
@WebServlet("/cancelOrders")
public class cancelOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String email=request.getParameter("email");
		String date=request.getParameter("date");
		String status="Cancel";
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
			Statement st=con.createStatement();
			st.executeUpdate("update cart set status='"+status+"' where product_id='"+id+"' and email='"+email+"' and address is not null and orderDate='"+date+"'");
			response.sendRedirect("admin/ordersReceived.jsp?msg=cancel");
				
			
			
				       
		} catch (Exception e) {
			System.out.println(e);
			response.sendRedirect("admin/ordersReceived.jsp?msg=wrong"); 
		}
		
       
		
	}
}

