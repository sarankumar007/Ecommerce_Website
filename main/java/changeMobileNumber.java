

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
 * Servlet implementation class changeMobileNumber
 */
@WebServlet("/changeMobileNumber")
public class changeMobileNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String email=session.getAttribute("email")+"";
		String mobileNumber=request.getParameter("mobileNumber");
		String password=encryptThisString(request.getParameter("password"));
		boolean f=true;
			try {
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
				Statement st=con.createStatement();
				ResultSet rs1=st.executeQuery("select * from users where email='"+email+"' and password='"+password+"'");
				while(rs1.next()) {
					f=false;
					st.executeUpdate("update users set mobileNumber="+mobileNumber+" where email='"+email+"'");
					 response.sendRedirect("changeMobileNumber.jsp?msg=done");
				}
				if(f)
					 response.sendRedirect("changeMobileNumber.jsp?msg=wrong");
				
			
			}
			catch(Exception e) {
				System.out.println(e);
				
			}
		}
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

