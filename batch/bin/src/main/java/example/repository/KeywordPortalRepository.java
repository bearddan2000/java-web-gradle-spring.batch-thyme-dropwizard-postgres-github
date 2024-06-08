package example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.model.keywords.KeywordPortal;

@Repository("KeywordPortalRepository")
public interface KeywordPortalRepository extends JpaRepository<KeywordPortal, Long>{}
