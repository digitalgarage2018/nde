import React from "react";
import homeCss from "./home.css";
import LoginComponent from "./components/LoginComponent";
import SignUpComponent from "./components/SignUpComponent";

export default class Home extends React.Component{ 

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