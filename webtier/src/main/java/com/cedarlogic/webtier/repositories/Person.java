package com.cedarlogic.webtier.repositories;

import java.util.List;

import com.cedarlogic.webtier.models.Billionaires;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "billionaires", path = "billionaires")
public interface Person extends PagingAndSortingRepository<Billionaires, Long> {

  List<Billionaires> findByLastName(@Param("name") String name);

}