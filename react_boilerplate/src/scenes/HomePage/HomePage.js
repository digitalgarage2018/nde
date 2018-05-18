import React from "react";
import homeCss from "./home.css";
import LoginComponent from "./components/LoginComponent";
import SignUpComponent from "./components/SignUpComponent";

export default class Home extends React.Component{ 
<<<<<<< HEAD
=======
   constructor(props){ 
      super(props);
        this.state = {
          username: '',
          password: '',
          showSuccess:false,
          showError:false,
          errorMessage:"",
          successMessage:""
        }
    this.loginService = new LoginService();
    }

    changeUsername(event){
         this.setState({username:event.target.value});
    }

    changePassword(event){
         this.setState({password:event.target.value});
    }

    onSubmit(event){
         event.preventDefault();
    }

    loginSuccess(dataResult){

       //flush del local storage
        localStorage.clear();

        this.setState({
                       showSuccess:true, 
                       successMessage:"Complimenti per il login, il tuo token è: " + dataResult,
                       showError:false,
                       errorMessage:""});
            localStorage.setItem("token", dataResult);
            this.props.history.push("/initialSearch");
        }

     loginError(){
        this.setState({
                       showError:true, 
                       errorMessage:"Errore durante il login: ",
                       showSuccess:false,
                       successMessage:""});
     }

    login(event){
        this.loginService.login(this.state.username, 
            this.state.password, 
            this.loginSuccess.bind(this), 
            this.loginError.bind(this)
            );
        console.log("Login con username: ", this.state.username);
        console.log("Login con password: ", this.state.password);
    }   

    getSuccessMessage(){
        if(this.state.showSuccess){
           return (
      
              <div style={{color:"green"}}>
                 {this.state.successMessage}
              </div>
      
           );
        }else{
          return (
      
              <div></div>
      
          );
        }
     }
     getErrorMessage(){
        if(this.state.showError){
            return (
      
              <div style={{color:"red"}}>
                  {this.state.errorMessage}
              </div>
      
            );
        }else{
           return (
      
              <div></div>
      
           );
        }
     }
>>>>>>> fe7c22726a2f8d436ccc3b2ccb19d4781f0d20ae

   render(){ 

      return( 
        <div style={{marginTop:"40px", minHeight:"60vh"}}>
            <div className = "container">
               <LoginComponent/>
               <SignUpComponent/>
            </div>
        </div>
      ); 
   } 
}