import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { MatchSmallCard } from "../components/MatchSmallcard";
import { MatchDetailCard } from "../components/MatchDetailCard";
import { MatchCard } from "../components/MatchCard";

export const MatchPage = () => {
    const [matches , setMatches] = useState([]);
    const {teamName,year}=useParams();

    useEffect(() => {
            const fetchMatches = async() => {
                const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
                const data = await response.json();
                console.log(data);
                setMatches(data);
            }
        fetchMatches();
    },[teamName,year]);

    return (
        <div>
            {matches.map(match => <MatchCard key={match.id} match={match}/>)}
        </div>
    )
}