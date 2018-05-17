import React from 'react';
import PropTypes from 'prop-types';
import {camelize} from './lib/String';
import ReactDOMServer from 'react-dom/server';
import Logo from '../../../components/Navbar/notebook.png';
import {Link} from 'react-router-dom';

import {Button} from "../../../components/Button/Button";

export class InfoWindow extends React.Component {

    componentDidUpdate(prevProps, prevState) {
        if (this.props.map !== prevProps.map) {
            this.renderInfoWindow();
        }

        if (this.props.children !== prevProps.children) {
            this.updateContent();
        }

        if ((this.props.visible !== prevProps.visible) ||
            (this.props.marker !== prevProps.marker)) {
            this.props.visible ?
                this.openWindow() :
                this.closeWindow();
        }
    }

    openWindow() {
        this.infowindow.open(this.props.map, this.props.marker);
    }

    closeWindow() {
        this.infowindow.close();
    }

    updateContent() {
        const content = this.renderChildren();
        this.infowindow.setContent(content);
    }

    renderChildren() {
        const {children} = this.props;
        return ReactDOMServer.renderToString(<div>

            <img src={ Logo } className="logo-image"  />
            <h1> {this.props.placeName} </h1>
            {/*<p> {this.props.placePrice)} </p>*/}
            <p> Price: {this.props.placePrice} euro</p>
            <p> Area: {this.props.placeArea} mq</p>
            <p> E Class: {this.props.placeEclass} </p>
            <p> Type: {this.props.placeType} </p>

            <a className="nav-item nav-link" href="/initialSearch">
                Details
            </a>

            {/*<p>
                {this.props.place.priceTag}
            </p>
            <br/>*/}

            {/*<button
                className = "btn btn-primary pull-right"
                onClick={ this.onInfoWindowClick } >
                View details
            </button>*/}



        </div>);
    }

    renderInfoWindow() {
        let {map, google, mapCenter} = this.props;


        const iw = this.infowindow = new google.maps.InfoWindow({
            content: "",})

        google.maps.event.addListener(iw, 'closeclick', this.onClose.bind(this));
        google.maps.event.addListener(iw, 'domready', this.onOpen.bind(this));
    }

    onOpen() {
        if (this.props.onOpen) this.props.onOpen();
    }

    onClose() {
        if (this.props.onClose) this.props.onClose();
    }

/*    onInfoWindowClick() {
        console.log("Ciao Windows clisckckackaskd");
        this.props.history.push("/");

        // this.props.history.push(variable);
    }*/

    render() {
        return null;




    }
}