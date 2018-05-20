import React from "react";
import HouseService from "../../services/houseService/HouseService";
import HouseComponent from "../../components/House/HouseComponent";

export default class InitialSearchPage extends React.Component{ 
   constructor(props){ 
      super(props);

        this.state = {
          city:'',
          prevCity:'',
          housesList:[{houses: Array().fill(null)}]
        }

    this.houseService = new HouseService();

        /*
        pezza, chiamato da Map page per cambiare citta
         */
       if(localStorage.getItem("requestType") === 'searchByCityName'){
           localStorage.removeItem("requestType");

           console.log("ALESSIO: richiesta searchByCityName");

           let city = localStorage.getItem("currentCity");

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

       CONDIZIONE: tipo richiesta, poi la si cancella subito dal local storage
        */
       else if(localStorage.getItem("requestType") === 'searchByFilter'){
           localStorage.removeItem("requestType");

           console.log("ALESSIO: richiesta searchByFilter");

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

           //let city = this.state.prevCity;
           //console.log("il valore di prev city è:"+this.state.prevCity);
           //if(localStorage.getItem("CittaByFilter") != null)
           //    city = localStorage.getItem("CittaByFilter");

           console.log("inizializzazione richiesta, prevCity:"+this.state.prevCity+", city:"+this.state.city);

           let params = {
               'city': localStorage.getItem('currentCity')||'Milano',
               'minPrice': localStorage.getItem("filterMinPrice")||'',
               'maxPrice': localStorage.getItem("filterMaxPrice")||'',
               'minArea': localStorage.getItem("filterMinArea")||'',
               'maxArea': localStorage.getItem("filterMaxArea")||'',
               'type': localStorage.getItem("filterType")||'',
               'E_class': localStorage.getItem("filterEclass")||''
           }


           this.houseService.getHousesAleMaxPrice(
               params,
               callback.bind(this), callbackError.bind(this));

       }
       else if(localStorage.getItem("requestType") === 'searchByMap') {
           localStorage.removeItem("requestType");

           console.log("GIAN: richiesta searchByFilter");

           let callback = (results) => {
               let houseResp = results.data.response;
               this.setState({housesList: houseResp});
               console.log(this.state.housesList);
               //forse va prima ripulito, forse no
               localStorage.setItem("houseList", JSON.stringify(houseResp));
               this.props.history.push("/map");
           };

           //GIAN: custom message?
           let callbackError = (error) => {
               localStorage.setItem("loginMessage", "Non sei loggato. Loggati!");
               this.props.history.push("/");
           };

           let params = {
               'jwt': localStorage.getItem("token"),
               'range': localStorage.getItem("mapSearchRange"),
               'latitude': localStorage.getItem("mapSearchLat"),
               'longitude': localStorage.getItem("mapSearchLng")
           }
           localStorage.removeItem("mapSearchLat");
           localStorage.removeItem("mapSearchLng");

           this.houseService.getHousesByCoords(params, callback.bind(this), callbackError.bind(this));

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
    getHousesByCityName(){
        let city = this.state.city;
        this.setState({prevCity: city});
        localStorage.setItem("currentCity",this.state.city);

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
        <div style={{paddingTop:"100px", minHeight:"80vh"}}>
            <div className = "container">
                <div className = "row">
                    <p style={{textAlign:"center", padding:"10px"}}><h4> What is your dream city?</h4></p>
                    <div className = "col-6 mr-auto ml-auto" style={{marginRight:"150px", marginLeft:"150px"}}>
                       <form onSubmit = {this.onSubmit.bind(this)}>
                          <div className = "form-group" >
                             <input 
                                 type="text"
                                 className = "form-control"
                                 placeholder="città"
                                 value = {this.state.city || ''}
                                 onChange = {this.changeCity.bind(this)}/>
                          </div>
                          <button 
                              className = "btn btn-primary pull-right"
                              onClick={this.getHousesByCityName.bind(this)}>
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