import {FETCH_POSTS, NEW_POST} from "./types";
import axios from 'axios';

export const fetchPosts = () => dispatch => {
    axios.get('https://jsonplaceholder.typicode.com/posts').then(response => {
        dispatch({
            type: FETCH_POSTS,
            payload: response.data
        })
    });
};

export const submitNewPost = post => dispatch => {
    axios.post('https://jsonplaceholder.typicode.com/posts', post).then(response => {
        dispatch({
            type: NEW_POST,
            payload: response.data
        });
    });
};