const moment = require('moment');

console.log(document.getElementById('results'));
const msg = 'Hello with webpack.config.js! ' + moment().startOf('day').fromNow();
document.getElementById('results').innerHTML = msg;