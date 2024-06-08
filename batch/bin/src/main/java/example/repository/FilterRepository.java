package example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.model.Filter;

@Repository("FilterRepository")
public interface FilterRepository extends JpaRepository<Filter, Long>{}
