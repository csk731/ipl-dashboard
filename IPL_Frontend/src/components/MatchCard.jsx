import React from 'react'
import { Link } from 'react-router-dom';

export const MatchCard = ({match}) => {
    
    if(!match) return null;
   
    return (
        <div className='MatchSmallCard'>
        <h4> 
            {match.firstInningsTeam} <b>vs</b> {match.secondInningsTeam}
        </h4>
        <p>{match.date}</p>
        <p>{match.venue}</p>
        </div>
    )
}
