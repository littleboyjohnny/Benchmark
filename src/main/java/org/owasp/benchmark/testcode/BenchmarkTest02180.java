/**
* OWASP Benchmark Project v1.2beta
*
* This file is part of the Open Web Application Security Project (OWASP)
* Benchmark Project. For details, please see
* <a href="https://www.owasp.org/index.php/Benchmark">https://www.owasp.org/index.php/Benchmark</a>.
*
* The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
* of the GNU General Public License as published by the Free Software Foundation, version 2.
*
* The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
* even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* @author Nick Sanidas <a href="https://www.aspectsecurity.com">Aspect Security</a>
* @created 2015
*/

package org.owasp.benchmark.testcode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BenchmarkTest02180")
public class BenchmarkTest02180 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		String param = request.getParameter("vector");
		if (param == null) param = "";

		String bar = doSomething(param);
		
		try {
			String sql = "SELECT TOP 1 userid from USERS where USERNAME='foo' and PASSWORD='"+ bar + "'";
	
			Long results = org.owasp.benchmark.helpers.DatabaseHelper.JDBCtemplate.queryForLong(sql);
			java.io.PrintWriter out = response.getWriter();
			out.write("Your results are: ");
	//		System.out.println("your results are");
			out.write(results.toString());
	//		System.out.println(results);
		} catch (org.springframework.dao.DataAccessException e) {
			if (org.owasp.benchmark.helpers.DatabaseHelper.hideSQLErrors) {
        		response.getWriter().println("Error processing request.");
        		return;
        	}
			else throw new ServletException(e);
		}
	}  // end doPost
	
	private static String doSomething(String param) throws ServletException, IOException {

		String bar = "safe!";
		java.util.HashMap<String,Object> map37558 = new java.util.HashMap<String,Object>();
		map37558.put("keyA-37558", "a_Value"); // put some stuff in the collection
		map37558.put("keyB-37558", param); // put it in a collection
		map37558.put("keyC", "another_Value"); // put some stuff in the collection
		bar = (String)map37558.get("keyB-37558"); // get it back out
		bar = (String)map37558.get("keyA-37558"); // get safe value back out
	
		return bar;	
	}
}