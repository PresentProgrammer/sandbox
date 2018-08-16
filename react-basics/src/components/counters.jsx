import React, { Component } from 'react';
import Counter from "./counter";

class Counters extends Component {

    state = {
        counters: [
            { key: 1, value: 4 },
            { key: 2, value: 2 },
            { key: 3, value: 0 },
            { key: 4, value: 1 }
        ]
    };

    handleDelete = () => {
        console.log('handleDelete() called');
    };

    render() {
        return (<div>
            {this.state.counters.map(counter =>
                <Counter key={counter.key} value={counter.value}
                    onDelete={this.handleDelete}/>)}
        </div>);
    }

}

export default Counters;