package ch.sebooom.timetracker.infrastructure.jpa;


import ch.sebooom.timetracker.infrastructure.dao.PeriodeDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodeJpaRepository extends JpaRepository<PeriodeDao,Long>{
}
