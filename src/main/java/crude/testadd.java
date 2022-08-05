package crude;


import java.io.BufferedReader;

import java.sql.SQLException;
	
	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	
import java.sql.Types;
import java.util.HashMap;

import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

	/**
	 * Servlet implementation class AddInvoice
	 */
	@WebServlet("/testadd")
	public class testadd extends HttpServlet {
		private static final long serialVersionUID = 1L;

		//private static Connection conn = null;
		//private static PreparedStatement stmt = null;
	    
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public testadd() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	response.getWriter().append("Served at: ").append(request.getContextPath());
	    	//request.getRequestDispatcher("/WEB-INF/views/test.jsp").forward(request, response);
	    }
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String invoice = null;
			
			try {
				BufferedReader reader = request.getReader();
				invoice = reader.readLine();
				System.out.println(invoice);
//				System.out.println(invoice.getClass().getName());
				
//				System.out.println(invoice.split(","));
//				System.out.println(invoice.split("\"\""));
//				System.out.println(invoice.split(","));
				
				invoice =  invoice.substring(1, invoice.length() - 1);
				String final_values[] = invoice.split(",");
				
				for(int i = 0; i < final_values.length; ++i) {
					final_values[i] = final_values[i].split(":")[1];
					final_values[i] = final_values[i].substring(1, final_values[i].length() - 1);
					System.out.println(final_values[i]);
				}
				String BusinessCode=final_values[0];
				String docId=final_values[1];
				String invoiceCurrency=final_values[2];
				
				
				
				Connection conn = Conn.dbconnector();
				String sql_statement = "INSERT INTO food (food_item ,serving,price) values (  ?, ?, ?)";
			
				PreparedStatement st = conn.prepareStatement(sql_statement);
				st.setString(1, BusinessCode);
				st.setString(2, docId);
				st.setString(3, invoiceCurrency);
			
				
				
				
				st.executeUpdate();
//				//conn.commit();
			st.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

