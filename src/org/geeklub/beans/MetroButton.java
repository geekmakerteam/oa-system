package org.geeklub.beans;

public class MetroButton {
	/**菜单标题*/
	private String Title;	
	/**metro按钮的图片资源*/
	private  int imageID;	
	/**metro按钮的背景资源*/
	private int bgID;			

	public MetroButton(String Title, int imageID,int bgID) {
		this.Title = Title;
		this.imageID = imageID;
		this.bgID = bgID;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public int getBgID() {
		return bgID;
	}

	public void setBgID(int bgID) {
		this.bgID = bgID;
	}







}
