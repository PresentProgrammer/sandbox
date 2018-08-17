import React, {Component} from 'react';
import Navbar from './navbar';
import Counters from './counters';

class App extends Component {

    render() {
        return (
            <React.Fragment>
                <Navbar/>
                <main className="container">
                    <Counters/>
                </main>
            </React.Fragment>
        );
    }
}

export default App;