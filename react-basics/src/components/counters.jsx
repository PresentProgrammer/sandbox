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

    handleDelete = (counterId) => {
        const counters = this.state.counters.filter(c => c.id !== counterId);
        this.setState({ counters }); // simplified from { counters: counters }
    };

    handleReset = () => {
        const counters = this.state.counters.map(c => {
            return { id: c.id, value: 0 };
        });
        this.setState({ counters });
    };

    render() {
        return (<div>
            <button className="btn btn-primary btn-sm m-2"
                    onClick={this.handleReset}>Reset</button>
            {this.state.counters.map(counter =>
                <Counter key={counter.id} onDelete={this.handleDelete}
                         counter={counter}>
                    <h4>Counter #{counter.id}</h4>
                </Counter>)
            }
        </div>);
    }

}

export default Counters;