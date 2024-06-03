package com.example.demo.externalApi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.demo.entity.api.BooksApiResponse;
import com.example.demo.entity.api.ImageLinks;
import com.example.demo.entity.api.IndustryIdentifiers;
import com.example.demo.entity.api.Items;
import com.example.demo.entity.api.VolumeInfo;


public class BooksApiResponseTest {
	@Test
    public void testGetFirstItemTitle_whenItemsListIsEmpty() {
        BooksApiResponse response = new BooksApiResponse("books#responses", 0, Collections.emptyList());

        assertNull(response.getFirstItemTitle());
    }

    @Test
    public void testGetFirstItemTitle_whenItemsListHasItems() {
        List<Items> items = new ArrayList<>();
        Items item = new Items();
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setTitle("Some Book Title");
        item.setVolumeInfo(volumeInfo);
        items.add(item);

        BooksApiResponse response = new BooksApiResponse("books#responses", 1, items);

        assertEquals("Some Book Title", response.getFirstItemTitle());
    }
    
    @Test
    public void testGetFirstItemAuthors_whenItemsListIsEmpty() {
        BooksApiResponse response = new BooksApiResponse("books#responses", 0, Collections.emptyList());

        assertNull(response.getFirstItemAuthors());
    }

    @Test
    public void testGetFirstItemAuthors_whenItemsListHasItems() {
        List<Items> items = new ArrayList<>();
        Items item = new Items();
        VolumeInfo volumeInfo = new VolumeInfo();
        List<String> authors = new ArrayList<>();
        authors.add("Author Name");
        volumeInfo.setAuthors(authors);
        item.setVolumeInfo(volumeInfo);
        items.add(item);

        BooksApiResponse response = new BooksApiResponse("books#responses", 1, items);

        assertEquals("Author Name", response.getFirstItemAuthors());
    }

    @Test
    public void testGetFirstItemImageUrl_whenItemsListIsEmpty() {
        BooksApiResponse response = new BooksApiResponse("books#responses", 0, Collections.emptyList());

        assertNull(response.getFirstItemImageUrl());
    }

    @Test
    public void testGetFirstItemImageUrl_whenItemsListHasItemsAndImageLinksIsNull() {
        List<Items> items = new ArrayList<>();
        Items item = new Items();
        VolumeInfo volumeInfo = new VolumeInfo();
        item.setVolumeInfo(volumeInfo);
        items.add(item);

        BooksApiResponse response = new BooksApiResponse("books#responses", 1, items);

        assertNull(response.getFirstItemImageUrl());
    }
    
    @Test
    public void getFirstItemImageUrl_WithEmptyItems_ShouldReturnNull() {
        
        BooksApiResponse response = new BooksApiResponse("books#volumeList", 0, new ArrayList<>());
        
        String imageUrl = response.getFirstItemImageUrl();

        assertNull(imageUrl, "Image URL should be null when items are empty");
    }

    @Test
    public void testGetFirstItemImageUrl_whenItemsListHasItemsAndImageLinksHasThumbnail() {
        List<Items> items = new ArrayList<>();
        Items item = new Items();
        VolumeInfo volumeInfo = new VolumeInfo();
        ImageLinks imageLinks = new ImageLinks();
        imageLinks.setThumbnail("https://image.url");
        volumeInfo.setImageLinks(imageLinks);
        item.setVolumeInfo(volumeInfo);
        items.add(item);

        BooksApiResponse response = new BooksApiResponse("books#responses", 1, items);

        assertEquals("https://image.url", response.getFirstItemImageUrl());
    }
    
    @Test
    public void testGetFirstItemIsbn10_whenItemsListIsEmpty() {
        BooksApiResponse response = new BooksApiResponse("books#responses", 0, Collections.emptyList());

        assertNull(response.getFirstItemIsbn10());
    }

    @Test
    public void testGetFirstItemIsbn10_whenItemsListHasItems() {
        List<Items> items = new ArrayList<>();
        Items item = new Items();
        VolumeInfo volumeInfo = new VolumeInfo();
        List<IndustryIdentifiers> identifiers = new ArrayList<>();
        
        IndustryIdentifiers Identifier = new IndustryIdentifiers();
        Identifier.setType("ISBN_10");
        Identifier.setIdentifier("1234567890");
        identifiers.add(Identifier);
        volumeInfo.setIndustryIdentifiers(identifiers);
        
        item.setVolumeInfo(volumeInfo);
        items.add(item);

        BooksApiResponse response = new BooksApiResponse("books#responses", 1, items);

        assertEquals("1234567890", response.getFirstItemIsbn10());
    }
    
    @Test
    public void testGetFirstItemIsbn13_whenItemsListHasItems() {
    	List<Items> items = new ArrayList<>();
        Items item = new Items();
        VolumeInfo volumeInfo = new VolumeInfo();
        List<IndustryIdentifiers> identifiers = new ArrayList<>();
        
        IndustryIdentifiers Identifier = new IndustryIdentifiers();
        Identifier.setType("ISBN_13");
        Identifier.setIdentifier("1234567890123");
        identifiers.add(Identifier);
        volumeInfo.setIndustryIdentifiers(identifiers);
        
        item.setVolumeInfo(volumeInfo);
        items.add(item);

        BooksApiResponse response = new BooksApiResponse("books#responses", 1, items);

        assertEquals("1234567890123", response.getFirstItemIsbn13());
    }
}
