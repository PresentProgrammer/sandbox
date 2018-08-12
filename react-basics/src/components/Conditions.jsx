import React, {Component} from 'react';

/**
 * This class is not used in project anymore, but is kept to show how to:
 *  - Add element if condition is met
 *  - Use condition with && operator.
 */
class Counter extends Component {
    state = {
        tags: [ 'tag1', 'tag2', 'tag3' ]
    };

    renderTags() {
        if (this.state.tags.length === 0)
            return <p>There are no tags!</p>;
        else
            return (
                <ul>
                    {this.state.tags.map(tag => <li key={tag}>{tag}</li>)}
                </ul>
            );
    }

    render() {
        return (
            <div>
                { this.state.tags.length > 0 && <p>Thanks for having some tags!</p> }
                { this.renderTags() }
            </div>
        );
    }
}

export default Counter;