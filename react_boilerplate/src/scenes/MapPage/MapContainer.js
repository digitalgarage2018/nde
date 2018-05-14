import React from 'react';
import {Map} from './components/Map';
import {Marker} from './components/Marker';
import {InfoWindow} from './components/InfoWindow';
import { GoogleApiWrapper } from 'google-maps-react';
import HouseService from "./../../services/HouseService";
import InitialSearchPage from "../InitialSearchPage/InitialSearchPage";
//import {LocationSearchInput} from './LocationSearchInput';

export class MapContainer extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            showingInfoWindow: false,
            activeMarker: {},
            selectedPlace: {},
            houseList: JSON.parse(localStorage.getItem("houseList")),
            city: ''
        };
        this.houseService = new HouseService();
    }

    onMapClick = () =>
        this.setState({
            showingInfoWindow: false,
            activeMarker: null
        });

    onSubmit(event){
        event.preventDefault();
    }

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

    changeCity(event){
        this.setState({city:event.target.value});
        console.log(this.state.city);
    };

    getHouses(){
        let city = this.state.city;
        let callback = (results) => {
            let houseResp = results.data.response;
            this.setState({houseList: houseResp});
            console.log(this.state.houseList);
            console.log("Siamo nella callback");
        };
        let callbackError = (error) => {
            localStorage.setItem("loginMessage", "Non sei loggato. Loggati!");
            this.props.history.push("/");
        };
        console.log("inizializazione richiesta");
        this.houseService.getHouses(city, callback.bind(this), callbackError.bind(this));

    };

    render() {
        const style = {
            width: '100vw',
            height: '100vh'
        }

        const pos = {lat: 25.783318, lng: -80.134017} //i.e. Miami Beach

        // const pos2 = {lat: (this.state.houseList[5].latitude), lng: (this.state.houseList[5].longitude)};
        //console.log(pos2);

        let markers = [];


        /*
        GIANMARCO: I must write down the markers in the return for the Container rendering, in order to let them be seen on map.
        That's a strange behaviour since I must be able to retrieve that from Axios and then create a Marker.

         {this.state.houseList.map(house => <Marker onClick={this.onMarkerClick} name={''} position={} />)}*/

        return (

            <div style={style}>
                <div style={{marginTop:"100px", minHeight:"0"}}>
                    <div className = "container">
                        <div className = "row">
                            <div className = "col-6 mr-auto ml-auto">
                                <form onSubmit = {this.onSubmit.bind(this)}>
                                    <div className = "form-group">
                                        <input
                                            type="text"
                                            className = "form-control"
                                            placeholder="città"
                                            value = {this.state.city || ''}
                                            onChange = {this.changeCity.bind(this)}/>
                                    </div>
                                    <button
                                        className = "btn btn-primary pull-right"
                                        onClick={this.getHouses.bind(this)}>
                                        Cerca
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <br/>
                <Map google={this.props.google} houseList={this.state.houseList} onClick={this.onMapClick}>

                    {this.state.houseList.map(house => <Marker onClick={this.onMarkerClick} name={house.address} priceTag={house.price} position={{lat: house.latitude, lng: house.longitude}} />)}
                    {/*<Marker position={{lat: this.state.houseList[0].latitude, lng: this.state.houseList[0].longitude}}/>
                    <Marker/>
                    <Marker/>*/}
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
})(MapContainer)