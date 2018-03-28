/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.6
 * Generated at: 2018-03-28 13:41:27 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import data.Record;
import model.TrawlExpert;
import search.BST;
import search.BasicSearchResult;

public final class histogram_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("data.Record");
    _jspx_imports_classes.add("search.BST");
    _jspx_imports_classes.add("search.BasicSearchResult");
    _jspx_imports_classes.add("model.TrawlExpert");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("  <!-- Plotly.js -->\r\n");
      out.write("  <script src=\"https://cdn.plot.ly/plotly-latest.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("  \r\n");
      out.write("  <div id=\"histogram\"><!-- Plotly chart will be drawn inside this DIV --></div>\r\n");
      out.write("  <script src=\"https://cdn.plot.ly/plotly-latest.min.js\"></script>\r\n");
      out.write("\t<script>\r\n");
      out.write("\r\n");
      out.write("\t");


		TrawlExpert te = (TrawlExpert)request.getServletContext().getAttribute("trawl");
		BasicSearchResult result = te.rangeSearch(2, 1960, 2016);

		BST<Integer, Integer> histogram = result.histogram();
		Iterable<Integer> results = histogram.keys();
		out.print("var y=[];");
		out.print("var x=[];");
		for (Integer year: results){
			out.print("\ty.push("+ histogram.get(year) +");\n");
			out.print("\tx.push('"+ year +"');\n");
		}
		
	
      out.write("\r\n");
      out.write("\r\n");
      out.write("\tvar data = [\r\n");
      out.write("  \t{ \tx: x,\r\n");
      out.write("    \ty: y,\r\n");
      out.write("    \ttype: 'bar',\r\n");
      out.write("     \tmarker: {\r\n");
      out.write("    \tcolor: 'blue',\r\n");
      out.write("    \t},\r\n");
      out.write("  \t}\r\n");
      out.write("\t];\r\n");
      out.write("\r\n");
      out.write("\tvar layout = {\r\n");
      out.write("\t\ttitle: 'Individual count vs Year',\r\n");
      out.write("    \txaxis:{title: 'Year',\r\n");
      out.write("    \t\ttitlefont: {\r\n");
      out.write("      \t\t\tfamily: 'Courier New, monospace',\r\n");
      out.write("      \t\t\tsize: 18,\r\n");
      out.write("      \t\t\tcolor: '#7f7f7f'\r\n");
      out.write("    \t\t\t}\r\n");
      out.write("    \t},\r\n");
      out.write("    \tyaxis:{title: 'Individual count',\r\n");
      out.write("    \t\ttitlefont: {\r\n");
      out.write("      \t\t\tfamily: 'Courier New, monospace',\r\n");
      out.write("      \t\t\tsize: 18,\r\n");
      out.write("      \t\t\tcolor: '#7f7f7f'\r\n");
      out.write("    \t\t\t}\r\n");
      out.write("    \t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tPlotly.newPlot('histogram', data,layout);  \r\n");
      out.write("\t</script>\r\n");
      out.write("</body>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
