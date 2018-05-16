import React from 'react';
import HomePage from "./scenes/HomePage/HomePage";
import SignUpPage from "./scenes/SignUpPage/SignUpPage";
import InitialSearchPage from "./scenes/InitialSearchPage/InitialSearchPage";
import MapContainer from "./scenes/MapPage/MapPage";
import {Route,Router} from 'react-router-dom';


export const AppRouter = () => {
    return (
        <div>
            <Route exact path="/" component={HomePage}/>
            <Route path="/signUp" component={SignUpPage}/>
            <Route path="/initialSearch" component={InitialSearchPage}/>
            <Route path="/map" component={MapContainer}/>
        </div>
    )
};