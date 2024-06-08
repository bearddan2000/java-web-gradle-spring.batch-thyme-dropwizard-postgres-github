package example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.model.keywords.Keyword;

@Repository("KeywordRepository")
public interface KeywordRepository extends JpaRepository<Keyword, Long>{}
