import React, { Component } from 'react';

import MapContainer from './MapContainer';

import styles from './researchBar.css';
import {Marker} from "google-maps-react";

class Contents extends Component {
    state = {
        position: null
    };

    componentDidMount() {
        this.renderAutoComplete();
    }

    componentDidUpdate(prevProps) {
        if (this.props !== prevProps.map) this.renderAutoComplete();
    }

    onSubmit(e) {
        e.preventDefault();
    }

    renderAutoComplete() {
        const { google, map } = this.props;

        if (!google || !map) return;

        const autocomplete = new google.maps.places.Autocomplete(this.autocomplete);
        autocomplete.bindTo('bounds', map);

        autocomplete.addListener('place_changed', () => {
            const place = autocomplete.getPlace();

            if (!place.geometry) return;

            if (place.geometry.viewport)
                map.fitBounds(place.geometry.viewport);
            else {
                map.setCenter(place.geometry.location);
                map.setZoom(17);
            }

            this.setState({ position: place.geometry.location });
        });
    }

    render() {
        const { position } = this.state;

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
                    <MapContainer
                        {...this.props}
                        center={position}
                        centerAroundCurrentLocation={false}
                        containerStyle={{
                            height: '100vh',
                            position: 'relative',
                            width: '100%'
                        }}>
                        <Marker position={position} />
                    </MapContainer>
                </div>
            </div>
        );
    }
}

const MapWrapper = props => (
    <MapContainer className="map" google={props.google} visible={false}>
        <Contents {...props} />
    </MapContainer>
);

export default MapWrapper;