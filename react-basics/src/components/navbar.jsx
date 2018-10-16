import React, {Component} from 'react';

/**
 * Stateless Functional Component - alternative for stateless components.
 * Can't use lifecycle hooks like Component#constructor, Component#componentDidMount.
 */
const Navbar = ({ message, sumOfCounters }) => {
    console.log('Navbar - Rendered');
    return (
        <nav className="navbar navbar-light bg-light">
            <a className="navbar-brand" href="#">
                {message} <span className="badge badge-pill badge-secondary">{sumOfCounters}</span>
            </a>
        </nav>
    );
};

export default Navbar;