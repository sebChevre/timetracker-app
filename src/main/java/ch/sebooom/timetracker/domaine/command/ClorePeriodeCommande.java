package ch.sebooom.timetracker.domaine.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
public class ClorePeriodeCommande {

    private ZonedDateTime fin;

    private Long periodeId;

    public ClorePeriodeCommande(){}

}
