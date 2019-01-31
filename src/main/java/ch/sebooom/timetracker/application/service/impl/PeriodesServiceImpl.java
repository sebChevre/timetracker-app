package ch.sebooom.timetracker.application.service.impl;

import ch.sebooom.timetracker.application.service.PeriodesService;
import ch.sebooom.timetracker.domaine.command.ClorePeriodeCommande;
import ch.sebooom.timetracker.domaine.command.CreerPeriodeCommande;
import ch.sebooom.timetracker.domaine.periode.Periode;
import ch.sebooom.timetracker.domaine.periode.PeriodeDomaineRepository;
import ch.sebooom.timetracker.domaine.periode.PeriodesDomaineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodesServiceImpl implements PeriodesService {

    @Autowired
    PeriodeDomaineRepository periodeDomaineRepository;

    public Periode ouvrirPeriode () {
        return null;
    }

    @Override
    public List<Periode> findAll() {
        return periodeDomaineRepository.chargerPeriodes();
    }

    @Override
    public Periode ouvrirPeriode(CreerPeriodeCommande commande) {

        PeriodesDomaineService periodesDomaineService = new PeriodesDomaineService(periodeDomaineRepository);

        return periodesDomaineService.ourvirPeriode(commande);

    }

    @Override
    public Periode fermerPeriode(ClorePeriodeCommande commande) {
        PeriodesDomaineService periodesDomaineService = new PeriodesDomaineService(periodeDomaineRepository);

        return periodesDomaineService.fermerPeriode(commande);

    }
}
