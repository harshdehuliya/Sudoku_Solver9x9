<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>results<h1>
	<br>
	<%
		int[][] board=(int[][])session.getAttribute("sudoku");
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				out.print(board[i][j]+" ");
			}
			out.println("<br>");
		}
		
	%>
</body>
</html>