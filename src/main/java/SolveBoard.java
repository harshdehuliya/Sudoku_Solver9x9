import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/solve")
public class SolveBoard extends HttpServlet{
	boolean isUnique(int grid[][],int target,int r , int c)
    {
        for(int i=0;i<9;i++)
        {
            if(i==c)
            {
                continue;
            }
            if(target==grid[r][i])
            {
                return false;
            }
        }
        
        for(int i=0;i<9;i++)
        {
            if(i==r)
            {
                continue;
            }
            if(target==grid[i][c])
            {
                return false;
            }
        }
        int i=0,j=0,endrow = 0,endcol=0;
        
        if(r>=0 && r<=2)
        {
            i=0;
            endrow=2;
            if(c>=0 && c<=2)
            {
                j=0;
                endcol=2;
            }
            else if(c>=3 && c<=5)
            {
                j=3;
                endcol=5;
            }
            else if(c>=6 && c<=8)
            {
                j=6;
                endcol=8;
            }
        }
        else if(r>=3 && r<=5)
        {
            i=3;
            endrow=5;
            if(c>=0 && c<=2)
            {
                j=0;
                endcol=2;
            }
            else if(c>=3 && c<=5)
            {
                j=3;
                endcol=5;
            }
            else if(c>=6 && c<=8)
            {
                j=6;
                endcol=8;
            }
        }
        else if(r>=6 && r<=8)
        {
            i=6;
            endrow=8;
            if(c>=0 && c<=2)
            {
                j=0;
                endcol=2;
            }
            else if(c>=3 && c<=5)
            {
                j=3;
                endcol=5;
            }
            else if(c>=6 && c<=8)
            {
                j=6;
                endcol=8;
            }
        }
        
        for(int ii=i;ii<=endrow;ii++)
        {
            for(int jj=j ; jj<=endcol ;jj++)
            {
                if(ii==r && jj==c)
                {
                    continue;
                }
                if(grid[ii][jj]==grid[r][c])
                {
                    return false;
                }
            }
        }
        return true;
    }
    boolean isSolved(int grid[][],int r,int c)
    {
        if(r==8 && c==9)
        {
            return true;
        }
        if(c==9)
        {
            r++;
            c=0;
            return isSolved(grid,r,c);
        }
        if(grid[r][c]!=0)
        {
            return isSolved(grid,r,c+1);
        }
        for(int i=1;i<=9;i++)
        {
            grid[r][c]=i;
            
            if(isUnique(grid,i,r,c))
            {
                if(isSolved(grid,r,c+1))
                {
                    return true;
                }
            }
            grid[r][c]=0;
        }
        return false;
    }
    
    boolean SolveSudoku(int grid[][])  
    { 
        // Your code here
        if(isSolved(grid,0,0))
        {
            return true;
        }
        return false;
        
    }
    boolean isValid(int[][] board)
    {
    	for(int i=0;i<9;i++)
    	{
    		for(int j=0;j<9;j++)
    		{
    			if(board[i][j]>9 || board[i][j]<0)
    			{
    				return false;
    			}
    		}
    	}
    	return true;
    }
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		HttpSession session= req.getSession();
		
		int[][] board=(int[][]) session.getAttribute("sudoku");
		session.removeAttribute("sudoku");
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				if(board[i][j]!=0)
				{
					if(!isUnique(board,board[i][j],i,j))
					{
						PrintWriter out=res.getWriter();
						out.println("OOPS...! Solution for given input is Not possible go back and try some other combination");
						return ;
					}
				}	
			}
		}
		if(isValid(board))
		{
			if(SolveSudoku(board) || isSolved(board, 0, 0))
			{
				session.setAttribute("sudoku", board);
				res.sendRedirect(req.getContextPath() + "/results.jsp");
			}
			else
			{
				PrintWriter out=res.getWriter();
				out.println("OOPS...! Solution for given input is Not possible go back and try some other combination");
			}
		}
		else
		{
			PrintWriter out=res.getWriter();
			out.println("OOPS...! Solution for given input is Not possible go back and try some other combination");
		}
	}
}
