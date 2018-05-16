import React from "react";
import HouseService from "./../../services/HouseService";
import HouseComponent from "../../components/House/HouseComponent";

export default class InitialSearchPage extends React.Component{ 
   constructor(props){ 
      super(props);



        this.state = {
          city:'',
          prevCity:'Milano',
          housesList:[{houses: Array().fill(null)}]
        }

    this.houseService = new HouseService();

        /*
        pezza, chiamato da Map page per cambiare citta
         */
       if(localStorage.getItem("cittaByMap")!= null ){

           let city = localStorage.getItem("cittaByMap");
           //this.setState({prevCity: city});
           localStorage.removeItem("cittaByMap");


           let callback = (results) => {
               let houseResp = results.data.response;
               this.setState({housesList: houseResp});
               console.log(this.state.housesList);
               localStorage.setItem("houseList", JSON.stringify(houseResp));
               this.props.history.push("/map");
           };

           let callbackError = (error) => {
               localStorage.setItem("loginMessage", "Non sei loggato. Loggati!");
               this.props.history.push("/");
           };

           console.log("inizializazione richiesta effettuata");

           this.houseService.getHouses(city, callback.bind(this), callbackError.bind(this));
       }

       /*
       chiamato da MAP, per ricercare tramite filtri di ricerca
        */
       else if(localStorage.getItem("maxPrice")!= null){

           console.log("tentativo di ricerca per maxPrice")
           let maxPrice = localStorage.getItem("maxPrice");
           localStorage.removeItem("maxPrice");

           let callback = (results) => {
               let houseResp = results.data.response;
               this.setState({housesList: houseResp});
               console.log(this.state.housesList);
               //forse va prima ripulito, forse no
               localStorage.setItem("houseList", JSON.stringify(houseResp));
               this.props.history.push("/map");
           };

           let callbackError = (error) => {
               localStorage.setItem("loginMessage", "Non sei loggato. Loggati!");
               this.props.history.push("/");
           };

           let city = this.state.prevCity;
           console.log("il valore di prev city è:"+this.state.prevCity);
           if(localStorage.getItem("CittaByFilter") != null)
               city = localStorage.getItem("CittaByFilter");

           const minPrice = "0";


           console.log("inizializata richiesta per maxPrice");

           let params = {
               'city': 'Roma',
               'minPrice': '',
               'maxPrice': '',
               'minArea': '',
               'maxArea': '',
               'type': '',
               'E_class': ''
           }


           this.houseService.getHousesAleMaxPrice(
               params,
               callback.bind(this), callbackError.bind(this));



       }

    }



    changeCity(event){
         this.setState({city:event.target.value});
    }


    onSubmit(event){
        event.preventDefault();
   }

   /*
   fa il push a MAP
    */
    getHouses(){
        let city = this.state.city;
        //this.setState({prevCity: city});

        let callback = (results) => {
            let houseResp = results.data.response;
            this.setState({housesList: houseResp});
            console.log(this.state.housesList);
            localStorage.setItem("houseList", JSON.stringify(houseResp));
            this.props.history.push("/map");
        };
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
                          {this.state.houses}
                      </form>
                   </div>
              </div>
         </div>
     </div>
      ); 
   } 
}