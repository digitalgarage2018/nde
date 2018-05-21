import React from "react";
import LoginService from "../../../services/loginService/LoginService";


export default class LoginComponent extends React.Component{ 
   constructor(props){ 
      super(props);

        this.state = {
          username: '',
          password: '',
          showSuccess:false,
          showError:false,
          errorMessage:"",
          successMessage:"",
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
        this.setState({
                       showSuccess:true, 
                       successMessage:"Complimenti per il login, il tuo token è: " + dataResult,
                       showError:false,
                       errorMessage:""});
            localStorage.setItem("token", dataResult.token.toString());
            localStorage.setItem("loggedUsername", dataResult.username);
            localStorage.setItem("loggedEmail", dataResult.email);

            console.log("l'username dell utente loggato è:" +localStorage.getItem("loggedUsername"));

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

   render(){ 
    let successMessage = this.getSuccessMessage();
 
    let errorMessage = this.getErrorMessage();

    return( 
        <div style={{minHeight:"30vh"}}>
            <div className = "container">
                <div className = "row">
                    <div className = "col-6 mr-auto ml-auto" style={{paddingRight:'400px',paddingLeft:'400px'}}>
                       <form onSubmit={this.onSubmit.bind(this)}>
                          <div className = "form-group" >
                             <input 
                                 type="text"
                                 className = "form-control"
                                 placeholder="username"
                                 value = {this.state.username || ''}
                                 onChange = {this.changeUsername.bind(this)}/>
                          </div>
                          <div className = "form-group" >
                             <input 
                                type="password"
                                className = "form-control"
                                placeholder="password"
                                value = {this.state.password}
                                onChange = {this.changePassword.bind(this)}/>
                          </div>
                          <button 
                              type="submit"
                              className = "btn btn-primary pull-right"
                              onClick={this.login.bind(this)}
                              
                             >
                              Log In
                          </button>
                          {localStorage.getItem("loginMessage")}
                          {localStorage.removeItem("loginMessage")}
                      </form>
                   </div>
                </div>
            </div>
        </div>
      ); 
   } 
}