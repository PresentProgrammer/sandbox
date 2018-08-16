import React, {Component} from 'react';

class Counter extends Component {
    state = {
        value: this.props.counter.value
    };

    /**
     * In arrow function, 'this' resolves to lexically enclosing environment.
     * Particularly in this case, 'this' will reverence this object, as in Java.
     */
    handleIncrement = () => {
        this.setState({ value: this.state.value + 1 });
    };

    // Alternative way is to use constructor and bind the function.
    // constructor() {
    //     super();
    //     this.handleIncrement = this.handleIncrement.bind(this);
    // }

    handleSetCounterToZero = () => {
        this.setState({ value: 0 });
    };

    badgeClasses() {
        let classes = "badge m-2 badge-";
        classes += (this.state.value === 0) ? "warning" : "primary";
        return classes;
    }

    formatCount() {
        const { value } = this.state;
        return value === 0 ? 'Zero' : value;
    }

    render() {
        return (
            <div>
                {this.props.children}
                <span className={this.badgeClasses()}>{this.formatCount()}</span>
                <button className="btn btn-secondary btn-sm"
                        onClick={this.handleIncrement}>
                    Increment
                </button>
                <button className="btn btn-warning btn-sm m-2"
                        onClick={this.handleSetCounterToZero}>
                    Set to Zero
                </button>
                <button className="btn btn-danger btn-sm m-2"
                        onClick={() => this.props.onDelete(this.props.counter.id)}>
                    Delete
                </button>
            </div>
        );
    }
}

export default Counter;