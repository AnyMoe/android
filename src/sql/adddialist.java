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
 * Servlet implementation class setdialist
 */
@WebServlet("/setdialist")
public class adddialist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String sql="select dia from dialist where id =";
	private static final String sql2="update dialist set dia = ";
	private static final String sql2_2="where id = ";
	private ServletResponse response;
	private Connection  connecter;
	private JSONObject js;
	private Statement statement;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adddialist() {
        super();
        connecter=null;
        js=new JSONObject();
        statement=null;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account"); 
		String type = request.getParameter("type");
		String value = request.getParameter("value");
		String thedia;
		
		int[] res;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			connecter =(Connection)DriverManager.getConnection("jdbc:mysql://120.24.90.152:3306/appuser","sqluser","1234567aBc*");
			connecter.setAutoCommit(false);//这句话贼重要,不加特么的他会自动执行
			statement =  (Statement) connecter.createStatement(); // Statement可以理解为数据库操作实例，对数据库的所有操作都通过它来实现  
			
			String tempforsql;
			
			tempforsql=sql+"'"+account+"'";
			ResultSet result;
			result = statement.executeQuery(tempforsql);   
            
			if(result.next())
			{
				thedia=result.getString(1);
			}
			else
			{
				thedia="";
			}
			if(type.equals("del"))
			{
				tempforsql=sql2+"'"+thedia.replace(value+"~", "")+"'"+sql2_2+"'"+account+"'";
	            statement.addBatch(tempforsql);
				
				res=statement.executeBatch();
				connecter.commit();
				if(res[0]>0)
	            {
	            	
	            	js.put("return","good");
	            }
	            else
	            {
	            	js.put("return","bad");
	            }
			}
			else if(type.equals("add"))
			{
				tempforsql=sql2+"'"+thedia+value+"~'"+sql2_2+"'"+account+"'";
	            statement.addBatch(tempforsql);
				
				res=statement.executeBatch();
				connecter.commit();
				if(res[0]>0)
	            {
	            	
	            	js.put("return","good");
	            }
	            else
	            {
	            	js.put("return","bad");
	            }
			}
			else js.put("return","bad");
			
        }
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("exc");
			js.put("return","bad");
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
