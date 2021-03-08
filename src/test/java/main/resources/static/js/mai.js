Vue.component('prod', {
    template:`<h1> {{ mes }}</h1>`
})

var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello User!'
    }
})
var app2 = new Vue({
    el: '#app-2',
    data: {
        message: 'You loaded this page on ' + new Date().toLocaleString()
    }
})
var app3 = new Vue({
    el: '#app-3',
    data: {
        seen: true
    }
})
var app4 = new Vue({
    el: '#app-4',
    data: {
        todos: [
            { text: 'Learn JavaScript' },
            { text: 'Learn Vue' },
            { text: 'Build something awesome' },
        ]
    }
})



var app5 = new Vue ({
    el: '#app-5',
    data: {

    }
})