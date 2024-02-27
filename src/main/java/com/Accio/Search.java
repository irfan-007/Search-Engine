package com.Accio;

import com.Accio.DBconnection;
import com.Accio.SearchResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // getting a keyword from user
        String keyword=req.getParameter("keyword");
        ArrayList<SearchResult> list;
        try {
            // setup database connuction
            Connection connection= DBconnection.getConnect();
            // store user query to history
            PreparedStatement ps=connection.prepareStatement("insert into history(keyword,link)values(?,?);");
            ps.setString(1,keyword);
            ps.setString(2,"http://localhost:8080/SearchEngine/Search?keyword="+keyword);
            ps.executeUpdate();
            // running ranking query and getting result
            ResultSet rs= connection.createStatement().executeQuery("select pageTitle,pageLink,(length(lower(pageText))-length(replace(lower(pageText),'"+keyword.toLowerCase()+"','')))/(length('"+keyword.toLowerCase()+"')) as occurance from pages order by occurance desc limit 30");
            // store results to an arraylist
            list=new ArrayList<SearchResult>();
            while(rs.next()){
                SearchResult searchResult=new SearchResult();
                searchResult.setLink(rs.getString("pageLink"));
                searchResult.setTitle(rs.getString("pageTitle"));
                list.add(searchResult);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // print results
        for(SearchResult r:list){
            System.out.println(r.getTitle()+" --"+r.getLink());
        }
        req.setAttribute("resultList",list);
        try {
            req.getRequestDispatcher("search.jsp").forward(req,res);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        System.out.println(keyword);
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        out.println("<h3>your keyword : "+keyword +"</h3>");
    }
}
