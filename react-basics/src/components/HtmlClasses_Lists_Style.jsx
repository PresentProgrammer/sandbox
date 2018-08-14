import React, {Component} from 'react';

/**
 * This class is not used in project anymore, but is kept to show how to:
 *  - Dynamically render HTML classes;
 *  - Render lists;
 *  - Apply styles.
 */
class Counter extends Component {
    state = {
        value: 1,
        tags: [ 'tag1', 'tag2', 'tag3' ]
    };

    render() {
        return (
            <div>
                <span className={this.badgeClasses()}>{this.formatCount()}</span>
                <button style={{ fontSize: 30 }} className="btn btn-secondary btn-sm">Increment</button>
                <ul>
                    { this.state.tags.map(tag => <li key={tag}>{tag}</li>) }
                </ul>
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