import React, {Component} from 'react';

class Counter extends Component {

    badgeClasses() {
        return "badge m-2 badge-" +
            ((this.props.counter.value === 0) ? "warning" : "primary");
    }

    formatCount() {
        const { value } = this.props.counter;
        return value === 0 ? 'Zero' : value;
    }

    componentDidUpdate(prevProps) {
        const prevValue = prevProps.counter.value;
        const id = this.props.counter.id;
        const currentValue = this.props.counter.value;
        if (prevValue !== currentValue) {
            // Normally, this would be a good place to make Ajax call.
            console.log('Value of counter #' + id + ' changed: ' +
                prevValue + ' → ' + currentValue);
        }
    }

    render() {
        console.log('Counter - Rendered');
        return (
            <div>
                {this.props.children}
                <span className={this.badgeClasses()}>{this.formatCount()}</span>
                <button className="btn btn-secondary btn-sm"
                        onClick={() => this.props.onIncrement(this.props.counter)}>
                    Increment
                </button>
                <button className="btn btn-warning btn-sm m-2"
                        onClick={() => this.props.onSetToZero(this.props.counter)}>
                    Set to Zero
                </button>
                <button className="btn btn-danger btn-sm m-2"
                        onClick={() => this.props.onDelete(this.props.counter)}>
                    Delete
                </button>
            </div>
        );
    }
}

export default Counter;