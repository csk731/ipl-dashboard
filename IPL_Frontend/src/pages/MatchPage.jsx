import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { MatchDetailCard } from "../components/MatchDetailCard";
import "./MatchPage.scss";
import { YearSelector } from "../components/YearSelector";

export const MatchPage = () => {
    const [matches , setMatches] = useState([]);
    const {teamName,year}=useParams();

    useEffect(() => {
            const fetchMatches = async() => {
                const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
                const data = await response.json();
                setMatches(data);
            }
        fetchMatches();
    },[teamName,year]);

    return (
        <div className="MatchPage">
            <div className="year-selector">
                <YearSelector teamName={teamName}/>
            </div>
            <div className="matches-list">
                <h3 className="matches-list-heading">{teamName} matches in the year {year}</h3>
                {matches.map(match => <MatchDetailCard key={match.id} match={match} teamName={teamName}/>)}
            </div>
        </div>
    )
}