import React, {Component} from 'react';

class Navbar extends Component {

    /**
     * Taken from https://getbootstrap.com/docs/4.1/components/navbar#brand
     */
    render() {
        return (
            <nav className="navbar navbar-light bg-light">
                <a className="navbar-brand" href="#">
                    Navbar <span className="badge badge-pill badge-secondary">{this.props.nonZeroCount}</span>
                </a>
            </nav>
        );
    }
}

export default Navbar;