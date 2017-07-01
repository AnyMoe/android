package sql;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import net.sf.json.JSONObject;



/**
 * Servlet implementation class testsql
 */
@WebServlet("/testsql")
public class testsql extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testsql() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		  String account = request.getParameter("account"); // 从 request 中获取名为 account 的参数的值  
	      String password = request.getParameter("password"); // 从 request 中获取名为 password 的参数的值  
	      System.out.println("account:" + account + "\npassword:" + password); // 打印出来看一看  
	      JSONObject js=new JSONObject();
	       
	          
	          
	        
	      try {  
	    	  	Class.forName("com.mysql.jdbc.Driver");//加载 驱动
	    	  	//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	        	Connection  connecter =(Connection)DriverManager.getConnection("jdbc:mysql://120.24.90.152:3306/appuser","sqluser","1234567aBc*");
	             
	            
	            Statement statement =  (Statement) connecter.createStatement(); // Statement可以理解为数据库操作实例，对数据库的所有操作都通过它来实现  
	            
	            ResultSet result;
	            String sqlQuery = "select * from " +"user" + " where id='" + account + "'";  
	              
	            // 查询类操作返回一个ResultSet集合，没有查到结果时ResultSet的长度为0  
	            result = statement.executeQuery(sqlQuery); // 先查询账号  
	        
	            if(result.next())
	            { // 已存在
	            	
	            	String temp=result.getString(3);
	            	int temp_num=result.getInt(4);
	            	System.out.println(temp);
	            	if(temp.equals(password))
	            	{
	            		System.out.println("good");
	            		
	            		js.put("return","good");
	            		js.put("num", temp_num);
	            	}
	            	else
	            	{
	            		System.out.println("bad");
	            		
	            		js.put("return","bad");
	            	}
	            	
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
