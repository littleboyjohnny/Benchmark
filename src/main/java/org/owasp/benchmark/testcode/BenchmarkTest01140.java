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
* @author Dave Wichers <a href="https://www.aspectsecurity.com">Aspect Security</a>
* @created 2015
*/

package org.owasp.benchmark.testcode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BenchmarkTest01140")
public class BenchmarkTest01140 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	
		String param = "";
		boolean flag = true;
		java.util.Enumeration<String> names = request.getHeaderNames();
		while (names.hasMoreElements() && flag) {
			String name = (String) names.nextElement();
			java.util.Enumeration<String> values = request.getHeaders(name);
			if (values != null) {
				while (values.hasMoreElements() && flag) {
					String value = (String) values.nextElement();
					if (value.equals("vector")) {
						param = name;
						flag = false;
					}
				}
			}
		}

		String bar = new Test().doSomething(param);
		
		try {
			long l = java.security.SecureRandom.getInstance("SHA1PRNG").nextLong();
			String rememberMeKey = Long.toString(l);
			
			String user = "SafeLogan";
			String fullClassName = this.getClass().getName();
			String testCaseNumber = fullClassName.substring(fullClassName.lastIndexOf('.')+1+"BenchmarkTest".length());
			user+= testCaseNumber;
			
			String cookieName = "rememberMe" + testCaseNumber;
			
			boolean foundUser = false;
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			for (int i = 0; cookies != null && ++i < cookies.length && !foundUser;) {
				javax.servlet.http.Cookie cookie = cookies[i];
				if (cookieName.equals(cookie.getName())) {
					if (cookie.getValue().equals(request.getSession().getAttribute(cookieName))) {
						foundUser = true;
					}
				}
			}
			
			if (foundUser) {
				response.getWriter().println("Welcome back: " + user + "<br/>");			
			} else {			
				javax.servlet.http.Cookie rememberMe = new javax.servlet.http.Cookie(cookieName, rememberMeKey);
				rememberMe.setSecure(true);
				request.getSession().setAttribute(cookieName, rememberMeKey);
				response.addCookie(rememberMe);
				response.getWriter().println(user + " has been remembered with cookie: " + rememberMe.getName() 
						+ " whose value is: " + rememberMe.getValue() + "<br/>");
			}

	    } catch (java.security.NoSuchAlgorithmException e) {
			System.out.println("Problem executing SecureRandom.nextLong() - TestCase");
			throw new ServletException(e);
	    }		
		response.getWriter().println("Weak Randomness Test java.security.SecureRandom.nextLong() executed");
	}  // end doPost

    private class Test {

        public String doSomething(String param) throws ServletException, IOException {

		// Chain a bunch of propagators in sequence
		String a92494 = param; //assign
		StringBuilder b92494 = new StringBuilder(a92494);  // stick in stringbuilder
		b92494.append(" SafeStuff"); // append some safe content
		b92494.replace(b92494.length()-"Chars".length(),b92494.length(),"Chars"); //replace some of the end content
		java.util.HashMap<String,Object> map92494 = new java.util.HashMap<String,Object>();
		map92494.put("key92494", b92494.toString()); // put in a collection
		String c92494 = (String)map92494.get("key92494"); // get it back out
		String d92494 = c92494.substring(0,c92494.length()-1); // extract most of it
		String e92494 = new String( new sun.misc.BASE64Decoder().decodeBuffer( 
		    new sun.misc.BASE64Encoder().encode( d92494.getBytes() ) )); // B64 encode and decode it
		String f92494 = e92494.split(" ")[0]; // split it on a space
		org.owasp.benchmark.helpers.ThingInterface thing = org.owasp.benchmark.helpers.ThingFactory.createThing();
		String g92494 = "barbarians_at_the_gate";  // This is static so this whole flow is 'safe'
		String bar = thing.doSomething(g92494); // reflection

            return bar;
        }
    } // end innerclass Test

} // end DataflowThruInnerClass