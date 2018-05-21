import axios from "axios";

export default class WishlistService{


    getWishlist(callback) {

        const url = "http://localhost:8070/wishlist/getWishlist";

        const data=JSON.stringify({
            'jwt' :localStorage.getItem("token"),
        });

        //debug
        console.log("Alessio: pacchetto dati:"+data);

        const config={
            headers:{'Content-Type': 'application/json; charset=UTF-8'
            }
        };

        axios.post(url, data, config)
            .then(function(result){
                console.log(result);
                callback(result);
            })
            .catch(function (error) {
                console.log(error);
                //errorCallback(error);
            })

    }


    addWish() {

        const url = "http://localhost:8070/wishlist/addWish";

        const data=JSON.stringify({
            'jwt' :localStorage.getItem("token"),
            'idHouse': localStorage.getItem("specIdHouse")
        });

        //debug
        console.log("Alessio: pacchetto dati:"+data);

        const config={
            headers:{'Content-Type': 'application/json; charset=UTF-8'
            }
        };

        axios.post(url, data, config)
            .then(function(result){
                console.log(result);
                //callback(result);
            })
            .catch(function (error) {
                console.log(error);
                //errorCallback(error);
            })

    }


   login(username, password, onSuccess, onError){
       console.log(username, password);
       const data=JSON.stringify({
        'username' :username, 'password': password
       });

       const config={
           headers:{'Content-Type': 'application/json; charset=UTF-8'
           }
       };

      axios.post("http://localhost:8070/authentication/logIn", data, config).then(function(result){
      console.log(result);   
      console.log("Login effettuato con successo, token: ", result.data.response);
      if (result.data.response.success)   
        onSuccess(result.data.response);
      else onError();
      }, function(error){
         console.error(error);
      });
  }


}