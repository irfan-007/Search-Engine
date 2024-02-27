<%@page import ="java.util.ArrayList"%>
<%@page import ="com.Accio.SearchResult"%>

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
    <th>Title</th>
    <th>Link</th>
  </tr>
  <%
    ArrayList<SearchResult> list=(ArrayList<SearchResult>)(request.getAttribute("resultList"));
    for(SearchResult sr:list){
  %>

  <tr>
      <td> <% out.println(sr.getTitle()); %> </td>
      <td> <a href="<% out.println(sr.getLink()); %>"><% out.println(sr.getLink()); %></a> </td>
   </tr>

   <% } %>

</table>

</body>
</html>
