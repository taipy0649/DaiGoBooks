package com.example.demo.entity.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksApiResponse {

	private String kind;
	private Integer totalItems;
	private List<Items> items;

	public String getFirstItemTitle() {
		if (CheckItemIsNullOrEmpty()) {
			return null;
		}
		return items.get(0).getVolumeInfo().getTitle();
	}

	public String getFirstItemAuthors() {
		if (CheckItemIsNullOrEmpty()) {
			return null;
		}
		return items.get(0).getVolumeInfo().getAuthorsAsString();
	}

	public String getFirstItemImageUrl() {
		if (CheckItemIsNullOrEmpty()) {
			return null;
		}
		return items.get(0).getVolumeInfo().getImageLinsksThumbnail();
	}

	public String getFirstItemIsbn10() {
		if (CheckItemIsNullOrEmpty()) {
			return null;
		}
		return items.get(0).getVolumeInfo().getIsbn10();
	}

	public String getFirstItemIsbn13() {
		if (CheckItemIsNullOrEmpty()) {
			return null;
		}
		return items.get(0).getVolumeInfo().getIsbn13();
	}

	private boolean CheckItemIsNullOrEmpty() {
		if (items == null || items.isEmpty()) {
			return true;
		}
		return false;
	}

	
}
