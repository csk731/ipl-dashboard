import React from 'react'
import { Link } from 'react-router-dom';

export const MatchDetailCard = ({match, teamName}) => {

    if(!match) return null;
   
    const otherTeam = match.firstInningsTeam === teamName ? match.secondInningsTeam : match.firstInningsTeam;
    const otherTeamRoute = `/team/${otherTeam}`;
    
    return (
        <div className='MatchDetailCard'>
        <h3>Latest Matches</h3>
        <h4>Match Details</h4>
        <p><b>vs </b> 
            <Link to={otherTeamRoute}>{otherTeam}</Link>
        </p>
        <p>{match.date}</p>
        <p>{match.venue}</p>
        <p>{match.playerOfMatch}</p>
        <p>{match.matchWinner} won by {match.resultMargin} {match.result}</p>
        </div>
    )
}