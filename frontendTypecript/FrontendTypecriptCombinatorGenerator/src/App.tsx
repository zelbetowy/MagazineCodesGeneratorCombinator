import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './HomePage';  // Import nowego komponentu HomePage
import MainPage from './MainPage';  // Import nowego komponentu MainPage
import Navbar from './Navbar';
import './styles/App.css';
import './styles/Navbar.css';

const App: React.FC = () => {
    return (
        <Router>
            <div>
                <Navbar />
                <div style={{ marginTop: '20px' }}>
                    <Routes>
                        <Route path="/" element={<HomePage />} /> {/* U¿ywamy nowego komponentu HomePage */}
                        <Route path="/main-page" element={<MainPage />} /> {/* U¿ywamy nowego komponentu MainPage */}
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;