package za.co.ajk.spring5webapp.repositories;

import org.springframework.data.repository.CrudRepository;

import za.co.ajk.spring5webapp.model.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
