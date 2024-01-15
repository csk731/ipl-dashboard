import React from 'react'
import { Link } from 'react-router-dom';

export const MatchSmallCard = ({match, teamName}) => {
    
    if(!match) return null;
   
    const otherTeam = match.firstInningsTeam === teamName ? match.secondInningsTeam : match.firstInningsTeam;
    const otherTeamRoute = `/team/${otherTeam}`;
   
    return (
        <div className='MatchSmallCard'>
        <p> <b>vs </b> 
            <Link to={otherTeamRoute}>{otherTeam}</Link>
        </p>
        <p>{match.matchWinner} won by {match.resultMargin} {match.result}</p>
        </div>
    )
    
}
