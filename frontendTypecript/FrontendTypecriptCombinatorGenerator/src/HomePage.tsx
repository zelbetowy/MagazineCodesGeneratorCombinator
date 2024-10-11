import React from 'react';
import { Link } from 'react-router-dom';
import './styles/home-page.css';

const HomePage: React.FC = () => (
    <div className="home-page">
        <div className="panel">
            <p>Helps to create a structure for warehouse and accounting codes, mainly for fasteners - screws, nuts,
                washers for the accounting program for a manufacturing company - to initially insert them into the accounting
                program database. This ensures consistent naming as the database evolves and different names are used across a wide
                range of products.</p>
        </div>
        <div className="Button1">
            <Link to="/main-page">
                <button>Get Started!</button>
            </Link>
        </div>
    </div>
);

export default HomePage;