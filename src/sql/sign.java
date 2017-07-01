package sql;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class sign
 */
@WebServlet("/sign")
public class sign extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
     
    public sign() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String account = request.getParameter("account"); // 从 request 中获取名为 account 的参数的值  
	     String value = request.getParameter("value"); // 从 request 中获取名为 password 的参数的值  
	     
	     JSONObject js=new JSONObject();
	       
	          
	          
	        
	      try {  
	    	  	Class.forName("com.mysql.jdbc.Driver");//加载 驱动
	    	  	//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	        	Connection  connecter =(Connection)DriverManager.getConnection("jdbc:mysql://120.24.90.152:3306/appuser","sqluser","1234567aBc*");
	             
	            
	            Statement statement =  (Statement) connecter.createStatement(); // Statement可以理解为数据库操作实例，对数据库的所有操作都通过它来实现  
	            
	            int result;
	            String sqlQuery = "update list set " +"sign='"+value + "' where id='" + account + "'";  
	              
	            
	            result = statement.executeUpdate(sqlQuery); // 先查询账号  
	        
	            if(result>0)
	            { 
	            	js.put("return","good");
	            }
	            else
          	{
          		System.out.println("bad");
          		
          		js.put("return","bad");
          	}
	             
	              
	        } 
	        catch (Exception e) 
	        {
	        	js.put("return","bad");
				e.printStackTrace();
			}  
		response.setContentType("text/html;charset=utf-8"); // 设置响应报文的编码格式  
	    PrintWriter pw = response.getWriter(); // 获取 response 的输出流  
	    // 通过输出流把业务逻辑的结果输出  
	    pw.println(js);
	    pw.flush();  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
