import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class UserCheckServlet extends HttpServlet 
{
	private RequestDispatcher rd;
	private PrintWriter out;
	private int count;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		String user,pass;
		user=req.getParameter("user");
		pass=req.getParameter("pass");
		if(user==null | user.equals(""))  //error with || 
		{
			rd=req.getRequestDispatcher("/login.jsp");
			req.setAttribute("UserNull","Enter username!!");
			rd.forward(req,res);
		}
		else if(pass==null | pass.equals(""))
		{
			rd=req.getRequestDispatcher("/login.jsp");
			req.setAttribute("PassNull","Enter Password!!");
			rd.forward(req,res);
		}
		else
		{
			try
			{
				count=0;
				Database.Db.rs=Database.Db.stmt.executeQuery("select username,password from login");
				while(Database.Db.rs.next()==true)
				{
					if(user.equals(Database.Db.rs.getString("username"))) 
					{	
						count=1;
						if(pass.equals(Database.Db.rs.getString("password")))
						{
							ServletContext sc=getServletContext();
							Database.Db.rs=Database.Db.stmt.executeQuery("select name,designation from login where username=\""+user+"\"");
							Database.Db.rs.next();						
							String name=Database.Db.rs.getString("name");
							String designation=Database.Db.rs.getString("designation");
							sc.setAttribute("name",name);
							sc.setAttribute("designation",designation);
							rd=req.getRequestDispatcher("/admin.jsp");
							rd.forward(req,res);
						}
						else
						{
							rd=req.getRequestDispatcher("/login.jsp");
							req.setAttribute("PassIncorrect","Password Incorrect!!");
							rd.forward(req,res);
						}
					}
					else
					{	if(count==0)
						{
							if(Database.Db.rs.isLast()==true)
							{
								rd=req.getRequestDispatcher("/login.jsp");
								req.setAttribute("UserIncorrect","Username Incorrect!!");
								rd.forward(req,res);
							}
						}
					}
				}
				
			}
			catch(SQLException e)
			{
				out.print("Exception6");
			}
		}
	}
}