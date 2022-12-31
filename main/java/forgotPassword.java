

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


/**
 * Servlet implementation class forgotPassword
 */
@WebServlet("/forgotPassword")
public class forgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String phno=request.getParameter("mobilenumber");
		String securityQuestion=request.getParameter("securityQuestion");
		String answer=request.getParameter("answer");
		String newpassword=request.getParameter("password");
		
			boolean f=true;
			try {
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
				 PreparedStatement pstmt = con.prepareStatement("select * from users where email=? and mobileNumber=? and securityQuestion=? and answer=?");
				 pstmt.setString(1,email);
				 pstmt.setString(2,phno);
				 pstmt.setString(3, securityQuestion);
				 pstmt.setString(4, answer);
				 ResultSet rs=pstmt.executeQuery();
				 while(rs.next()) {
					 newpassword=encryptThisString(newpassword);
					 f=false;
					 pstmt.executeUpdate("update users set password='"+newpassword+"' where email='"+email+"'");
					 response.sendRedirect("forgotPassword.jsp?msg=done");
				 }
				 if(f)
					 response.sendRedirect("forgotPassword.jsp?msg=invalid");
				rs.close();
			}
			catch(Exception e) {
				System.out.println(e);
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
