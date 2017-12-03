package com.smilegate.serverdevcamp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smilegate.serverdevcamp.model.URL;

public interface URLRepository extends JpaRepository<URL, Long>{
	URL findById(long id);
	
	URL findByOriginalURL(String originalURL);
	
	@Query(nativeQuery = true, value = "select auto_increment from information_schema.TABLES where TABLE_NAME ='url' and TABLE_SCHEMA='urlshortener'")
	Long getNextId();
}
