package edu.austincc.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import edu.austincc.db.Documentmanager;
import edu.austincc.domain.Document;


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig(maxFileSize = 16177215)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/DB")
	DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/SupportingDocumentServlet";
		HttpSession session = request.getSession();
		int parentTableId = (int) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		String parentTableName = "SE_USER";
		boolean success = false;

		InputStream inputStream = null; // input stream of the upload file
 	
        Part file = request.getPart("filename");
        if (file != null) {
            inputStream = file.getInputStream();	 
            
            String fileName = getFileName(file);
            Calendar cal = Calendar.getInstance(); 
            Date createDate = cal.getTime();
            Document document = new Document(0,fileName,file.getContentType(),null,userName,createDate,parentTableId,parentTableName);
            success = new Documentmanager(ds).addDocuemnt(document, inputStream);
        }
        response.sendRedirect(request.getContextPath() + url);        
     
	}

	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : part.getHeader("content-disposition").split(";")) {
		    if (content.trim().startsWith("filename")) {
		        return content.substring(
		                content.indexOf('=') + 1).trim().replace("\"", "");
		    }
		}
		return partHeader;
	}

}
