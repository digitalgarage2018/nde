import React, { Component } from 'react';

import Map from 'google-maps-react';

import {Marker} from './Marker';
import {InfoWindow} from './InfoWindow';


import styles from './autocomplete.module.css';


class Contents extends Component {
    constructor(props) {
        super(props);
        const {lat, lng} = this.props.initialCenter;
        this.state = {
            position: null,

            showingInfoWindow: false,
            activeMarker: {},
            selectedPlace: {},
        }
    }

    componentDidMount() {
        this.renderAutoComplete();
    }

    componentDidUpdate(prevProps) {
        if (this.props !== prevProps.map) this.renderAutoComplete();
    }

    onSubmit(e) {
        e.preventDefault();
    }

    onMarkerClick = (props, marker, e) =>
        this.setState({
            selectedPlace: props,
            activeMarker: marker,
            showingInfoWindow: true
        });

    onMapClicked = (props) => {
        if (this.state.showingInfoWindow) {
            this.setState({
                showingInfoWindow: false,
                activeMarker: null
            })
        }
        this.recenterMap();
    };

    recenterMap() {
        const map = this.map;
        const curr = this.state.currentLocation;

        const google = this.props.google;
        const maps = google.maps;

        if (map) {
            let center = new maps.LatLng(curr.lat, curr.lng)
            map.panTo(center)
        }
    }

    windowHasClosed = (props) => {
        this.setState({
            selectedPlace: {},
            activeMarker: {},
            showingInfoWindow: false
        })
    };

    renderAutoComplete() {
        const { google, map } = this.props;

        if (!google || !map) return;

        const autocomplete = new google.maps.places.Autocomplete(this.autocomplete);
        autocomplete.bindTo('bounds', map);

        autocomplete.addListener('place_changed', () => {
            const place = autocomplete.getPlace();

            if (!place.geometry) return;

            if (place.geometry.viewport) map.fitBounds(place.geometry.viewport);
            else {
                map.setCenter(place.geometry.location);
                map.setZoom(17);
            }

            this.setState({ position: place.geometry.location });
        });
    }

    render() {
        const { position } = this.state;
        const style = {
            width: '100vw',
            height: '80vh'
        }

        return (
            <div className={styles.flexWrapper}>
                <div className={styles.left}>
                    <form onSubmit={this.onSubmit}>
                        <input
                            placeholder="Enter a location"
                            ref={ref => (this.autocomplete = ref)}
                            type="text"
                        />

                        <input className={styles.button} type="submit" value="Go" />
                    </form>

                    <div>
                        <div>Lat: {position && position.lat()}</div>
                        <div>Lng: {position && position.lng()}</div>
                    </div>
                </div>

                <div className={styles.right}>
                    <Map
                        {...this.props}
                        style={style}
                        center={position}
                        centerAroundCurrentLocation={false}
                        onClick={this.onMapClicked}
                        containerStyle={{
                            height: '100vh',
                            position: 'relative',
                            width: '100%'
                        }}>
                        <Marker/>
                        <Marker onClick={this.onMarkerClick} name={'Current Location'} position={position} />
                        <InfoWindow onClose={this.windowHasClosed} marker={this.state.activeMarker} visible={this.state.showingInfoWindow}>
                            <div>
                                <h1>{this.state.selectedPlace.name}</h1>
                            </div>
                        </InfoWindow>
                    </Map>
                </div>
            </div>
        );
    }
}
Contents.defaultProps = {
    zoom: 14,
    // Milan, by default
    initialCenter: {
        lat: 45.463194,
        lng: 9.184940
    },
    centerAroundCurrentLocation: true
};

const MapWrapper = props => (
    <Map className="map" google={props.google} visible={false}>
        <Contents {...props} />
    </Map>
);

export default MapWrapper;