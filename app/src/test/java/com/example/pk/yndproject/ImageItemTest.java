package com.example.pk.yndproject;

import com.example.pk.yndproject.model.ImageItem;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by PK on 20.08.2017.
 */

public class ImageItemTest extends TestCase{

    private ImageItem imageItem;

    protected void setUp() throws Exception {
        super.setUp();
        imageItem = new ImageItem();
    }

    public void testTitle() {

        String expected = RandomStringGenerator.random();
        imageItem.setFormat(expected);
        String actual = imageItem.getFormat();
        Assert.assertEquals(expected, actual);

    }

    public void testWidth() {

        int expected = (int) (Math.random());
        imageItem.setWidth(expected);
        int actual = imageItem.getWidth();
        Assert.assertEquals(expected, actual);

    }

    public void testHeight() {

        int expected = (int) (Math.random());
        imageItem.setHeight(expected);
        int actual = imageItem.getHeight();
        Assert.assertEquals(expected, actual);

    }

    public void testFilename() {

        String expected = RandomStringGenerator.random();
        imageItem.setFilename(expected);
        String actual = imageItem.getFilename();
        Assert.assertEquals(expected, actual);

    }

    public void testId() {

        int expected = (int) (Math.random());
        imageItem.setId(expected);
        int actual = imageItem.getId();
        Assert.assertEquals(expected, actual);

    }

    public void testPostUrl() {

        String expected = RandomStringGenerator.random();
        imageItem.setPostUrl(expected);
        String actual = imageItem.getPostUrl();
        Assert.assertEquals(expected, actual);

    }

    public void testAuthor() {

        String expected = RandomStringGenerator.random();
        imageItem.setAuthor(expected);
        String actual = imageItem.getAuthor();
        Assert.assertEquals(expected, actual);

    }

    public void testAuthorUrl() {

        String expected = RandomStringGenerator.random();
        imageItem.setAuthorUrl(expected);
        String actual = imageItem.getAuthorUrl();
        Assert.assertEquals(expected, actual);

    }

}
