import React, {Component} from 'react';

/**
 * Stateless Functional Component - alternative for stateless components.
 */
const Navbar = ({ sumOfCounters }) => {
    return (
        <nav className="navbar navbar-light bg-light">
            <a className="navbar-brand" href="#">
                Navbar <span className="badge badge-pill badge-secondary">{sumOfCounters}</span>
            </a>
        </nav>
    );
};

export default Navbar;