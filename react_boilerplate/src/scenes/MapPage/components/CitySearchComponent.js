import React, { Component } from 'react'
import CityService from "../../../services/CityService";



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

    render(){
        let listItems;

        if(this.state.city != "")
            listItems = this.state.hints.map((hint) =>
            <li>{hint}</li>
        );


        return (<div>

            <input type="text" onChange={this.onTextChange.bind(this)}/>

            <div>
               {/* {
                    this.state.hints.map( cityName =>

                            {cityName}



                    )
                }*/}
                {listItems}


            </div>
        </div>)
    }




}