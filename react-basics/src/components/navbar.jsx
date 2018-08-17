import React, {Component} from 'react';

/**
 * Stateless Functional Component - alternative for stateless components.
 */
const Navbar = props => {
    return (
        <nav className="navbar navbar-light bg-light">
            <a className="navbar-brand" href="#">
                Navbar <span className="badge badge-pill badge-secondary">{props.totalCount}</span>
            </a>
        </nav>
    );
};

export default Navbar;