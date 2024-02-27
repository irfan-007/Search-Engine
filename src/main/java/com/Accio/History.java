package com.Accio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/History")
public class History extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        Connection connection=DBconnection.getConnect();
        try {
            ResultSet rs=connection.createStatement().executeQuery("select * from history;");
            ArrayList<HistoryResult> list=new ArrayList<>();
            while (rs.next()){
                HistoryResult result=new HistoryResult();
                result.setKeyword(rs.getString("keyword"));
                result.setLink(rs.getString("link"));
                list.add(result);
            }
            request.setAttribute("results",list);
            request.getRequestDispatcher("history.jsp").forward(request,response);

        } catch (SQLException | ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
