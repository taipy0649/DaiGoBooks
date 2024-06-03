package com.example.demo.entity.api;

import java.util.List;

import lombok.Data;

@Data
public class VolumeInfo {

	private String title;
	private List<String> authors;
	private String publishedDate;
	private List<IndustryIdentifiers> industryIdentifiers;
	private ImageLinks imageLinks;
	
	public String getAuthorsAsString() {
        if (authors == null || authors.size() == 0) {
            return "";
        }
        StringBuilder authorsString = new StringBuilder();
        for (String author : authors) {
            if (authorsString.length() > 0) {
                authorsString.append(", ");
            }
            authorsString.append(author);
        }
        return authorsString.toString();
    }
	
	public String getImageLinsksThumbnail() {
		if (getImageLinks() == null) {
			return null;
		}
		
		if (imageLinks.getThumbnail() == null) {
			return imageLinks.getSmallThumbnail();
		} else {
			return imageLinks.getThumbnail();
		}
	}
	
    public String getIsbn10() {
        if (industryIdentifiers != null) {
            for (IndustryIdentifiers identifier : industryIdentifiers) {
                if ("ISBN_10".equals(identifier.getType())) {
                    return identifier.getIdentifier();
                }
            }
        }
        return null;
    }
    
    
    public String getIsbn13() {
        if (industryIdentifiers != null) {
            for (IndustryIdentifiers identifier : industryIdentifiers) {
                if ("ISBN_13".equals(identifier.getType())) {
                    return identifier.getIdentifier();
                }
            }
        }
        return null;
    }
}
