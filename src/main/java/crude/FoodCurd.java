package crude;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@WebServlet("/Food")
public class FoodCurd extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private static Connection conn = null;
		private static PreparedStatement stmt = null;
	       
	    public FoodCurd() {
	        super();
	        
	    }
	    
	    
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			
			

		
			ArrayList<foodModel> data = new ArrayList<foodModel>();
			
			try {
				conn = Conn.dbconnector();
				Statement stmt = conn.createStatement();
				String sql = "SELECT * FROM food";
				ResultSet result = stmt.executeQuery(sql);		
				while(result.next()) {
					foodModel pojo = new foodModel();
//					pojo.setCust_number(result.getString(2));
//					pojo.setName_customer(result.getString(3));
//					pojo.setDoc_id(result.getLong(6));
//					pojo.setDue_in_date(result.getString(9));
//					pojo.setTotal_open_amount(result.getDouble(14));
//					pojo.setInvoice_id(result.getLong(17));

					pojo.setSl_no(result.getString(1));
					pojo.setFood_item(result.getString(2));
					pojo.setPrice(result.getInt(3));
					pojo.setserving(result.getInt(4));
					
					
					data.add(pojo);
				}
				
				Gson gson = new Gson();
				String resData = gson.toJson(data);
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				out.print(resData);
				out.flush();
				result.close();
				stmt.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
}
