import React, { Component } from 'react';
import Counter from "./counter";

class Counters extends Component {

    state = {
        counters: [
            { id: 1, value: 4 },
            { id: 2, value: 2 },
            { id: 3, value: 0 },
            { id: 4, value: 1 }
        ]
    };

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

    render() {
        return (<div>
            <button className="btn btn-primary btn-sm m-2"
                    onClick={this.handleReset}>Reset</button>
            {this.state.counters.map(counter =>
                <Counter key={counter.id} counter={counter}
                         onIncrement={this.handleIncrement}
                         onSetToZero={this.handleSetToZero}
                         onDelete={this.handleDelete}>
                    <h4>Counter #{counter.id}</h4>
                </Counter>)
            }
        </div>);
    }

}

export default Counters;