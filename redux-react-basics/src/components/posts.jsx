import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchPosts} from "../actions/postActions";

class Posts extends Component {

    componentWillMount() {
        console.log('Posts - will mount');
        this.props.fetchPosts();
    }

    render() {
        console.log('Posts - render');
        return (
            <div>
                <h1>Posts</h1>
                {this.props.posts.map(post => (
                    <div key={post.id}>
                        <h3>{post.title}</h3>
                        <p>{post.body}</p>
                    </div>
                ))}
            </div>
        );
    }
}

const mapStateToProps = state => ({
    posts: state.posts.items
});

export default connect(mapStateToProps, { fetchPosts })(Posts);