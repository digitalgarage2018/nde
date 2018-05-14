import React, { Component } from 'react';
import {Map} from './Map';
import {Marker} from './Marker';
import {InfoWindow} from './InfoWindow';
import { GoogleApiWrapper } from 'google-maps-react'
import {Autocomplete} from "./Autocomplete";

export class Container extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            showingInfoWindow: false,
            activeMarker: {},
            selectedPlace: {}
        };
    }

    onMapClick = () =>
            this.setState({
                showingInfoWindow: false,
                activeMarker: null
            });



    onMarkerClick = (props, marker, e) =>
        this.setState({
            selectedPlace: props,
            activeMarker: marker,
            showingInfoWindow: true
        });

    onInfoWindowClose = () =>
        this.setState({
            showingInfoWindow: false,
            activeMarker: null
        });


    render() {
        const style = {
            width: '100vw',
            height: '100vh'
        }

        const pos = {lat: 25.783318, lng: -80.134017} //i.e. Miami Beach


        /*
        GIANMARCO: I must write down the markers in the return for the Container rendering, in order to let them be seen on map.
        That's a strange behaviour since I must be able to retrieve that from Axios and then create a Marker.
         */

        return (
            <div style={style}>
                <Map google={this.props.google} onClick={this.onMapClick}>
                <Autocomplete {...this.props}/>
                <Marker />
                <Marker onClick={this.onMarkerClick} name={'Miami Beach'} position={pos} />
                    <InfoWindow
                        marker={this.state.activeMarker}
                        visible={this.state.showingInfoWindow}
                        onClose={this.onInfoWindowClose}>
                        <div>
                            <h1>{this.state.selectedPlace.name}</h1>
                        </div>
                    </InfoWindow>
                </Map>
            </div>
        )
    }
}

export default GoogleApiWrapper({
    apiKey: "AIzaSyAeZarnT-wYAMc6IZpwls-P6Cf90H_SVRk"
})(Container)
