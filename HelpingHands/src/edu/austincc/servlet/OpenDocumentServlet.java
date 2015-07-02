package edu.austincc.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.catalina.connector.Response;

import edu.austincc.db.Documentmanager;
import edu.austincc.domain.Document;

/**
 * Servlet implementation class OpenDocumentServlet
 */
@WebServlet("/OpenDocumentServlet")
public class OpenDocumentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB

	@Resource(name = "jdbc/DB")
	DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OpenDocumentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Document document = null;
		int docId = Integer.parseInt(request.getParameter("docId"));
		document = new Documentmanager(ds).getDocument(docId);
		String format = document.getFormat();
		String fileName = document.getFileName();

		byte[] fileData = new Documentmanager(ds).getBlobAsByte(docId);

		response.reset();
		response.setContentType(format);
		response.setBufferSize(DEFAULT_BUFFER_SIZE);
		response.setHeader("Content-Length", String.valueOf(fileData.length));
		response.addHeader("Content-Disposition", "attachment; filename="
				+ fileName + "." + format);
		response.setContentLength((int) fileData.length);

		// Prepare streams.
		InputStream input = null;
		OutputStream output = null;
		input = new ByteArrayInputStream(fileData);
		output = new BufferedOutputStream(response.getOutputStream(),
				DEFAULT_BUFFER_SIZE);

		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int length;
		while ((length = input.read(buffer)) > 0) {
			output.write(fileData, 0, fileData.length);
		}

		// Gently close streams.
		close(output);
		close(input);

	}

	private static void close(Closeable resource) {
		if (resource != null) {
			try {

				resource.close();

			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
