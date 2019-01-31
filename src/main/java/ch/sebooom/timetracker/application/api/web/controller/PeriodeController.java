package ch.sebooom.timetracker.application.api.web.controller;

import ch.sebooom.timetracker.application.api.web.ListePeriodeResources;
import ch.sebooom.timetracker.application.service.PeriodesService;
import ch.sebooom.timetracker.domaine.command.ClorePeriodeCommande;
import ch.sebooom.timetracker.domaine.command.CreerPeriodeCommande;
import ch.sebooom.timetracker.domaine.periode.Periode;
import ch.sebooom.timetracker.infrastructure.dao.PeriodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("periodes")
public class PeriodeController {

    @Autowired
    PeriodesService periodesService;

    @CrossOrigin
    @GetMapping
    public ListePeriodeResources getAllperiodes(){

        return new ListePeriodeResources(periodesService.findAll());

    }

    @CrossOrigin
    @PostMapping
    public Periode savePeriode(@RequestBody CreerPeriodeCommande commande){

        return periodesService.ouvrirPeriode(commande);

    }

    @PostMapping("/{id}/clore")
    public Periode clorePeriode(@PathVariable String id, @RequestBody ClorePeriodeCommande commande){

        commande.setPeriodeId(Long.parseLong(id));
        return periodesService.fermerPeriode(commande);

    }
}
