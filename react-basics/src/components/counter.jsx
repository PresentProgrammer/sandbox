import React, {Component} from 'react';

class Counter extends Component {
    state = {
        count: 0
    };

    /**
     * In arrow function, 'this' resolves to lexically enclosing environment.
     * Particularly in this case, 'this' will reverence this object, as in Java.
     */
    handleIncrement = product => {
        console.log(product);
        this.setState({ count: this.state.count + 1 });
    };

    // Alternative way is to use constructor and bind the function.
    // constructor() {
    //     super();
    //     this.handleIncrement = this.handleIncrement.bind(this);
    // }

    render() {
        return (
            <div>
                <span className={this.badgeClasses()}>{this.formatCount()}</span>
                <button className="btn btn-secondary btn-sm"
                        onClick={ () => this.handleIncrement( { id: 1 }) }>
                    Increment
                </button>
            </div>
        );
    }

    badgeClasses() {
        let classes = "badge m-2 badge-";
        classes += (this.state.count === 0) ? "warning" : "primary";
        return classes;
    }

    formatCount() {
        const { count } = this.state;
        return count === 0 ? 'Zero' : count;
    }
}

export default Counter;