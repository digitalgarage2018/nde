import axios from "axios";

export default class LoginService{
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
        onSuccess(result.data.response.token);
      else onError();
      }, function(error){
         console.error(error);
      });
  }
}