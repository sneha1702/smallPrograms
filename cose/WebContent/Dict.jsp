<%@page import="java.util.*,java.io.*"%>
<html>
<head>
<title>Translator</title>
</head>
<body>
	<h1>Translate tool</h1>
	<%
		HashMap<String, String> dict = (HashMap<String, String>) application.getAttribute("Dict");
		String enWord = request.getParameter("translateWord");
		if (enWord == null) {
			enWord = "";
		}
		String jspPath = session.getServletContext().getRealPath("/");
		if (dict == null) {
			dict = new HashMap<String, String>();
			String txtFilePath = jspPath + "/dictionary_en_de.txt";
			BufferedReader rd = new BufferedReader(new FileReader(
					txtFilePath));
			while (true) {
				String line = rd.readLine();
				if (line == null) {
					break;
				} else {
					StringTokenizer st = new StringTokenizer(line, "=");
					String en = st.nextToken();
					String de = st.nextToken();
					dict.put(en, de);
				}
			}
			rd.close();
			application.setAttribute("Dict", dict);
		}
	%>
	<form action="Dict.jsp" method="GET">
		Enter text: <input type="text" name="translateWord" 
			value="<%=enWord%>" /> <input type="submit"
			value="Translate" />
		<%
			if (enWord.length() > 0) {
		%>
		<br>
		<b>
			<%
				String translation = dict.get(enWord);
					out.println(translation);
			%>
		</b>
		<%
			}
		%>
	</form>
</body>
</html>