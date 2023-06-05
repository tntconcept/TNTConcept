package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Tag;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.TagSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TagDAO_IT extends IntegrationTest {
    final TagDAO tagDAO;

    public TagDAO_IT() {
        tagDAO = (TagDAO) SpringUtils.getSpringBean("daoTag");
    }

    @Test
    public void loadByIdShouldLoadTag() {
        final int tagId = 1;

        final Tag tag = tagDAO.loadById(tagId);

        assertEquals("Test", tag.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullTag() {
        final int tagId = Integer.MAX_VALUE;

        final Tag tag = tagDAO.loadById(tagId);

        assertNull(tag);
    }

    @Test
    public void getByIdShouldGetTag() {
        final int tagId = 1;

        final Tag tag = tagDAO.getById(tagId);

        assertEquals("Test", tag.getName());
    }

    @Test
    public void getByIdShouldGetNullTag() {
        final int tagId = Integer.MAX_VALUE;

        final Tag tag = tagDAO.getById(tagId);

        assertNull(tag);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultTag() {
        Tag tag = createTag();
        tagDAO.insert(tag);

        List<Tag> tags = tagDAO.search(new SortCriteria());

        assert(tags.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedTag() {
        Tag tag = createTag();
        tagDAO.insert(tag);

        TagSearch tagSearch = new TagSearch();
        tagSearch.setName(tag.getName());
        List<Tag> tags = tagDAO.search(tagSearch, new SortCriteria());

        assert(tags.size() == 1);
    }

    @Test
    public void updateShouldChangeTagName() {
        Tag tagToUpdate = tagDAO.getById(1);
        tagToUpdate.setName("Update");

        tagDAO.update(tagToUpdate);

        Tag updatedTag = tagDAO.getById(1);
        assertEquals("Update", updatedTag.getName());
    }

    @Test
    public void deleteShouldRemoveTag() {
        Tag tagToDelete = tagDAO.getById(1);

        tagDAO.delete(tagToDelete);

        assertNull(tagDAO.getById(1));
    }

    private Tag createTag() {
        Tag tag = new Tag();
        tag.setName("Tag");
        tag.setDescription("");
        tag.setOwnerId(1);
        tag.setDepartmentId(1);

        return tag;
    }
}