package csvfiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import csvfiles.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}
