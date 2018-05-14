import React from "react";
import HouseService from "./../../services/HouseService";

export default class MapPage extends React.Component{ 
   constructor(props){ 
      super(props);
        this.state = {
          city:'',
          houses:[]
        }
    this.houseService = new HouseService();
    }

    changeCity(event){
         this.setState({city:event.target.value});
    }


    onSubmit(event){
        event.preventDefault();
   }

    getHouses(){
        let city = this.state.city;
        let callback = (results) => {this.setState({houses:results.data.response})};
        let callbackError = (error) => {
            localStorage.setItem("loginMessage", "Non sei loggato. Loggati!");
            this.props.history.push("/");
        }; 
        console.log("inizializazione richiesta");
        this.houseService.getHouses(city, callback.bind(this), callbackError.bind(this));
    }   

   render(){ 

      return( 
        <div style={{marginTop:"100px", minHeight:"70vh"}}>
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
                          {this.state.houses[0]}
                      </form>
                   </div>
              </div>
         </div>
     </div>
      ); 
   } 
}