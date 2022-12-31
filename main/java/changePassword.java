

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class changePassword
 */
@WebServlet("/changePassword")
public class changePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String email=session.getAttribute("email")+"";
		String oldPass=request.getParameter("oldPass");
		oldPass=encryptThisString(oldPass);
		String newPass=request.getParameter("newPass");
		String confirmPass=request.getParameter("confirmPass");
		if(!confirmPass.equals(newPass))
			response.sendRedirect("changePassword.jsp?msg=notMatch");
		else {
		boolean f=true;
			try {
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
				 PreparedStatement pstmt = con.prepareStatement("select * from users where email=? and password=?");
				 pstmt.setString(1,email);
				 pstmt.setString(2,oldPass);
				 ResultSet rs=pstmt.executeQuery();
				 while(rs.next()) {
					 newPass=encryptThisString(newPass);
					 f=false;
					 pstmt.executeUpdate("update users set password='"+newPass+"' where email='"+email+"'");
					 response.sendRedirect("changePassword.jsp?msg=done");
				 }
				 if(f)
					 response.sendRedirect("changePassword.jsp?msg=wrong");
			
			}
			catch(Exception e) {
				System.out.println(e);
				
			}
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
