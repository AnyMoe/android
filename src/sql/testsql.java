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
		  String account = request.getParameter("account"); // �� request �л�ȡ��Ϊ account �Ĳ�����ֵ  
	      String password = request.getParameter("password"); // �� request �л�ȡ��Ϊ password �Ĳ�����ֵ  
	      System.out.println("account:" + account + "\npassword:" + password); // ��ӡ������һ��  
	      JSONObject js=new JSONObject();
	       
	          
	          
	        
	      try {  
	    	  	Class.forName("com.mysql.jdbc.Driver");//���� ����
	    	  	//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	        	Connection  connecter =(Connection)DriverManager.getConnection("jdbc:mysql://120.24.90.152:3306/appuser","sqluser","1234567aBc*");
	             
	            
	            Statement statement =  (Statement) connecter.createStatement(); // Statement�������Ϊ���ݿ����ʵ���������ݿ�����в�����ͨ������ʵ��  
	            
	            ResultSet result;
	            String sqlQuery = "select * from " +"user" + " where id='" + account + "'";  
	              
	            // ��ѯ���������һ��ResultSet���ϣ�û�в鵽���ʱResultSet�ĳ���Ϊ0  
	            result = statement.executeQuery(sqlQuery); // �Ȳ�ѯ�˺�  
	        
	            if(result.next())
	            { // �Ѵ���
	            	
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
	          
	        
	        
	        response.setContentType("text/html;charset=utf-8"); // ������Ӧ���ĵı����ʽ  
	        PrintWriter pw = response.getWriter(); // ��ȡ response �������  
	       // ͨ���������ҵ���߼��Ľ�����  
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
