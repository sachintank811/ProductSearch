package com.operation.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/full-stack-ecommerce",
					"ecommerceapp", "ecommerceapp");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from product where id = " + request.getParameter("product"));
			out.println("<table border=1>");
			out.println("<tr><th>ProductId</th><th>SKU</th><th>ProductName</th><th>UnitPrice</th><tr>");
			while (rs.next()) {
				String n = rs.getString("id");
				String s = rs.getString("sku");
				String nm = rs.getString("name");
				int u = rs.getInt("unit_price");
				out.println("<tr><td>" + n + "</td><td>" + s + "</td><td>" + nm + "</td><td>" + u + "</td></tr>");
			}
			out.println("</table>");
			out.println("</html></body>");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
