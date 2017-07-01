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

import ali.testkkk;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class zhuce
 */
@WebServlet("/zhuce")
public class zhuce extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String sql="insert into user (id,name,psw,num) values(";
    private static final String sql2="insert into list (id,name) values(";
    private static final String sql3="insert into dialist (id,dia) value (";
	private ServletResponse response;
	private Connection  connecter;
	private JSONObject js;
	private Statement statement;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public zhuce() {
        super();
        connecter=null;
        js=new JSONObject();
        statement=null;
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String account = request.getParameter("account"); 
		String pwd = request.getParameter("password");
		String name = request.getParameter("name");
		System.out.println(account+" "+pwd+" "+name);
		
		testkkk.sample();
		int[] res;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			connecter =(Connection)DriverManager.getConnection("jdbc:mysql://120.24.90.152:3306/appuser","sqluser","1234567aBc*");
			//PreparedStatement pstarement= connecter.prepareStatement(sql);
			connecter.setAutoCommit(false);//这句话贼重要,不加特么的他会自动执行
			statement =  (Statement) connecter.createStatement(); // Statement可以理解为数据库操作实例，对数据库的所有操作都通过它来实现  
			 
			String tempforsql,tempforsql2,tempforsql3;
			tempforsql=sql+"'"+account+"'"+","+"'"+name+"','"+pwd+"','0')";
			tempforsql2=sql2+"'"+account+"','"+name+"')";
			tempforsql3=sql3+"'"+account+"','')";
			statement.addBatch(tempforsql);
			statement.addBatch(tempforsql2);
			statement.addBatch(tempforsql3);
			//res=statement.executeUpdate(sql);
			//res2=statement.executeUpdate(sql2);
			//connecter.commit();
			
			
			res=statement.executeBatch();
			connecter.commit();
			
			System.out.println(res[0]+" "+res[1]);
            if(res[0]>0&&res[1]>0&&res[1]>0)
            {
            	//成功
            	System.out.println("good");
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
