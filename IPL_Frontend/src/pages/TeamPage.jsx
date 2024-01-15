import React, { useEffect, useState } from 'react';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallcard';
import { useParams } from 'react-router-dom';
import './TeamPage.scss';
import { PieChart } from 'react-minimal-pie-chart';

export const TeamPage = () => {
  const [team, setTeam] = useState([]);

  const { teamName } = useParams();

  useEffect(() => {
    const fetchMatches = async () => {
      const response = await fetch(`http://localhost:8080/team/${teamName}`);
      const data = await response.json();
      setTeam(data);
    };
    fetchMatches();
  }, [teamName]);

  if (!team || !team.teamName) {
    return <h1>Team not found</h1>;
  }

  return (
    <div className='TeamPage'>
        <div className='team-name-section'>
          <h1 className='team-name'>{team.teamName}</h1>
        </div>
        <div className='wins-losses-section'>
          <p>Wins/Losses</p>
        <PieChart
          data={[
            { title: 'Wins', value: team.totalWins, color: '#b44650' },
            { title: 'Losses', value: team.totalMatches-team.totalWins, color: '#3a9a51' },
          ]}
          />

        </div>
        <div className='match-details-card'>
          <MatchDetailCard match={team.matches[0]} teamName={team.teamName} />
        </div>
            {team.matches.slice(1).map((match) => (
                <MatchSmallCard key={match.id} teamName={team.teamName} match={match} />
            ))}
          <div className='more-link'>
            <a href='#'>More > </a>
          </div>
    </div>
  );
};
