package ch.sebooom.timetracker.domaine.periode;

import ch.sebooom.timetracker.domaine.command.ClorePeriodeCommande;
import ch.sebooom.timetracker.domaine.command.CreerPeriodeCommande;
import ch.sebooom.timetracker.infrastructure.jpa.PeriodeJpaRepository;

public class PeriodesDomaineService {

    PeriodeDomaineRepository periodeDomaineRepository;

    public PeriodesDomaineService(PeriodeDomaineRepository periodeDomaineRepository) {
        this.periodeDomaineRepository = periodeDomaineRepository;
    }

    public Periode ourvirPeriode(CreerPeriodeCommande commande) {

        Periode periode = Periode.ouvrirPeriode(commande.getDebut());
        periode = periodeDomaineRepository.ouvrirPeriode(periode);

        if(commande.getFin() != null){
            periode.complete(commande.getFin());
            periode = periodeDomaineRepository.fermerPeriode(periode);
        }

        return periode;

    }

    public Periode fermerPeriode(ClorePeriodeCommande commande) {

        Periode periode = periodeDomaineRepository.chargerPeriode(commande.getPeriodeId());

        periode.complete(commande.getFin());

        periode = periodeDomaineRepository.fermerPeriode(periode);

        return periode;

    }
}
