

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class addressPaymentForOrder
 */
@WebServlet("/addressPaymentForOrder")
public class addressPaymentForOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String email=session.getAttribute("email")+"";
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		String mobileNumber=request.getParameter("mobileNumber");
		String paymentMethod=request.getParameter("paymentMethod");
		String transactionId=request.getParameter("transactionId");
		String status="bill";
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
			 PreparedStatement pstmt = con.prepareStatement("update users set address=?,city=?,state=?,country=?,mobilenumber=? where email=?");
			 pstmt.setString(1, address);
			 pstmt.setString(2, city);
			 pstmt.setString(3, state);
			 pstmt.setString(4, country);
			 pstmt.setString(5, mobileNumber);
			 pstmt.setString(6, email);
			 
			 pstmt.executeUpdate();
			 PreparedStatement pstmt1 = con.prepareStatement("update cart set address=?,city=?,state=?,country=?,mobilenumber=?, orderDate=now(),deliveryDate=date_add(orderDate,interval 7 DAY),paymentMethod=?,transactionId=?,status=? where email=? and address is NULL");
			 pstmt1.setString(1, address);
			 pstmt1.setString(2, city);
			 pstmt1.setString(3, state);
			 pstmt1.setString(4, country);
			 pstmt1.setString(5, mobileNumber);
			 pstmt1.setString(6, paymentMethod);
			 pstmt1.setString(7, transactionId);
			 pstmt1.setString(8, status);
			 pstmt1.setString(9, email);

			 pstmt1.executeUpdate();
			 response.sendRedirect("bill.jsp");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
