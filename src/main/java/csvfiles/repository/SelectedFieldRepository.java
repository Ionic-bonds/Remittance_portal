package csvfiles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import csvfiles.model.SelectedField;
import csvfiles.model.ApiField;

@Repository
public interface SelectedFieldRepository extends JpaRepository<SelectedField, Long> {
    // Get all Selected Field by api_field_id
    @Query("SELECT sf FROM SelectedField sf WHERE sf.apiFieldId = :apiFieldId")
    public List<SelectedField> findAllByApiFieldId(@Param("apiFieldId") ApiField apiField);
}