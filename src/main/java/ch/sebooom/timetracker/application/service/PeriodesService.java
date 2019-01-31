package ch.sebooom.timetracker.application.service;

import ch.sebooom.timetracker.domaine.command.ClorePeriodeCommande;
import ch.sebooom.timetracker.domaine.command.CreerPeriodeCommande;
import ch.sebooom.timetracker.domaine.periode.Periode;
import ch.sebooom.timetracker.infrastructure.dao.PeriodeDao;

import java.util.List;

public interface PeriodesService {

    List<Periode> findAll();

    Periode ouvrirPeriode(CreerPeriodeCommande commande);

    Periode fermerPeriode(ClorePeriodeCommande commande);
}
