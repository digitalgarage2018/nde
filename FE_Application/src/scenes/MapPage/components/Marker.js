import React from 'react';
import PropTypes from 'prop-types';
import {camelize} from './lib/String';
import HouseService from "../../../services/houseService/HouseService";


const evtNames = ['click', 'mouseover'];

export class Marker extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            price:this.props.priceTag
        };

    }


    componentWillUnmount() {
        if (this.marker) {
            this.marker.setMap(null);
        }
        console.log("Ciao sto morendo");
    }

    componentDidUpdate(prevProps) {
        if ((this.props.map !== prevProps.map) ||
            (this.props.position !== prevProps.position)) {
            this.renderMarker();
        }
    }

    renderMarker() {

        let {
            map, google, position, mapCenter
        } = this.props;

        let pos = position || mapCenter;
        position = new google.maps.LatLng(pos.lat, pos.lng);

        const pref = {
            map: map,
            position: position,
        };

        this.marker = new google.maps.Marker(pref);

        evtNames.forEach(e => {
            this.marker.addListener(e, this.handleEvent(e));
        })
    }

    handleEvent(evt) {
        return (e) => {
            const evtName = `on${camelize(evt)}`
            if (this.props[evtName]) {
                this.props[evtName](this.props, this.marker, e);
            }
        }
    }


    //GIANMARCO: not interacting with virtualDOM parts, so I can return null on this and not fucking up the view
    render() {
        console.log("debug di house:"+JSON.stringify(this.props.house));
        return null;
    }
}

Marker.propTypes = {
    position: PropTypes.object,
    map: PropTypes.object
}
