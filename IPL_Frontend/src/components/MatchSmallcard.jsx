import React from 'react'
import { Link } from 'react-router-dom';
import './MatchSmallCard.scss';

export const MatchSmallCard = ({match, teamName}) => {
    
    if(!match) return null;
   
    const otherTeam = match.firstInningsTeam === teamName ? match.secondInningsTeam : match.firstInningsTeam;
    const otherTeamRoute = `/team/${otherTeam}`;
   const isMatchWon = teamName === match.matchWinner;
    return (
        <div className={isMatchWon ? 'won-card MatchSmallCard' : 'lost-card MatchSmallCard'}>
        <div>
            <span className='vs'>vs</span> 
            <h1 className='other-team-name'><Link to={otherTeamRoute}>{otherTeam}</Link></h1>
        </div>
        <p className='match-result'>{match.matchWinner} won by {match.resultMargin} {match.result}</p>
        </div>
    )
}
