package ch.sebooom.timetracker.infrastructure.dao;

import ch.sebooom.timetracker.domaine.periode.EtatPeriode;
import ch.sebooom.timetracker.domaine.periode.Periode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
public class PeriodeDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private ZonedDateTime dateDebut;
    private ZonedDateTime dateFin;
    private EtatPeriode etat;


    public PeriodeDao () {}

    private PeriodeDao(Long id,ZonedDateTime dateDebut, ZonedDateTime dateFin, EtatPeriode etat) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.etat = etat;
    }

    public static PeriodeDao get(Periode periode){
        return  new PeriodeDao(periode.getId(),periode.getDateDebut(),periode.getDateFin(),periode.getEtat());
    }

    public static Periode toEntity(PeriodeDao periodeDao){

        Periode p = Periode.ouvrirPeriode(periodeDao.dateDebut);
        p.setId(periodeDao.id);

        if(periodeDao.getEtat().equals(EtatPeriode.FERME)){
            p.complete(periodeDao.dateFin);
        }

        return p;
    }
}
