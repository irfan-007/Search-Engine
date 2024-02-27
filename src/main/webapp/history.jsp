<%@page import ="java.util.ArrayList"%>
<%@page import ="com.Accio.HistoryResult"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>

<div>
<h2>Search Engine</h2>
<form action="Search" method="get">
    <input type="text"/ name="keyword" placeholder="enter your keyword">
    <button  type="submit">Search</button>
</form>

<form action="History" method="get">
    <button style="background-color:grey;color:yellow" type="submit">History</button>
</form>
</div>

<table style="width:100%">
  <tr>
    <th>Keyword</th>
    <th>Link</th>
  </tr>
  <%
    ArrayList<HistoryResult> list=(ArrayList<HistoryResult>)(request.getAttribute("results"));
    for(HistoryResult hr:list){
  %>

  <tr>
      <td> <% out.println(hr.getKeyword()); %> </td>
      <td> <a href="<% out.println(hr.getLink()); %>"><% out.println(hr.getLink()); %></a> </td>
   </tr>

   <% } %>

</table>

</body>
</html>