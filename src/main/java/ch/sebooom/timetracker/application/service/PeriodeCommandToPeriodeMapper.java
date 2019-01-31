package ch.sebooom.timetracker.application.service;

import ch.sebooom.timetracker.domaine.command.CreerPeriodeCommande;
import ch.sebooom.timetracker.domaine.periode.Periode;
import org.mapstruct.Mapper;

@Mapper
public interface PeriodeCommandToPeriodeMapper {

    CreerPeriodeCommande sourceToDestination(Periode source);
    Periode destinationToSource(CreerPeriodeCommande destination);

}
