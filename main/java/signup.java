

import java.sql.*;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String mobileNumber=request.getParameter("mobilenumber");
		String securityQuestion=request.getParameter("securityQuestion");
		String answer=request.getParameter("answer");
		String password=request.getParameter("password");
		password=encryptThisString(password);
		String address="";
		String city="";
		String state="";
		String country="";
		Connection con=null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
			 PreparedStatement pstmt = con.prepareStatement("INSERT INTO users  VALUES(?,?,?,?,?,?,?,?,?,?)");
		        pstmt.setString(1,name);
		        pstmt.setString(2,email );
		        pstmt.setString(3,mobileNumber );
		        pstmt.setString(4,securityQuestion);
		        pstmt.setString(5,answer);
		        pstmt.setString(6,password );
		        pstmt.setString(7,address);
		        pstmt.setString(8,city);
		        pstmt.setString(9,state);
		        pstmt.setString(10,country );
		        pstmt.executeUpdate();
		       response.sendRedirect("signup.jsp?msg=valid");
		} catch (Exception e) {
			System.out.println(e);
			response.sendRedirect("signup.jsp?msg=invalid"); 
		}
		
       
		
	}
	
	//hashing for passwords
	 public static String encryptThisString(String input)
	    {
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-512");

	            byte[] messageDigest = md.digest(input.getBytes());

	       
	            BigInteger no = new BigInteger(1, messageDigest);

	          
	            String hashtext = no.toString(16);

	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }

	            return hashtext;
	        }

	      
	        catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }

}
