

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class addChangeAddress
 */
@WebServlet("/addChangeAddress")
public class addChangeAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String email=session.getAttribute("email")+"";
		String address=request.getParameter("address");
		String city =request.getParameter("city");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
			try {
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
				 PreparedStatement pstmt = con.prepareStatement("update users set address=?,city=?,state=?,country=? where email=?");
				 pstmt.setString(1,address);
				 pstmt.setString(2,city);
				 pstmt.setString(3,state);
				 pstmt.setString(4,country);
				 pstmt.setString(5, email);
				 pstmt.executeUpdate();
				 response.sendRedirect("addChangeAddress.jsp?msg=valid");
				
			
			}
			catch(Exception e) {
				System.out.println(e);
				response.sendRedirect("addChangeAddress.jsp?msg=invalid");
				
			}
		}
	}




