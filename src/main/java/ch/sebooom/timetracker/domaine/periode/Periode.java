package ch.sebooom.timetracker.domaine.periode;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * <h2>Classe période. Modélise une période</h2>
 *
 * <h3>Règles de gestion:</h3>
 *
 * <ul>
 *     <li>Une instance de période doit obligatoirement posséder une date de debut</li>
 *     <li>La date de fin doit au moins être égale à la date de debut</li>
 *     <li>Le temps passé est caculé en heures</li>
 * </ul>
 *
 */
@Slf4j
@NonNull
public class Periode {

    @NonNull
    private LocalDateTime dateDebut;

    private LocalDateTime dateFin;

    private Long id;

    private EtatPeriode etat = EtatPeriode.OUVERTE;



    public static Periode ouvrirPeriode(LocalDateTime dateDebut){
        return new Periode(dateDebut);
    }

    public Periode(LocalDateTime dateDebut){

        this.dateDebut = dateDebut;
        this.dateFin = resolveDateFin();
        etat = EtatPeriode.OUVERTE;
    }


    public boolean isFerme(){
        return etat.equals(EtatPeriode.FERME);
    }

    public void complete(LocalDateTime dateFin){
        checkCompleteCoherence(dateFin);
        this.etat = EtatPeriode.FERME;
        this.dateFin = dateFin;

    }

    public Duree getDuree(){
        return Duree.from(dateDebut,resolveDateFin());
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    private LocalDateTime resolveDateFin() {
        return (dateFin == null) ? LocalDateTime.now() : dateFin;
    }

    private void checkCompleteCoherence(LocalDateTime dateFin){

        if(etat.equals(EtatPeriode.FERME)){
            throw  new PeriodeBusinessException("Le période est déjà fermée");
        }
        //duree < 1h
        Duration d = Duration.between(dateDebut, dateFin);

        if(d.toHours() < 1){
            throw  new PeriodeBusinessException("Date fin doit etre plus avance de 1h que date debut");
        }

    }

    public EtatPeriode getEtat() {
        return etat;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
