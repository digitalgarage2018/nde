import React, { Component } from 'react';
import './App.css';
// import the Google Maps API Wrapper from google-maps-react
import { GoogleApiWrapper } from 'google-maps-react';
// import child component
import MapContainer from './MapContainer';
import MapWrapper from "./researchBar";
class App extends Component {
    render() {

        return (
            <div>
                <MapContainer google={this.props.google} />
            </div>
        );
    }
}

export default GoogleApiWrapper({
    apiKey: 'AIzaSyAeZarnT-wYAMc6IZpwls-P6Cf90H_SVRk',
})(App)
