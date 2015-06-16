package edu.austincc.domain;

import java.util.Date;

public class Document {

	private int docId;
	private String fileName;
	private String format;
	private String fileDesc;
	private String createdBy;
	private Date   createDate;
	private int    parentTableId;
	private String parentTableName;
	

	
	public Document(int docId, String fileName, String format, String fileDesc,
			String createdBy, Date createDate) {
		super();
		this.docId = docId;
		this.fileName = fileName;
		this.format = format;
		this.fileDesc = fileDesc;
		this.createdBy = createdBy;
		this.createDate = createDate;
	}
	
	public Document(int docId, String fileName, String format, String fileDesc,
			String createdBy, Date createDate, int parentTableId,
			String parentTableName) {
		super();
		this.docId = docId;
		this.fileName = fileName;
		this.format = format;
		this.fileDesc = fileDesc;
		this.createdBy = createdBy;
		this.createDate = createDate;
		this.parentTableId = parentTableId;
		this.parentTableName = parentTableName;
	}
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getFileDesc() {
		return fileDesc;
	}
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getParentTableId() {
		return parentTableId;
	}
	public void setParentTableId(int parentTableId) {
		this.parentTableId = parentTableId;
	}
	public String getParentTableName() {
		return parentTableName;
	}
	public void setParentTableName(String parentTableName) {
		this.parentTableName = parentTableName;
	}
	
	@Override
	public String toString() {
		return "Document [docId=" + docId + ", fileName=" + fileName
				+ ", format=" + format + ", fileDesc=" + fileDesc
				+ ", createdBy=" + createdBy + ", createDate=" + createDate
				+ ", parentTableId=" + parentTableId + ", parentTableName="
				+ parentTableName + "]";
	}
	
	
	
}
