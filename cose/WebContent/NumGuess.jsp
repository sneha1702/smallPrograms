<%@page import="java.util.Random"%>
<%
	Integer randNum = (Integer)session.getAttribute("randNum");
	if(randNum == null)
	{
		Random rnd = new Random();
		randNum = 1 + rnd.nextInt(99);
		session.setAttribute("randNum", randNum);
	}
	Integer count = (Integer) session.getAttribute("attemptCount");
	if (count == null) {
		count = 0;
	}
	String ipnum = request.getParameter("inputNum");
	if (ipnum == null) {
		ipnum = "";
	}
%>


<html>
<head>
<title>Number Magic</title>
</head>
<body>
	Computer has choosen a number between 1 and l00. Guess the number:
	<br>
	<form action="NumGuess.jsp">

		Enter num: <input type="text" name="inputNum" /> <input type="submit"
			value="Check My Guess!"> <br> <b> <%
 	if (ipnum.length() > 0) {
 		count = count + 1;
 		session.setAttribute("attemptCount", count);
 		int num = Integer.parseInt(ipnum);
 		
 		if (num == randNum) {
 			out.println("Your guess is right!");
 			out.println(count + " attempts");
 			session.removeAttribute("attemptCount");
 			session.removeAttribute("randNum");
 		} else if (num < randNum) {
 			out.println("The guessed number is smaller");
 		} else {
 			out.println("The guessed number is greater");
 		}

 	}
 %>
		</b>
	</form>

</body>
</html>