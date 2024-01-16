import React from "react";
import "./YearSelector.scss";
import { Link } from "react-router-dom";

export const YearSelector = ({teamName}) => {
    
    let years =[];
    const startYear =import.meta.env.VITE_APP_DATA_START_YEAR;
    const endYear = import.meta.env.VITE_APP_DATA_END_YEAR;


    for(let i=startYear;i<=endYear;i++){
        years.push(i);
    }

    return(
        <ol className="YearSelector">
            <h3>Select Year</h3>
            {years.map(year => 
                <li key={year}>
                    <Link to={`/team/${teamName}/matches/${year}`}>{year}</Link>
                </li>
            )}
        </ol>
    )
};