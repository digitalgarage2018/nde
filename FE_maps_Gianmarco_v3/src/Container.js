import React, {Component} from 'react';

import GitHubForkRibbon from 'react-github-fork-ribbon';
import PropTypes from 'prop-types';
import {withRouter, Switch, Link, Redirect, Route} from 'react-router-dom';
import { GoogleApiWrapper } from 'google-maps-react'

import styles from './styles.module.css';

/*
const GoogleApiWrapper = __IS_DEV__
    ? require('../src/index').GoogleApiWrapper
    : require('../dist').GoogleApiWrapper;
*/

class Container extends Component {
    static propTypes = {};

    static contextTypes = {
        router: PropTypes.object
    };

    render() {
        const {children, routes, routeDef} = this.props;

        return (
            <div className={styles.container}>

                <div className={styles.wrapper}>
                    <div className={styles.list}>
                        <ul>
                            {routes.map(route => (
                                <Link key={route.path} to={route.path}>
                                    <li>{route.name} or Go to somewhere else</li>
                                </Link>
                            ))}
                        </ul>
                    </div>

                    <div className={styles.content}>
                        <div className={styles.header}>
                            <h1>{routeDef && routeDef.name} WebImmobiliare</h1>

                        </div>

                        <Switch>
                            {routes.map(route => (
                                <Route
                                    key={route.name}
                                    path={route.path}
                                    routeDef={route}
                                    routes={routes}
                                    render={routingProps => (
                                        <div>
                                            <route.component
                                                {...routingProps}
                                                google={this.props.google}
                                                loaded={this.props.loaded}
                                            />
                                        </div>
                                    )}
                                />
                            ))}
                            <Redirect path="*" to={'/basic'} />
                        </Switch>
                    </div>
                </div>
            </div>
        );
    }
}

const Loading = () => <div>Fancy loading container</div>;

export default withRouter(
    GoogleApiWrapper({
        apiKey: 'AIzaSyAeZarnT-wYAMc6IZpwls-P6Cf90H_SVRk',
        libraries: ['places', 'visualization'],
        LoadingContainer: Loading
    })(Container)
);