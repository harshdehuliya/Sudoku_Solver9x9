import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/makeBoard")
public class SolverServlet extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, 
	ServletException
	{
		int[][] sudoku=new int[9][9];
		
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				sudoku[i][j]=Integer.parseInt(req.getParameter("e"+i+j));
			}
		}
		HttpSession session= req.getSession();
		session.setAttribute("sudoku", sudoku);
		res.sendRedirect("solve");
	}
}
