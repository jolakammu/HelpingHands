package edu.austincc.db;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import edu.austincc.domain.Address;
import edu.austincc.domain.Document;
import edu.austincc.domain.ElecctronicCommunication;
import edu.austincc.domain.VolunteerItems;

public class Documentmanager {

	DataSource ds;

	public Documentmanager(DataSource ds) {
		this.ds = ds;
	}

	public byte[] getBlobAsByte(int docId) {
		byte[] fileData = null;
		Blob data = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection connection;
			connection = ds.getConnection();
			ps = connection
					.prepareStatement("select  DATA from app.hh_doc where DOC_ID = ?");
			ps.setInt(1, docId);
			rs = ps.executeQuery();
			while (rs.next()) {
				data = rs.getBlob("DATA");
				fileData = data.getBytes(1, (int) data.length());
			}
			ps.close();
			rs.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fileData;
	}

	public Document getDocument(int docId) {

		Document document = null;
		;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection connection;
			connection = ds.getConnection();
			ps = connection
					.prepareStatement("select  DOC_ID, FILENAME_TXT,  FORMAT_CD,  FILE_DESC_TXT,  CREATED_BY_TXT, CREATE_DT  from app.hh_doc where DOC_ID = ?");
			ps.setInt(1, docId);
			rs = ps.executeQuery();
			while (rs.next()) {
				document = new Document(rs.getInt("DOC_ID"),
						rs.getString("FILENAME_TXT"),
						rs.getString("FORMAT_CD"),
						rs.getString("FILE_DESC_TXT"),
						rs.getString("CREATED_BY_TXT"), rs.getDate("CREATE_DT"));
			}
			ps.close();
			rs.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return document;
	}

	public ArrayList<Document> listDocuments(int parentTableId,
			String parentTableName) {

		ArrayList<Document> documentArray = new ArrayList<Document>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection connection;
			connection = ds.getConnection();
			ps = connection
					.prepareStatement("select  DOC_ID, FILENAME_TXT,  FORMAT_CD,  FILE_DESC_TXT,  CREATED_BY_TXT, CREATE_DT  from app.hh_doc where PARENT_TABLE_ID = ? and PARENT_TABLE_NAME = ?");
			ps.setInt(1, parentTableId);
			ps.setString(2, parentTableName);
			rs = ps.executeQuery();
			while (rs.next()) {
				documentArray.add(new Document(rs.getInt("DOC_ID"), rs
						.getString("FILENAME_TXT"), rs.getString("FORMAT_CD"),
						rs.getString("FILE_DESC_TXT"), rs
								.getString("CREATED_BY_TXT"), rs
								.getDate("CREATE_DT")));
			}
			ps.close();
			rs.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return documentArray;
	}

	public boolean addDocuemnt(Document document, InputStream inputStream) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		int docId = 0;

		try {
			Connection connection;
			connection = ds.getConnection();
			ps = connection
					.prepareStatement("select Coalesce(Max(DOC_ID),0) + 1  as DOC_ID from app.HH_DOC");
			rs = ps.executeQuery();
			while (rs.next()) {
				docId = rs.getInt("DOC_ID");
			}

			ps = connection
					.prepareStatement("insert into app.HH_DOC(DOC_ID, FILENAME_TXT,FORMAT_CD,FILE_DESC_TXT,CREATED_BY_TXT,CREATE_DT,PARENT_TABLE_ID,PARENT_TABLE_NAME,DATA) values (?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, docId);
			ps.setString(2, document.getFileName());
			ps.setString(3, document.getFormat());
			ps.setString(4, document.getFileDesc());
			ps.setString(5, document.getCreatedBy());
			ps.setDate(6, new java.sql.Date(document.getCreateDate().getTime()));
			ps.setInt(7, document.getParentTableId());
			ps.setString(8, document.getParentTableName());
			ps.setBinaryStream(9, inputStream);

			ps.executeUpdate();
			ps.close();
			rs.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
