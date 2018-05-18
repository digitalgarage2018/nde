import React from 'react'
import HomePage from "./scenes/HomePage/HomePage";
import SignUpPage from "./scenes/SignUpPage/SignUpPage";
import InitialSearchPage from "./scenes/InitialSearchPage/InitialSearchPage";
<<<<<<< HEAD
import MapContainer from "./scenes/MapPage/MapContainer";
import Profile from "./scenes/Profile/profile";
import CaratteristicHouse from "./scenes/CaratteristicHouse/caratteristicHouse";
import AboutUs from "./scenes/AboutUs/aboutUs";
=======
import MapContainer from "./scenes/MapPage/MapPage";
>>>>>>> fe7c22726a2f8d436ccc3b2ccb19d4781f0d20ae
import {Route,Router} from 'react-router-dom';


export const AppRouter = () => {
    return (
        <div>
            <Route exact path="/" component={HomePage}/>
            <Route path="/signUp" component={SignUpPage}/>
            <Route path="/initialSearch" component={InitialSearchPage}/>
            <Route path="/map" component={MapContainer}/>
            <Route path="/profile" component={Profile}/>
            <Route path="/aboutUs" component={AboutUs}/>
            <Route path="/caratteristicHouse" component={CaratteristicHouse}/>
        </div>
    )
};