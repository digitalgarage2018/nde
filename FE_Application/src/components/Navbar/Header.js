import React from "react";
import "./Header.css";
import Logo from "./notebook.png";


export default class Header extends React.Component{
    constructor(props){
        super(props);
    }
    render(){
        return(
            <div>
                <nav className="navbar fixed-top navbar-expand-lg navbar-light bg-success">
                    <a className="navbar-brand" href="/">
                        <img src={Logo} className = "logo-image"/>
                    </a>
                    <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div className="navbar-nav" style={{padding:'20px'}}>
                            <a className="nav-item nav-link" href="/profile">
                                My Profile
                            </a>
                        </div>
                        <div className="navbar-nav" style={{padding:'20px'}}>
                            <a className="nav-item nav-link" href="/initialSearch">
                                Map
                            </a>
                        </div>
                        <div className="navbar-nav" style={{padding:'20px'}}>
                            <a className="nav-item nav-link" href="/aboutUs">
                                About Us
                            </a>
                        </div>
                        <div className="navbar-nav" style={{padding:'20px'}}>
                            <a className="nav-item nav-link" href="/specPage">
                                Caratteristic House
                            </a>
                        </div>

                    </div>
                </nav>
            </div>
        );
    }
}