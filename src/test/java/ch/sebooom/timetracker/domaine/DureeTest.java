package ch.sebooom.timetracker.domaine;

import ch.sebooom.timetracker.domaine.periode.Duree;
import ch.sebooom.timetracker.domaine.periode.DureeBusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DureeTest {

    @Test
    public void test_static_builder () {

        Duree duree = Duree.from(LocalDateTime.now(),LocalDateTime.now().plus(1,ChronoUnit.HOURS));
        assertThat(duree).isNotNull();
    }

    @Test
    public void assertThatFinEqualOrGreatherThanOneHours () {

        assertThrows(DureeBusinessException.class,()->{
            Duree duree = Duree.from(LocalDateTime.now(),LocalDateTime.now());
        });


    }

    @ParameterizedTest
    @MethodSource("createHeureIncrementAndValueExpected")
    public void assertThatHeuresRetourneeAreCorrect (long hIncrement, long heuresRertournees) {

        Duree duree = Duree.from(LocalDateTime.now(),LocalDateTime.now().plus(hIncrement,ChronoUnit.HOURS));
        assertThat(duree.heures()).isEqualTo(heuresRertournees);


        assertThrows(DureeBusinessException.class,()->{
            Duree dureeNegative = Duree.from(LocalDateTime.now(),LocalDateTime.now().minus(1,ChronoUnit.HOURS));
            assertFalse(true);
        });

    }

    private static Stream<Arguments> createHeureIncrementAndValueExpected() {
        return Stream.of(
                Arguments.of(1L, 1L),
                Arguments.of(13L, 13L),
                Arguments.of(26L, 26L),
                Arguments.of(10999009L, 10999009L));
    }

    private static Stream<Arguments> createMinutesIncrementAndVMinutesalueExpected() {
        return Stream.of(
                Arguments.of(60L, 0L),
                Arguments.of(80L, 20L),
                Arguments.of(150L, 30L),
                Arguments.of(200L, 20L));
    }

    @ParameterizedTest
    @MethodSource("createMinutesIncrementAndVMinutesalueExpected")
    public void assertThatMinutesRetourneeAreCorrect (long mIncrement, long minutesRertournees) {


        LocalDateTime uneHeurePlsTard = LocalDateTime.now().plus(mIncrement,ChronoUnit.MINUTES);

        Duree duree = Duree.from(LocalDateTime.now(),uneHeurePlsTard);

        assertThat(duree.minutes()).isEqualTo(minutesRertournees);


        assertThrows(DureeBusinessException.class,()->{
            Duree dureeNegative = Duree.from(LocalDateTime.now(),LocalDateTime.now().minus(1,ChronoUnit.HOURS));
            assertFalse(true);
        });

    }

}