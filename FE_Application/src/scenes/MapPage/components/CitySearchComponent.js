import React, { Component } from 'react'
import CityService from "../../../services/cityService/CityService";
import {Button} from "../../../components/Button/Button";



export default class CitySearchComponent extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            city:"",
            hints:[]
        };

        this.cityService=new CityService();
    }


    onTextChange(event){

        this.cityService.getCitiesLike(event.target.value, this.showHints.bind(this), this.errorCallback.bind(this) )

        this.setState(
            {
                city:event.target.value
            }
        );
        console.log("provo service con parametro:"+event.target.value);

        localStorage.setItem('currentCity',event.target.value);

    }

    showHints(hints){
        this.setState(
            {
                hints:hints
            }
        )
    }

    errorCallback(){
        console.log("Errore");
    }


    search(){


        //tipologia di richiesta
        localStorage.setItem("requestType",'searchByFilter');

        this.props.history.push("/initialSearch");
    }

    render(){
        let listItems;

        if(this.state.city != "")
            listItems = this.state.hints.map((hint) =>
                <li>{hint}</li>
            );


        return (
            <div
            className = "row"
        >

                <div className = "col-sm-8">

            <input
                className = "form-control"
                type="text"
                placeholder="città"
                onChange={this.onTextChange.bind(this)

            }/>

            <div>
                {/* {
                    this.state.hints.map( cityName =>
                            {cityName}
                    )
                }*/}
                {listItems}
            </div>

                </div>

                <div className = "col-sm-4">

            <button
                className = "btn btn-primary"
                style={{float:"left"}}
                onClick={this.search.bind(this)} >
                Cerca
            </button>

                </div>

        </div>)
    }




}