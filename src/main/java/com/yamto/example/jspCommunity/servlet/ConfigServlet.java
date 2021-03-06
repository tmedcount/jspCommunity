package com.yamto.example.jspCommunity.servlet;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.yamto.example.jspCommunity.container.Container;
import com.yamto.example.jspCommunity.service.EmailService;
import com.yamto.example.util.Util;

@WebServlet(name = "loadAppConfig", urlPatterns = {"/loadConfig"}, loadOnStartup = 1)
public class ConfigServlet extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ServletContext context = getServletContext();
		InputStream inStream = context.getResourceAsStream("/META-INF/config.json");
		Map<String, Object> configMap = Util.getJsonMapfromFile(inStream);
		
		String gmailId = (String)configMap.get("gmailId");
		String gmailPw = (String)configMap.get("gmailPw");
				
		EmailService emailService = Container.emailService;
		
		emailService.init(gmailId, gmailPw, "jspCommunity", "jspCommunity");
	}
	
}
