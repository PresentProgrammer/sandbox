import React, { Component } from 'react';
import Counter from "./counter";

class Counters extends Component {

    render() {
        const { counters, onReset, onIncrement, onSetToZero, onDelete } = this.props;
        return (<div>
            <button className="btn btn-primary btn-sm m-2" onClick={onReset}>Reset</button>
            {counters.map(counter =>
                <Counter key={counter.id} counter={counter}
                         onIncrement={onIncrement}
                         onSetToZero={onSetToZero}
                         onDelete={onDelete}>
                    <h4>Counter #{counter.id}</h4>
                </Counter>)
            }
        </div>);
    }
}

export default Counters;