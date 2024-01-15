package dev.chaitanyaallu.ipl_hub.data;

import dev.chaitanyaallu.ipl_hub.model.Match;
import dev.chaitanyaallu.ipl_hub.model.Team;
import dev.chaitanyaallu.ipl_hub.repository.TeamRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);


    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
//            jdbcTemplate
//                    .query("SELECT first_innings_team, second_innings_team FROM match", new DataClassRowMapper<>(Match.class))
//                    .forEach(match -> log.info("Found <{{}, {}}> in the database.", match.getFirstInningsTeam(), match.getSecondInningsTeam()));
            Map<String, Team> teamData = new HashMap<>();

            entityManager.createQuery("select m.firstInningsTeam, count(*) from Match m group by m.firstInningsTeam", Object[].class)
                    .getResultList()
                    .stream()
                    .map(e -> new Team((String) e[0], (Long) e[1]))
                    .forEach(team -> teamData.put(team.getTeamName(), team));

            entityManager.createQuery("select m.secondInningsTeam, count(*) from Match m group by m.secondInningsTeam", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(team -> {
                        Team team1 = teamData.getOrDefault(team[0].toString(), new Team(team[0].toString(), 0L));
                        //System.out.println(team[0].toString()+"==="+(team1.getTotalMatches()+(Long) team[1]));
                        team1.setTotalMatches(team1.getTotalMatches() + (Long) team[1]);
                    });

            entityManager.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(team -> {
                        Team team1 = teamData.get(team[0].toString());
                        if (team1 != null) team1.setTotalWins((Long) team[1]);
                    });

//            teamRepository.save(new Team("ABC", 1L));
            teamData.values().forEach(team -> entityManager.persist(team));
//            teamData.values().forEach(System.out::println);
        }
    }
}