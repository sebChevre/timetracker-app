package ch.sebooom.timetracker.application;

import ch.sebooom.timetracker.domaine.periode.Periode;
import ch.sebooom.timetracker.infrastructure.jpa.PeriodeJpaRepository;
import ch.sebooom.timetracker.infrastructure.dao.PeriodeDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ch.sebooom.timetracker.infrastructure.jpa"})
@EntityScan(basePackages = "ch.sebooom.timetracker.infrastructure.dao")
@ComponentScan(basePackages = "ch.sebooom.timetracker")
public class TimeTrackerApplication {


    @Autowired
    PeriodeJpaRepository periodeJpaRepository;

    public static void main(String ...args) {
        SpringApplication.run(TimeTrackerApplication.class);
    }

    @Transactional
    @PostConstruct
    public void init () {

        Periode p = Periode.ouvrirPeriode(ZonedDateTime.now().minus(3, ChronoUnit.HOURS));

        PeriodeDao pdo = PeriodeDao.get(p);
        periodeJpaRepository.save(pdo);

        p = PeriodeDao.toEntity(periodeJpaRepository.findById(pdo.getId()).get());

        p.complete(ZonedDateTime.now());

        periodeJpaRepository.save(PeriodeDao.get(p));

        Periode p2 = Periode.ouvrirPeriode(ZonedDateTime.now().minus(6, ChronoUnit.HOURS));
        periodeJpaRepository.save(PeriodeDao.get(p2));


    }

    @Bean
    ObjectMapper objectMapper () {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
        return mapper;
    }


}
