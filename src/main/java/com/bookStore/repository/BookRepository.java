package com.bookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bookStore.entity.Book;

@Repository
//@Component
public interface BookRepository extends JpaRepository<Book, Integer>{

	

}
