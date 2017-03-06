package com.respodo.commerce.mapper;

import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.ImageDTO;
import com.respodo.commerce.repository.domain.Image;

@Component
public class ImageMapper {

	public ImageDTO toImageDTO(Image image) {
		if(image==null){
			return null;
		}
		ImageDTO imageDTO=new ImageDTO();
		
		imageDTO.setImage1(image.getImage1());
		imageDTO.setImage2(image.getImage2());
		imageDTO.setImage3(image.getImage3());
		imageDTO.setImage4(image.getImage4());
		imageDTO.setOtherImages(image.getOtherImages());
		imageDTO.setThumbnail(image.getThumbnail());
		return imageDTO;
	}

	public Image toImageDomain(ImageDTO image) {
		if(image==null){
			return null;
		}
		Image domainImage=new Image();
		
		domainImage.setImage1(image.getImage1());
		domainImage.setImage2(image.getImage2());
		domainImage.setImage3(image.getImage3());
		domainImage.setImage4(image.getImage4());
		domainImage.setOtherImages(image.getOtherImages());
		domainImage.setThumbnail(image.getThumbnail());
		return domainImage;
	}

}
