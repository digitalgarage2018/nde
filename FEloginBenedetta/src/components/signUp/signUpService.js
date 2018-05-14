import axios from "axios";

export default class SignUpService{
    signUp(email, username, password, onSuccess, onError){
        console.log(email, username, password);
        const data=JSON.stringify({
         'username' :username, 'password': password, 'email':email
        });
 
        const config={
            headers:{'Content-Type': 'application/json; charset=UTF-8'
            }
        };
 
       axios.post("http://localhost:8070/authentication/signUpJ", data, config).then(function(result){
       console.log(result);   
       if (result.data.response)   
         onSuccess();
       else onError();
       }, function(error){
          console.error(error);
       });
   }
 }