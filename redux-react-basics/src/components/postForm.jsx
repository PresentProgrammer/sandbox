import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {submitNewPost} from '../actions/postActions';

class PostForm extends Component {

    state = this.props.post;

    onChange = event => {
        console.log('onChange: ' + event.target.value);
        this.setState({ [event.target.name]: event.target.value });
    };

    onSubmit = event => {
        event.preventDefault();
        console.log('onSubmit event triggered');
        this.props.submitNewPost({
            title: this.state.title,
            body: this.state.body
        });
    };

    render() {
        return (
            <div>
                <h1>Add Post</h1>
                <form onSubmit={this.onSubmit}>
                    <div>
                        <label>Title:</label>
                        <br/>
                        <input type="text" name="title" value={this.state.title}
                                onChange={this.onChange}/>
                    </div>
                    <br/>
                    <div>
                        <label>Body:</label>
                        <br/>
                        <textarea name="body" value={this.state.body}
                                  onChange={this.onChange}/>
                    </div>
                    <br/>
                    <button type="submit">Submit</button>
                </form>
            </div>
        );
    }
}

PostForm.propTypes = {
    submitNewPost: PropTypes.func.isRequired,
    post: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    post: state.posts.item
});

export default connect(mapStateToProps, { submitNewPost })(PostForm);