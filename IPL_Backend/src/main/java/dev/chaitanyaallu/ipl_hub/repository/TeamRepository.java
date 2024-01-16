package dev.chaitanyaallu.ipl_hub.repository;

import dev.chaitanyaallu.ipl_hub.model.Team;
import org.aspectj.weaver.ast.ITestVisitor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long>  {

     Team findByTeamName(String teamName);
     Team save(Team team);
     List<Team> findAll();

}
