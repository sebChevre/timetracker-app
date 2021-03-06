package ch.sebooom.timetracker.domaine.periode;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
public class Duree {

    private long heures;

    private long minutes;

    Duree(){}

    public static Duree from (ZonedDateTime dateDebut, ZonedDateTime dateFin){
        
        checkDatesCoherence(dateDebut,dateFin);
        Duration duration = Duration.between(dateDebut,dateFin);

        Duree duree = new Duree();
        duree.heures = duration.toHours();
        duree.minutes = duration.toMinutes() % 60;

        return  duree;
    }

    private static void checkDatesCoherence(ZonedDateTime dateDebut, ZonedDateTime dateFin){

        //duree < 1h
        Duration d = Duration.between(dateDebut, dateFin);

        //if(d.toHours() < 1){
         //   throw  new DureeBusinessException("Date fin doit etre plus avance de 1h que date debut");
       // }

    }

    public long minutes (){
        return minutes;
    }

    public long heures () {
        return heures;
    }
}
