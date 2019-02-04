package ch.sebooom.timetracker.domaine.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class CreerPeriodeCommande {

    private ZonedDateTime debut;
    private ZonedDateTime fin;

    public CreerPeriodeCommande(){}


    @JsonIgnore
    public boolean isDateFinSetted(){
        return fin != null;
    }
}
