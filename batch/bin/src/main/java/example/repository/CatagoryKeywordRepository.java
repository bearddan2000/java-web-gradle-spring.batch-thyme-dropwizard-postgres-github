package example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.model.catagory.CatagoryKeyword;

@Repository("CatagoryKeywordRepository")
public interface CatagoryKeywordRepository extends JpaRepository<CatagoryKeyword, Long>{}
