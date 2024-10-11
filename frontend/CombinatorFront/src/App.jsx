import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'

import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import './App.css';

function App() {
    return (
        <Router>

            <div>
                <h1>Parts numbers Generator-Combinator Application</h1>

                <Routes>
                    <Route path="/" element={
                        <div>
                            <p>App helps to create a structure for warehouse and accounting codes, mainly for fasteners - screws, nuts,
                                washers for the accounting program for a manufacturing company - to initially insert them into the accounting
                                program database. This ensures consistent naming as the database evolves and different names are used across a wide
                                range of products.</p>
                            {/* U¿ywamy Link zamiast button */}
                            <Link to="/main">
                                <button>Get Started!</button>
                            </Link>
                        </div>
                    } />
                    <Route path="/main" element={
                        <div>
                            <h1>Main Page</h1>
                            <p>This is another page</p>
                            {/* U¿ywamy Link zamiast button */}
                            <Link to="/">
                                <button>Back to Home</button>
                            </Link>
                        </div>
                    } />
                </Routes>
            </div>
        </Router>
    );
}

export default App;