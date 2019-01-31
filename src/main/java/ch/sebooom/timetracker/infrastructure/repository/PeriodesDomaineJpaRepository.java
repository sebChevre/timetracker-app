package ch.sebooom.timetracker.infrastructure.repository;

import ch.sebooom.timetracker.domaine.periode.Periode;
import ch.sebooom.timetracker.domaine.periode.PeriodeDomaineRepository;
import ch.sebooom.timetracker.infrastructure.dao.PeriodeDao;
import ch.sebooom.timetracker.infrastructure.jpa.PeriodeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PeriodesDomaineJpaRepository implements PeriodeDomaineRepository {

    @Autowired
    PeriodeJpaRepository periodeJpaRepository;


    @Override
    public Periode ouvrirPeriode(Periode periode) {
        PeriodeDao pdao = PeriodeDao.get(periode);

        pdao = periodeJpaRepository.save(pdao);

        return PeriodeDao.toEntity(pdao);

    }

    @Override
    public Periode fermerPeriode(Periode periode) {
        PeriodeDao pdao = PeriodeDao.get(periode);

        pdao = periodeJpaRepository.save(pdao);

        return PeriodeDao.toEntity(pdao);
    }

    @Override
    public Periode chargerPeriode(Long periodeId) {

        PeriodeDao pdao = periodeJpaRepository.findById(periodeId).get();

        return PeriodeDao.toEntity(pdao);
    }

    @Override
    public List<Periode> chargerPeriodes() {

        return periodeJpaRepository.findAll()
                .stream()
                .map(pdao -> PeriodeDao.toEntity(pdao))
                .collect(Collectors.toList());

    }
}
