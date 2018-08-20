import {FETCH_POSTS, NEW_POST} from "./types";

export const fetchPosts = () => dispatch => {
    fetch('https://jsonplaceholder.typicode.com/posts')
        .then(res => res.json())
        .then(posts => dispatch({
            type: FETCH_POSTS,
            payload: posts
        }));
};

export const submitNewPost = post => dispatch => {
    fetch('https://jsonplaceholder.typicode.com/posts', {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(post)
    }).then(res => res.json())
        .then(newPost => dispatch({
            type: NEW_POST,
            payload: newPost
        }));
};