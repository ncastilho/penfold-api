const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {
    render() {
        return <p>Hello Katy Penfold!</p>
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('content')
)

