package csvfiles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import csvfiles.model.ApiField;
import csvfiles.model.Api;

@Repository
public interface ApiFieldRepository extends JpaRepository<ApiField, Long> {
    // Get all Api Fields by api_id
    @Query("SELECT af FROM ApiField af WHERE af.apiId = :apiId")
	public List<ApiField> findAllByApiId(@Param("apiId") Api api);
}