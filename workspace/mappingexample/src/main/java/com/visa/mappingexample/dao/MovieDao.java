package com.visa.mappingexample.dao;

import com.visa.mappingexample.entity.Movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDao extends JpaRepository<Movie, Integer> {
}
