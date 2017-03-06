package com.respodo.commerce.dto;

import java.io.Serializable;

/**
 * A Image.
 */
public class ImageDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5804850677280562455L;

	private Long id;

    private String image1;

    private String image2;

    private String image3;

    private String image4;

    private String otherImages;
    
    private String thumbnail;
    
    public ImageDTO(){
    	
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

	public String getOtherImages() {
		return otherImages;
	}

	public void setOtherImages(String otherImages) {
		this.otherImages = otherImages;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return "ImageDTO [id=" + id + ", image1=" + image1 + ", image2="
				+ image2 + ", image3=" + image3 + ", image4=" + image4
				+ ", otherImages=" + otherImages + ", thumbnail=" + thumbnail
				+ "]";
	}


}
