import React from 'react';
import { Route, Switch } from 'react-router-dom';
import HomePage from './HomePage/HomePage';
import NotFoundPage from './NotFoundPage/NotFoundPage';
import PropTypes from 'prop-types';
import Header from './Header/Header'
import Footer from './Footer/Footer'

class App extends React.Component {
    render() {
        return (
            <React.Fragment>
                <Header />
                <Switch>
                    <Route exact path='/' component={HomePage} />
                    <Route component={NotFoundPage} />
                </Switch>
                <Footer />
            </React.Fragment>
        );
    }
}

App.propTypes = {
    children: PropTypes.element
};

export default App;
