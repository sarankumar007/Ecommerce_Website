

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class editProduct
 */
@WebServlet("/editProduct")
public class editProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String category=request.getParameter("category");
		String price=request.getParameter("price");
		String active=request.getParameter("active");
		Connection con=null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "sarankumarr007");
			 PreparedStatement pstmt = con.prepareStatement("update product set name=?,category=?,price=?,active=? where id=?");
		        pstmt.setString(1,name);
		        pstmt.setString(2,category );
		        pstmt.setString(3,price);
		        pstmt.setString(4,active);
		        pstmt.setString(5,id);
		        pstmt.executeUpdate();
		        if(active.equals("no"))
		        	pstmt.executeUpdate("delete from cart where product_id='"+id+"' and address is NULL");
		       response.sendRedirect("admin/allProductEditProduct.jsp?msg=done");
		} catch (Exception e) {
			System.out.println(e);
			response.sendRedirect("admin/allProductEditProduct.jsp?msg=invalid"); 
		}
		
       
		
	}
}
