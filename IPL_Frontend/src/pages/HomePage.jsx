import React, { useEffect, useState } from "react";
import { TeamTile } from "../components/TeamTile";
import './HomePage.scss'

export const HomePage = () => {

    const [teams, setTeams] = useState([]);

    useEffect(() => {
        const  fetchAllTeams = async() => {
            const response = await fetch('http://localhost:8080/teams')
            const data = await response.json();
            setTeams(data);
        }
        fetchAllTeams();
    },[teams])

    return(
        <div className="HomePage">
            <div className="header-section">
                <h1 className="app-name">Welcome to IPL Hub</h1>
            </div>
            <div className="team-list-heading">
                <h3>Select a team to see the latest matches</h3>
            </div>
            <div className="team-grid">
                {teams.map(team=> <div key={team}><TeamTile teamName={team.teamName}/></div>)}
            </div>
       </div>
    )
}