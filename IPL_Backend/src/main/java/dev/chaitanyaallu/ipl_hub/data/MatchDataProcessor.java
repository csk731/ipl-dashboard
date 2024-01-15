package dev.chaitanyaallu.ipl_hub.data;

import dev.chaitanyaallu.ipl_hub.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {
    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(final MatchInput matchInput) throws Exception {
        Match match = new Match();

        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());

        // Set Team 1 and Team 2 depending on the innings order
        // If Team 1 bats first, then Team 1 is the batting team and Team 2 is the bowling team

        String team1 = matchInput.getTeam1();
        String team2 = matchInput.getTeam2();
        String tossWinner = matchInput.getToss_winner();
        String tossDecision = matchInput.getToss_decision();

        if(tossWinner.equals(team1) && tossDecision.equals(("bat")) || tossWinner.equals(team2) && tossDecision.equals(("field"))){
//            System.out.println("1. "+team1+"------"+team2);
            match.setFirstInningsTeam(team1);
            match.setSecondInningsTeam(team2);
        }
        else{
//            System.out.println("2. "+team1+"------"+team2);
            match.setFirstInningsTeam(team2);
            match.setSecondInningsTeam(team1);
        }

        match.setTossWinner(tossWinner);
        match.setTossDecision(tossDecision);
        match.setMatchWinner(matchInput.getWinner());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());

        return match;
    }
}