package ch.sebooom.timetracker.domaine.periode;

import java.time.LocalDateTime;
import java.util.List;

public interface PeriodeDomaineRepository {

    Periode ouvrirPeriode(Periode periode);

    Periode fermerPeriode(Periode periode);

    Periode chargerPeriode(Long periodeId);

    List<Periode> chargerPeriodes();

}
