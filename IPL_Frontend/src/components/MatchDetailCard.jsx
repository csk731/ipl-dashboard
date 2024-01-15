import React from 'react'
import { Link } from 'react-router-dom';
import './MatchDetailCard.scss';

export const MatchDetailCard = ({match, teamName}) => {

    if(!match) return null;
   
    const otherTeam = match.firstInningsTeam === teamName ? match.secondInningsTeam : match.firstInningsTeam;
    const otherTeamRoute = `/team/${otherTeam}`;
    const isMatchWon = teamName === match.matchWinner;

    return (
        <div className={isMatchWon ? 'won-card MatchDetailCard' : 'lost-card MatchDetailCard'}>
            <div>
                <div>
                    <span className='vs'>vs</span> 
                    <h1 className='other-team-name'><Link to={otherTeamRoute}>{otherTeam}</Link></h1>
                </div>
                <h4 className='match-date'>on {match.date}</h4>
                <h4 className='match-venue'>at {match.venue}</h4>
                <h3 className='match-result'>{match.matchWinner} won by {match.resultMargin} {match.result}</h3>
                </div>
                <div className='additional-detail'>
                    <h3>First Innings</h3>
                    <p>{match.firstInningsTeam}</p>
                    <h3>Second Innings</h3>
                    <p>{match.secondInningsTeam}</p>
                    <h3>Player of the Match</h3>
                    <p>{match.playerOfMatch}</p>
                    <h3>Umpires</h3>
                    <p>{match.umpire1}, {match.umpire2}</p>
                </div>
        </div>
    )
}