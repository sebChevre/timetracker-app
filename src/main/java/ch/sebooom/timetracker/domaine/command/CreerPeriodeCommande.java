package ch.sebooom.timetracker.domaine.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CreerPeriodeCommande {

    private LocalDateTime debut;
    private LocalDateTime fin;

    public CreerPeriodeCommande(){}


    @JsonIgnore
    public boolean isDateFinSetted(){
        return fin != null;
    }
}
