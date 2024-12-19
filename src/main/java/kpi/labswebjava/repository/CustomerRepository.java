package kpi.labswebjava.repository;

import kpi.labswebjava.repository.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, UUID> {
}
