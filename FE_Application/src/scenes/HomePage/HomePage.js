import React from "react";
import "./HomePage.css";
import LoginComponent from "./components/LoginComponent";
import SignUpComponent from "./components/SignUpComponent";

export default class Home extends React.Component{

    render(){

        return(
            <div style={{marginTop:"40px", minHeight:"60vh"}}>
                <div className = "container">
                    <LoginComponent history={this.props.history}/>
                    <SignUpComponent/>
                </div>
            </div>
        );
    }
}