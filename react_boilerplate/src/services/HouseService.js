import axios from "axios";

export default class HouseService{
    
    getHouses(city, callback, errorCallback){

      const url = "http://localhost:8071/house/findByCityName";

      console.log(city, localStorage.getItem("token"));

      axios.get(url, {
        params: {
          'cityName': city,
          'jwt': localStorage.getItem("token")
        },
        headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            "Access-Control-Allow-Origin": "*",
        }
      })
      .then(function (response) {
        console.log(response);
        callback(response);
      })
      .catch(function (error) {
        console.log(error);
        errorCallback(error);
      });
  }


    //per ora cablato con city Milano
    getHousesAleMaxPrice(callback, errorCallback){

        const url = "http://localhost:8071/house/findByFilterParametersAndCityName";
        const city = "Milano";//MilanoCablato

        console.log(city, localStorage.getItem("token"));

        /*
        serve invece una POSt!!
         */

        const data=JSON.stringify({
            'jwt' :localStorage.getItem("token"),
            'city': city,
            'maxPrice':'100000',
            'minPrice': '0'
        });

        const config={
            headers:{'Content-Type': 'application/json; charset=UTF-8'
            }
        };

        axios.post("http://localhost:8071/house/findByFilterParametersAndCityName", data, config)
            .then(function(result){
            console.log(result);
            callback(result);
        })
            .catch(function (error) {
            console.log(error);
            //errorCallback(error);
        })

       /* axios.get(url, {
            params: {
                'cityName': city,
                'jwt': localStorage.getItem("token"),
                'maxPrice': localStorage.getItem("maxPrice")
            },
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
                "Access-Control-Allow-Origin": "*",
            }
        })
            .then(function (response) {
                console.log(response);
                callback(response);
            })
            .catch(function (error) {
                console.log(error);
                errorCallback(error);
            });*/
    }

}