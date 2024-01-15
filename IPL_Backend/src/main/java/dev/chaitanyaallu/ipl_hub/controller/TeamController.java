package dev.chaitanyaallu.ipl_hub.controller;

import dev.chaitanyaallu.ipl_hub.model.Match;
import dev.chaitanyaallu.ipl_hub.model.Team;
import dev.chaitanyaallu.ipl_hub.repository.MatchRepository;
import dev.chaitanyaallu.ipl_hub.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class TeamController {

    private TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable("teamName") String teamName){
        Team team=teamRepository.findByTeamName(teamName);
        if (team==null){
            throw new RuntimeException("Team not found with name: "+teamName);
        }
        team.setMatches(matchRepository.findLatestMatches(teamName,4));
        return team;
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable("teamName") String teamName, @RequestParam("year") int year){
        //List<Match> matches = matchRepository.getMatchesByFirstInningsTeamAndDateBetweenOrSecondInningsTeamAndDateBetweenOrderByDateDesc(teamName, LocalDate.of(year,1,1), LocalDate.of(year,12,31), teamName, LocalDate.of(year,1,1),LocalDate.of(year,12,31));
        List<Match> matches=matchRepository.getMatchesByTeamBetweenDates(teamName,LocalDate.of(year,1,1), LocalDate.of(year,12,31));
        return matches;
    }
}
