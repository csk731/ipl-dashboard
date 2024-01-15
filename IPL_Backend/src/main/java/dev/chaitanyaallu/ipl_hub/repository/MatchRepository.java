package dev.chaitanyaallu.ipl_hub.repository;

import dev.chaitanyaallu.ipl_hub.model.Match;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
    Optional<Match> findByIdEquals(Long id);
    List<Match> getByFirstInningsTeamOrSecondInningsTeamOrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    @Query("select m from Match m where (m.firstInningsTeam=:teamName or m.secondInningsTeam=:teamName) and m.date between :startDate and :endDate order by m.date desc")
    List<Match> getMatchesByTeamBetweenDates(@Param("teamName") String teamName,@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    //List<Match> getMatchesByFirstInningsTeamAndDateBetweenOrSecondInningsTeamAndDateBetweenOrderByDateDesc(String teamName1, LocalDate startDate1, LocalDate endDate1, String teamName2, LocalDate startDate2, LocalDate endDate2);
    default List<Match> findLatestMatches(String teamName, int count){
        return getByFirstInningsTeamOrSecondInningsTeamOrderByDateDesc(teamName, teamName, Pageable.ofSize(count));
    }
}
