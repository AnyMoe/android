package sql;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class dialist
 */
@WebServlet("/dialist")
public class dialist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private String sqlQuery;
	private JSONObject js;
    public dialist() {
        super();
        sqlQuery = " select dia from dialist where id =";  
        js=new JSONObject();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String account = request.getParameter("account"); // �� request �л�ȡ��Ϊ account �Ĳ�����ֵ  
	    System.out.println("account:" + account ); 
		
		int row;
		
        /* ��ѯdia*/  
        try 
        {   
        	
        	Class.forName("com.mysql.jdbc.Driver");//���� ����
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        	Connection  connecter =(Connection)DriverManager.getConnection("jdbc:mysql://120.24.90.152:3306/appuser","sqluser","1234567aBc*"); 
            Statement statement =  (Statement) connecter.createStatement(); // Statement�������Ϊ���ݿ����ʵ���������ݿ�����в�����ͨ������ʵ��  
            
            ResultSet result;
           
           
            // ��ѯ���������һ��ResultSet���ϣ�û�в鵽���ʱResultSet�ĳ���Ϊ0  
            result = statement.executeQuery(sqlQuery+"'"+account+"';");   
            row=result.getRow();
            
            if(result.next())
            { // ����
            	
            	js.put("dia",result.getString(1));//dia
            }
            else
            {
            	js.put("dia","null");//dia
            }

        } 
        catch (Exception e)
        {
			// TODO Auto-generated catch block
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
