import React, {Component} from 'react';

class Counter extends Component {
    state = {
        value: this.props.value
    };

    /**
     * In arrow function, 'this' resolves to lexically enclosing environment.
     * Particularly in this case, 'this' will reverence this object, as in Java.
     */
    handleIncrement = product => {
        console.log(product);
        this.setState({ value: this.state.value + 1 });
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
                        onClick={ () => this.handleIncrement({ id: 1 }) }>
                    Increment
                </button>
            </div>
        );
    }

    badgeClasses() {
        let classes = "badge m-2 badge-";
        classes += (this.state.value === 0) ? "warning" : "primary";
        return classes;
    }

    formatCount() {
        const { value } = this.state;
        return value === 0 ? 'Zero' : value;
    }
}

export default Counter;