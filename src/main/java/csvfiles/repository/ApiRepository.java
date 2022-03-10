package csvfiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csvfiles.model.Api;

@Repository
public interface ApiRepository extends JpaRepository<Api, Long> {
    
}