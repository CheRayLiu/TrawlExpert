package com.example.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
//import com.example.model.TrawlExpert; // notice this
import com.example.model.*; // notice this


public class Director extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
    String c = request.getParameter("phylum");

    TrawlExpert t = new TrawlExpert();
    List result = t.getBrands(c);

    request.setAttribute("styles", result);
    RequestDispatcher view = request.getRequestDispatcher("result.jsp");
    view.forward(request, response);
    }
}