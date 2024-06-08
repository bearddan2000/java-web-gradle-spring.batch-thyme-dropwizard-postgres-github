package example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.model.catagory.Catagory;

@Repository("CatagoryRepository")
public interface CatagoryRepository extends JpaRepository<Catagory, Long>{}
