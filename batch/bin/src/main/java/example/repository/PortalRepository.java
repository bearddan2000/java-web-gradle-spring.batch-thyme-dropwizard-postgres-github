package example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import example.model.Portal;

@Repository("PortalRepository")
@Transactional
public interface PortalRepository extends JpaRepository<Portal, Long>{}
