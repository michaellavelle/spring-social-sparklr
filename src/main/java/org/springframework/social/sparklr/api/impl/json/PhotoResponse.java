package org.springframework.social.sparklr.api.impl.json;

import java.util.List;

import org.springframework.social.sparklr.api.Photo;

public class PhotoResponse {

	private List<Photo> photos;

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
}
