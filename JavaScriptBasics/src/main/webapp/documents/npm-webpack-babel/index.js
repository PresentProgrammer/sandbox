import moment from 'moment';

console.log(document.getElementById('results'));
const msg = `Hello with webpack.config.js, babel and scripts! ${moment().startOf('day').fromNow()}`;
document.getElementById('results').innerHTML = msg;

const name = "Arturs", time = "today";
console.log(`Hello ${name}, how are you ${time}?`);