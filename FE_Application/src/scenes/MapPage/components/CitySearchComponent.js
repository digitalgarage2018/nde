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

        this.props.change(event);

        this.cityService.getCitiesLike(event.target.value, this.showHints.bind(this), this.errorCallback.bind(this) )

        this.setState(
            {
                city:event.target.value
            }
        );
        console.log("provo service con parametro:"+event.target.value);

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
        console.log(this.props.filter.getState);
    }

    render(){
        let listItems;

        if(this.state.city != "")
            listItems = this.state.hints.map((hint) =>
            <li>{hint}</li>
        );


        return (
            <div style={{marginTop:"100px", minHeight:"0"}}>
                <div className = "container">
                    <div className = "row">
                        <div className = "col-6 mr-auto ml-auto">
                                <div className = "form-group">
                                    <input
                                        type="text"
                                        className = "form-control"
                                        placeholder="città"
                                        value = {this.state.city || ''}
                                        onChange = {this.onTextChange.bind(this)}/>
                                </div>
                                <div>
                                    {listItems}
                                </div>
                        </div>
                    </div>
                </div>
            </div>)
    }




}