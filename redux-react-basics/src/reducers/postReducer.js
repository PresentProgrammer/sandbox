import {FETCH_POSTS, NEW_POST} from "../actions/types";

const initialState = {
    items: [],
    item: {
        title: 'John',
        body: 'Big Boss'
    }
};

export default function(state = initialState, action) {
    switch(action.type) {
        case FETCH_POSTS:
            return {...state, items: action.payload};
        case NEW_POST:
            const newItem = action.payload;
            return {...state, items: state.items.concat(newItem), item: newItem };
        default:
            return state;
    }
}