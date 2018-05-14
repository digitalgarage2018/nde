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
}