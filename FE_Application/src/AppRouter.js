import React from 'react';
import HomePage from "./scenes/HomePage/HomePage";
import SignUpPage from "./scenes/SignUpPage/SignUpPage";
import InitialSearchPage from "./scenes/InitialSearchPage/InitialSearchPage";
import MapContainer from "./scenes/MapPage/MapPage";
import Profile from "./scenes/Profile/Profile";
import SpecPage from "./scenes/SpecPage/SpecPage";
import AboutUs from "./scenes/AboutUs/AboutUs";
import {Route,Router} from 'react-router-dom';


export const AppRouter = () => {
    return (
        <div>
            <Route exact path="/" component={HomePage}/>
            <Route path="/signUp" component={SignUpPage}/>
            <Route path="/initialSearch" component={InitialSearchPage}/>
            <Route path="/map" component={MapContainer}/>
            <Route path="/profile" component={Profile}/>
            <Route path="/specPage" component={SpecPage}/>
            <Route path="/aboutUs" component={AboutUs}/>
        </div>
    )
};