import {FETCH_POSTS} from "./types";

export const fetchPosts = () => dispatch => {
    console.log("fetchPosts called");
    fetch('https://jsonplaceholder.typicode.com/posts')
        .then(res => res.json())
        .then(posts => dispatch({
            type: FETCH_POSTS,
            payload: posts
        }));
};