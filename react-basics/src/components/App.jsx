import React, {Component} from 'react';
import Navbar from './navbar';
import Counters from './counters';

class App extends Component {

    state = {
        counters: [
            { id: 1, value: 4 },
            { id: 2, value: 2 },
            { id: 3, value: 0 },
            { id: 4, value: 1 }
        ]
    };

    /**
     * 1st mounting phase lifecycle hook.
     */
    constructor(props) {
        super(props);
        console.log('App - Constructor with props:', this.props);
    }

    /**
     * 3rd mounting phase lifecycle hook.
     * Good place to make Ajax calls and get data for the component from server.
     */
    componentDidMount() {
        console.log('App - Mounted')
    }

    handleIncrement = (counter) => {
        const counters = [...this.state.counters];
        const index = counters.indexOf(counter);
        counters[index] = {...counter};
        counters[index].value++;
        this.setState({ counters });
    };

    handleSetToZero = (counter) => {
        const counters = [...this.state.counters];
        const index = counters.indexOf(counter);
        counters[index] = Object.assign({}, counter, { value: 0 });
        this.setState({ counters });
    };

    handleDelete = (counter) => {
        const counters = this.state.counters.filter(c => c.id !== counter.id);
        this.setState({ counters }); // simplified from { counters: counters }
    };

    handleReset = () => {
        const counters = this.state.counters.map(c => {
            return Object.assign({}, c, { value: 0 });
        });
        this.setState({ counters });
    };

    /**
     * 2nd mounting phase lifecycle hook.
     */
    render() {
        console.log('App - Rendered');
        return (
            <React.Fragment>
                <Navbar sumOfCounters={this.state.counters.map(c => parseInt(c.value)).reduce((sum, value) => sum + value)}/>
                <main className="container">
                    <Counters counters={this.state.counters}
                        onReset={this.handleReset}
                        onIncrement={this.handleIncrement}
                        onSetToZero={this.handleSetToZero}
                        onDelete={this.handleDelete}/>
                </main>
            </React.Fragment>
        );
    }
}

export default App;