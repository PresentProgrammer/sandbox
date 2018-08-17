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

    /**
     * 2nd update phase lifecycle hook after render().
     * Normally, this would be a good place to make Ajax call if the value has changed.
     * Note that this method is called even if the the values for this component have not changed.
     */
    componentDidUpdate(prevProps) {
        const prevValue = prevProps.counter.value;
        const id = this.props.counter.id;
        const currentValue = this.props.counter.value;
        if (prevValue !== currentValue) {
            console.log('Value of counter #' + id + ' changed: ' +
                prevValue + ' → ' + currentValue);
        }
    }

    /**
     * Unmount phase lifecycle hook.
     * Good place to do clean-ups, e.g., to remove event listeners.
     */
    componentWillUnmount() {
        console.log("Counter - Unmount");
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