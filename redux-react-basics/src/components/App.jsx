import React, {Component} from 'react';
import PostForm from "./postForm";
import Posts from "./posts";
import {Provider} from 'react-redux';
import store from '../store';

class App extends Component {
    render() {
        return (
            <Provider store={store}>
                <div>
                    <PostForm/>
                    <hr/>
                    <Posts/>
                </div>
            </Provider>
        );
    }
}

export default App;