import React from "react";

import SignUpService from "./../../../services/SignUpService";

export default class SignUpComponent extends React.Component{ 
   constructor(props){ 
      super(props);
        this.state = {
          email:'',
          username: '',
          password: '',
        }
    this.signUpService = new SignUpService();
    }

     changeEmail(event){
        this.setState({email:event.target.value});
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

   signUpSuccess(){
       this.setState({
                      showSuccess:true, 
                      successMessage:"Complimenti per il signUp",
                      showError:false,
                      errorMessage:""});
    }
    signUpError(){
       this.setState({
                      showError:true, 
                      errorMessage:"Errore ricontrolla le credenziali",
                      showSuccess:false,
                      successMessage:""});
    }
   signUp(event){
           this.signUpService.signUp(this.state.email,
           this.state.username, 
           this.state.password, 
           this.signUpSuccess.bind(this), 
           this.signUpError.bind(this)
           );
       console.log("Sign Up con username: ", this.state.username);
       console.log("Sign Up con password: ", this.state.password);
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

    let successMessage= this.getSuccessMessage();
 
    let errorMessage= this.getErrorMessage();

      return( 
        <div style={{minHeight:"40vh"}}>
           
            <div className = "container" >
                   <div className = "row" >
                       <div className = "col-6 mr-auto ml-auto" style={{paddingRight:'400px',paddingLeft:'400px'}}>
                          <form onSubmit={this.onSubmit.bind(this)}>
                          <div className = "form-group">
                                <input 
                                    type="text"
                                    className = "form-control"
                                    placeholder="email"
                                    value = {this.state.email || ''}
                                    onChange = {this.changeEmail.bind(this)}/>
                             </div>
                             <div className = "form-group">
                                <input 
                                    type="text"
                                    className = "form-control"
                                    placeholder="username"
                                    value = {this.state.username || ''}
                                    onChange = {this.changeUsername.bind(this)}/>
                             </div>
                             <div className = "form-group">
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
                                 onClick={this.signUp.bind(this)}>
                                 Sign Up
                             </button>
                             {successMessage}
                             {errorMessage}
                         </form>
                      </div>
                 </div>
            </div>
        </div>
      ); 
   } 
}