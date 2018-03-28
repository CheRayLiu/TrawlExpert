package web;

import javax.servlet.*;
import javax.servlet.http.*;

import data.Record;

import java.io.*;
import java.util.*;
//import com.example.model.TrawlExpert; // notice this
import model.*; // notice this
import search.BasicSearchResult;


public class Director extends HttpServlet {
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
	    String req = getUrlDoPortion(request);
	    //if (req == "doBioLookup.do")
	    		//doBioLookup(request, response);
	    
	    TrawlExpert te = (TrawlExpert) request.getServletContext().getAttribute("trawl");
	    //very basic example of listing all species
	   // Iterable<Integer> result = te.listAllSpecies();
	    
	    BasicSearchResult result = te.rangeSearch(159512, 1960, 2016);

	    request.setAttribute("results", result);
	    RequestDispatcher view = request.getRequestDispatcher("result.jsp");
	    view.forward(request, response);
    }
	
	private void doBioLookup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get answers from form
		String phylum = request.getParameter("phylum");
		String bioClass = request.getParameter("class");
		String order = request.getParameter("order");
		String family = request.getParameter("family");
		String genus = request.getParameter("genus");
		String species = request.getParameter("species");

	    TrawlExpert te = (TrawlExpert)request.getServletContext().getAttribute("trawl");
	    //find all applicable things underneath the top most one, need API for this
	    
	    //send lists of things to build the dropdowns
	    request.setAttribute("phylum", result);
	    request.setAttribute("bioClass", result);
	    request.setAttribute("order", result);
	    request.setAttribute("family", result);
	    request.setAttribute("genus", result);
	    request.setAttribute("species", result);
	    //send back the index.jsp page
	    RequestDispatcher view = request.getRequestDispatcher("index.jsp");
	    view.forward(request, response);
	}
	
	private String getUrlDoPortion(HttpServletRequest request) {
		String url = (String) request.getRequestURL().toString();
		String[] s = url.split("/");
		return s[s.length - 1];
	}
}