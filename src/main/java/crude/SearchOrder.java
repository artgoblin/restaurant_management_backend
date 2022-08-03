package crude;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class SearchSalesOrder
 */
@WebServlet("/SearchSalesOrder")
public class SearchOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		int NO_OF_ROWS_TO_GET = 12;
		
		try {
			Connection conn = Conn.dbconnector();
			
			String searchKeyword = request.getParameter("searchKeyword");
			String page = request.getParameter("page");
//			int key = Integer.parseInt(searchKeyword);
			
			Statement st = conn.createStatement();
//			String sql_statement = "SELECT * FROM invoice_details ORDER BY doc_id LIMIT " + page + ", 10";
			String sql_statement = "SELECT * FROM food WHERE food_item LIKE '"+searchKeyword +"%' OR serving LIKE '"+searchKeyword+"%' OR price LIKE '"+searchKeyword+"%'"+" LIMIT " + page +"," + NO_OF_ROWS_TO_GET;
			ResultSet rs = st.executeQuery(sql_statement);
//		
			
			ArrayList<foodModel> data = new ArrayList<>();
			while(rs.next()) {
				foodModel inv = new foodModel();
				inv.setSl_no(rs.getString("Sl_no"));
				inv.setFood_item(rs.getString("food_item"));
				inv.setPrice(rs.getInt("price"));	
				inv.setserving(rs.getInt("serving"));
				data.add(inv);
			}
			
			Gson gson = new GsonBuilder().serializeNulls().create();
			String invoices = gson.toJson(data);
			
//			System.out.println(invoices);
			out.print(invoices);
			response.setStatus(200);
			out.flush();
		}
	
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
