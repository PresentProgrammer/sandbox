import React, {Component} from 'react';
import PostForm from "./postForm";
import Posts from "./posts";

class App extends Component {
    render() {
        return (
            <div>
                <PostForm/>
                <hr/>
                <Posts/>
            </div>
        );
    }
}

export default App;