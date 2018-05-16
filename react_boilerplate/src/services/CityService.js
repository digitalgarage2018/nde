import axios from "axios";

export default class CityService{


    getCitiesLike(city, callback, errorCallback){

        const url = "http://localhost:8071/city/findByName";

        console.log(city, localStorage.getItem("token"));

        axios.get(url, {
            params: {
                'name': city,
            },
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
                "Access-Control-Allow-Origin": "*",
            }
        })
            .then(function (response) {
                console.log(response);
                let names = [];

                for(let i=0; i < response.data.response.length; i++)
                    names.push(response.data.response[i].name);

                callback(names);
            })
            .catch(function (error) {
                console.log(error);
                errorCallback(error);
            });
    }

}