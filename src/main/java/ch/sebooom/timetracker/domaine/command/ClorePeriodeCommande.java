package ch.sebooom.timetracker.domaine.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClorePeriodeCommande {

    private LocalDateTime fin;

    private Long periodeId;

    public ClorePeriodeCommande(){}

}
