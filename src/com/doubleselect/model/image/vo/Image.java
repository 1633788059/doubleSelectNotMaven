package com.doubleselect.model.image.vo;


public class Image {
	
	private int id;
	
	private String sno;
	
	private String imagename;
	
	public Image(){
		
	}
	
	public Image(int id, String sno, String imagename, String imagepath) {
		super();
		this.id = id;
		this.sno = sno;
		this.imagename = imagename;
		this.imagepath = imagepath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	private String imagepath;
	
}
