

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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		if("admin@gmail.com".equals(email)&& "admin".equals(password)) {
			session.setAttribute("email", email);
			response.sendRedirect("admin/adminHome.jsp");
		}
		else {
			boolean f=true;
			try {
				password=encryptThisString(password);
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
				 PreparedStatement pstmt = con.prepareStatement("select * from users where email=? and password=?");
				 pstmt.setString(1,email);
				 pstmt.setString(2,password);
				 ResultSet rs=pstmt.executeQuery();
				 while(rs.next()) {
					 f=false;
					 session.setAttribute("email", email);
					 response.sendRedirect("home.jsp");
				 }
				 if(f)
					 response.sendRedirect("login.jsp?msg=notexist");
				
			}
			catch(Exception e) {
				System.out.println(e);
				response.sendRedirect("login.jsp?msg=invalid");
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
