package dev.chaitanyaallu.ipl_hub.controller;


import dev.chaitanyaallu.ipl_hub.model.Match;
import dev.chaitanyaallu.ipl_hub.model.Team;
import dev.chaitanyaallu.ipl_hub.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchController {
    private MatchRepository matchRepository;
    @Autowired
    public MatchController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }
    @GetMapping("/match/{id}")
    public Match getMatch(@PathVariable("id") Long id) {
        System.out.println("id = " + id);
        Match match= matchRepository.findById(id).get();
        return match;
    }
}
