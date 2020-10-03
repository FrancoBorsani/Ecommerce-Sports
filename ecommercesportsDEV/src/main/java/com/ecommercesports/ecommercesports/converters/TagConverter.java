package com.ecommercesports.ecommercesports.converters;

import com.ecommercesports.ecommercesports.entities.Tag;
import com.ecommercesports.ecommercesports.models.TagModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tagConverter")
public class TagConverter {
    @Autowired
    @Qualifier("tagConverter")
    private TagConverter tagConverter;

    public TagModel entityToModel(Tag tag) {
        return new TagModel(tag.getId(), tag.getNombre());
    }

    public Tag modelToEntity(TagModel tagModel) {
        return new Tag(tagModel.getIdTag(), tagModel.getNombre());
    }
}