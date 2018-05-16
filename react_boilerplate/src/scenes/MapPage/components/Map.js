import React from 'react';
import ReactDOM from 'react-dom';
import PropTypes from 'prop-types';
import {camelize} from './lib/String';

//Gianmarco: declaring types of events I wanna handle
const evtNames = [
    'ready',
    'click',
    'dragend',
    'recenter',
    'bounds_changed',
    'center_changed',
    'dragstart',
    'heading_change',
    'idle',
    'maptypeid_changed',
    'mousemove',
    'mouseout',
    'mouseover',
    'projection_changed',
    'resize',
    'rightclick',
    'tilesloaded',
    'tilt_changed',
    'zoom_changed'
];


export class Map extends React.Component {
    constructor(props) {
        super(props);

        const {latitude, longitude} = this.props.initialCenter;

        if (this.props.houseList.length > 0) {
            this.state = {
                currentLocation: {
                    lat: this.props.houseList[0].city.latitude,
                    lng: this.props.houseList[0].city.longitude
                }
            }
        } else {
            this.state = {
                currentLocation: {
                    lat: 0,
                    lng: 0
                }
            }
        }
    }

    componentDidMount() {
        console.log(this.props.houseList);
        console.log(this.state.currentLocation);
        console.log(this.state.initialCenter);
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
        this.loadMap();
    }

    componentDidUpdate(prevProps, prevState) {
        if (prevProps.google !== this.props.google) {
            this.loadMap();
        }
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

    newCenter(e) {
        this.setState({
            currentLocation: {lat: e.latLng.lat(), lng: e.latLng.lng()}
        })
    }

    loadMap() {
        if (this.props && this.props.google) {
            // google is available
            const {google} = this.props;
            const maps = google.maps;

            const mapRef = this.refs.map;
            const node = ReactDOM.findDOMNode(mapRef);

            let {initialCenter, zoom} = this.props;
            const {lat, lng} = this.state.currentLocation;
            const center = new maps.LatLng(lat, lng);
            const mapConfig = {
                center: center,
                zoom: zoom,
                gestureHandling: 'greedy'
            };

            this.map = new maps.Map(node, mapConfig);

            //adding a single marker for center...doesn't work, it's static
            /*this.markerCenter = new google.maps.Marker({
                map: this.map,
                position: center,
            });*/

            evtNames.forEach(e => {
                this.map.addListener(e, this.handleEvent(e));
            });

            //Gianmarco: I can integrate this in the ^^ function, but let me declare it explicitly
            this.map.addListener('click', (e) => {this.newCenter(e);})

            maps.event.trigger(this.map, 'ready');

            this.forceUpdate();
        }
        // ...
    }

    //event handler, for EVERY event...
    handleEvent(evtName) {
        let timeout;
        const handlerName = `on${camelize(evtName)}`;

        return (e) => {
            if (timeout) {
                clearTimeout(timeout);
                timeout = null;
            }
            timeout = setTimeout(() => {
                if (this.props[handlerName]) {
                    this.props[handlerName](this.props, this.map, e);
                }
            }, 0);
        }
    }

    renderChildren() {
        const {children} = this.props;

        if(!children) return;

        return React.Children.map(children, c => {
            return React.cloneElement(c, {
                map: this.map,
                google: this.props.google,
                mapCenter: this.state.currentLocation
            });
        })
    }

    render() {
        const style = {
            width: '100vw',
            height: '100vh'
        }
        return (
            <div ref='map' style={style}>
                Loading map...
                {this.renderChildren()}
            </div>
        )
    }
}

Map.propTypes = {
    google: PropTypes.object,
    zoom: PropTypes.number,
    initialCenter: PropTypes.object,
    centerAroundCurrentLocation: PropTypes.bool,
    onMove: PropTypes.func,
}

evtNames.forEach(e => Map.propTypes[camelize(e)] = PropTypes.func)

Map.defaultProps = {
    zoom: 13,
    // Gianmarco: I'm using Miami here, change it later
    initialCenter: {
        lat: 25.788573,
        lng: -80.193584
    },
    centerAroundCurrentLocation: false,
    onMove: function() {}
}