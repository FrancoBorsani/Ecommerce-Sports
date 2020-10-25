package com.ecommercesports.ecommercesports.repositories;

import com.ecommercesports.ecommercesports.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("tagRepository")
public interface ITagRepository extends JpaRepository<Tag, Serializable> {
    Tag findByIdTag(long idTag);
}