import axios from "axios";

export default class LoginService{
   login(username, password, onSuccess, onError){
      axios.post("http://localhost:8070/authentication/logIn", {username:username, password:password}).then(function(result){
         console.log("Login effettuato con successo, token: ",result.data);
         onSuccess(result.data);
      }, function(error){
         console.error("Errore durante il login: ", error);
         onError(error.response.data);
      });
  }
}