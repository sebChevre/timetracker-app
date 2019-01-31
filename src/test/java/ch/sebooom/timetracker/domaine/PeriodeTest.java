package ch.sebooom.timetracker.domaine;

import ch.sebooom.timetracker.domaine.periode.Duree;
import ch.sebooom.timetracker.domaine.periode.DureeBusinessException;
import ch.sebooom.timetracker.domaine.periode.Periode;
import ch.sebooom.timetracker.domaine.periode.PeriodeBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class PeriodeTest {

    @Test
    public void test_builder_instanciation(){

        Periode periode = Periode.ouvrirPeriode(LocalDateTime.now());

        assertThat(periode).isNotNull();
        assertThat(periode).isNotNull();

    }

    @Test
    public void when_no_dateFin_then_dateNowIsUsed(){

        LocalDateTime debut = LocalDateTime.now().minus(2,ChronoUnit.HOURS);
        Periode periode = Periode.ouvrirPeriode(debut);
        assertThat(periode).isNotNull();
        assertThat(periode.isFerme()).isFalse();

        assertThat(periode.getDuree().heures()).isGreaterThan(1L);

        log.info("NB Heures: {}",periode.getDuree().heures());

    }

    @Test
    public void when_dateFin_setted_then_dateFinIsUsed(){

        LocalDateTime debut = LocalDateTime.of(2000,12,12,10,10);
        LocalDateTime fin = LocalDateTime.of(2000,12,12,18,10);

        Periode periode = Periode.ouvrirPeriode(debut);
        periode.complete(fin);

        assertThat(periode).isNotNull();
        assertThat(periode.getDuree().heures()).isGreaterThan(1L).isEqualTo(8L);
        assertThat(periode.isFerme()).isTrue();

        assert(true);
        log.info("Nb Heures: {}",periode.getDuree().heures());

    }

    @Test
    public void ensure_that_no_dateFin_less_1h_is_not_possible(){

        LocalDateTime debut = LocalDateTime.of(2000,12,12,10,10);
        final LocalDateTime fin = LocalDateTime.of(2000,12,12,10,20);

        Periode periode = Periode.ouvrirPeriode(debut);

        assertThrows(PeriodeBusinessException.class,()->{
            periode.complete(fin);
        });

        assertThat(periode.isFerme()).isFalse();

        final LocalDateTime fin2 = LocalDateTime.of(2000,12,12,11,20);
        periode.complete(fin2);
        assertThat(periode.isFerme()).isTrue();

        log.info("{}",periode.getDuree().heures());

    }

    @ParameterizedTest
    @MethodSource("createMinutesIncrementAndHeuresMinutesDureeVlueExpected")
    public void assertThatDureeRetourneeAreCorrect (long mIncrement, long heuresRetounees, long minutesRertournees) {


        LocalDateTime dateFin = LocalDateTime.now().plus(mIncrement,ChronoUnit.MINUTES);

        Periode periode = Periode.ouvrirPeriode(LocalDateTime.now());
        periode.complete(dateFin);

        assertThat(periode.getDuree().minutes()).isEqualTo(minutesRertournees);
        assertThat(periode.getDuree().heures()).isEqualTo(heuresRetounees);


        assertThrows(DureeBusinessException.class,()->{
            Duree dureeNegative = Duree.from(LocalDateTime.now(),LocalDateTime.now().minus(1,ChronoUnit.HOURS));
            assertFalse(true);
        });

    }

    private static Stream<Arguments> createMinutesIncrementAndHeuresMinutesDureeVlueExpected() {
        return Stream.of(
                Arguments.of(60L, 1L,0L),
                Arguments.of(80L, 1L,20L),
                Arguments.of(150L, 2L,30L),
                Arguments.of(200L, 3L,20L));
    }

}