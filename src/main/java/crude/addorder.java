package crude;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditSalesOrder
 */
@WebServlet("/EditSalesOrder")
public class addorder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addorder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String salesOrder = null;
		
		try {
			BufferedReader reader = request.getReader();
			salesOrder = reader.readLine();
			System.out.println(salesOrder);
			
			salesOrder = salesOrder.substring(1,  salesOrder.length() - 1);
			String final_values[] = salesOrder.split(",");
			
			for(int i = 0; i < final_values.length; ++i) {
				final_values[i] = final_values[i].split(":")[1];
				if(final_values[i].charAt(0) == '\"') {
					final_values[i] = final_values[i].substring(1, final_values[i].length() - 1);
				}
				System.out.println(final_values[i]);
			}
			
			String salesOrderNumber = final_values[0];
			String salesOrderCurr = final_values[1];
			String Cuspayterm = final_values[2];
			
			Connection conn = Conn.dbconnector();
			String sql_statement = "UPDATE food SET table_no = ?, notes = ? WHERE Sl_no = ?";
			
			PreparedStatement st = conn.prepareStatement(sql_statement);
			st.setString(3, salesOrderNumber);
			st.setString(1, salesOrderCurr);
			st.setString(2, Cuspayterm);
			
			System.out.println(st);
			
			st.executeUpdate();
			st.close();
		}
		catch(Exception e) {
			
		}
	}

}
