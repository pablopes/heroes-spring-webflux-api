package com.pablocosta.heroesapi.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.pablocosta.heroesapi.document.Heroes;
@EnableScan
public interface HeroesRepository extends CrudRepository<Heroes, String>{

}
