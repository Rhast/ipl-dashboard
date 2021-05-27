import {React} from 'react';
import {Link} from 'react-router-dom';
import './YearSelector.scss'
export const YearSelector = ({teamName}) => {

    const years = getYears();

    return (
        <ol className="YearSelector">
            {
                years.map(year => (
                    <li>
                        <Link to={`/teams/${teamName}/matches/${year}`}>{year}</Link>
                    </li>
                ))
            }
        </ol>
    );
};

function getYears() {
    const startYear = process.env.REACT_APP_DATA_START_YEAR;
    const endYear = process.env.REACT_APP_DATA_END_YEAR;

    const years = [];
    for (let year = startYear; year <= endYear; year++) {
        years.push(year);
    }

    return years;
}
