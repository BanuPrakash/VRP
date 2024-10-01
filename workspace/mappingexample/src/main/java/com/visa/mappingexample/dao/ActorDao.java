package com.visa.mappingexample.dao;

import com.visa.mappingexample.entity.Actor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorDao extends JpaRepository<Actor, Integer> {
}
