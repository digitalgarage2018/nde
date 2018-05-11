import React, {Component} from 'react';
import ReactDOM from 'react-dom'
import {Map, Marker, InfoWindow} from "google-maps-react";
import PropTypes from "prop-types";
import {researchBar} from "./researchBar";

export default class MapContainer extends Component {

    constructor(props) {
        super(props);

        const {lat, lng} = this.props.initialCenter;
        this.state = {
            currentLocation: {
                lat: lat,
                lng: lng
            },
            showingInfoWindow: false,
            activeMarker: {},
            selectedPlace: {},
        }
    }



    //GIANMARCO: update status when mounting/updating...
    componentDidMount() {
        if (this.props.centerAroundCurrentLocation) {
            if (navigator && navigator.geolocation) {
                navigator.geolocation.getCurrentPosition((pos) => {
                    const coords = pos.coords;
                    this.setState({
                        currentLocation: {
                            lat: coords.latitude,
                            lng: coords.longitude
                        }
                    })
                })
            }
        }
    }

    componentDidUpdate(prevProps, prevState) {
        if (prevState.currentLocation !== this.state.currentLocation) {
            this.recenterMap();
        }
    }

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
    };

    windowHasClosed = (props) => {
        this.setState({
            selectedPlace: {},
            activeMarker: {},
            showingInfoWindow: false
        })
    }

    //onsubmit2()



 /*   loadMap() {
        if (this.props && this.props.google) { // checks to make sure that props have been passed
            const {google} = this.props; // sets props equal to google
            const maps = google.maps; // sets maps to google maps props

            const mapRef = this.refs.map; // looks for HTML div ref 'map'. Returned in render below.
            const node = ReactDOM.findDOMNode(mapRef); // finds the 'map' div in the React DOM, names it node

            const mapConfig = Object.assign({}, {
                center: {lat: 40.7485722, lng: -74.0068633}, // sets center of google map to NYC.
                zoom: 11, // sets zoom. Lower numbers are zoomed further out.
                mapTypeId: 'roadmap' // optional main map layer. Terrain, satellite, hybrid or roadmap--if unspecified, defaults to roadmap.
            })

            this.map = new maps.Map(node, mapConfig); // creates a new Google map on the specified node (ref='map') with the specified configuration set above.

        }
    }
*/

    /*renderChildren() {
        const {children} = this.props;

        if (!children) return;

        return React.Children.map(children, c => {
            return React.cloneElement(c, {
                map: this.map,
                google: this.props.google,
                mapCenter: this.state.currentLocation
            });
        })
    }*/

    render() {
        const style = {
            width: '100vw',
            height: '100vh'
        }

        return ( // in our return function you must return a div with ref='map' and style.
            <div>
                <researchBar />
                <Map
                    google={this.props.google}
                    style={style}
                    initialCenter={this.props.initialCenter}
                    zoom={this.props.zoom}
                    onClick={this.onMapClicked}
                >
                    {/*{this.renderChildren()}*/}
                    <Marker onClick={this.onMarkerClick}
                        name={'Current location'} />

                    <InfoWindow
                        onClose={this.windowHasClosed}
                        marker={this.state.activeMarker}
                        visible={this.state.showingInfoWindow}>
                        <div>
                            <h1>{this.state.selectedPlace.name}</h1>
                        </div>
                    </InfoWindow>
                </Map>
            </div>
        )
    }
}

MapContainer.propTypes = {
    google: PropTypes.object.isRequired,
    zoom: PropTypes.number,
    initialCenter: PropTypes.object,
    centerAroundCurrentLocation: PropTypes.bool
};

MapContainer.defaultProps = {
    zoom: 14,
    // Milan, by default
    initialCenter: {
        lat: 45.463194,
        lng: 9.184940
    },
    centerAroundCurrentLocation: true
};