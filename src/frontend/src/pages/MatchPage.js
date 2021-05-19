import {React, useEffect, useState} from 'react';
import {MatchDetailCard} from '../components/MatchDetailCard';
import {useParams} from 'react-router-dom';

export const MatchPage = () => {
    const [matches, setMatches] = useState([]);
    const {teamName, year} = useParams();
    useEffect(() => {
        async function fetchMatches() {
            const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
            const data = await response.json();
            setMatches(data);
        }

        fetchMatches();
    }, [teamName, year]);

    if (!matches) {
        return <h1>No matches found</h1>;
    }
    return (
        <div className="MatchPage">
            <h1>Matches page</h1>
            {matches.map(match => <MatchDetailCard teamName={teamName} match={match}/> )}
        </div>
    );
};




