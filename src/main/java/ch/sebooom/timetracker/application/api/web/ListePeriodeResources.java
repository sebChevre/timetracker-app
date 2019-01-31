package ch.sebooom.timetracker.application.api.web;

import ch.sebooom.timetracker.domaine.periode.Periode;
import lombok.Getter;

import java.util.List;

@Getter
public class ListePeriodeResources {

    private List<Periode> periodes;

    public ListePeriodeResources(List<Periode> periodes){
        this.periodes = periodes;
    }
}
