import React from "react";
import headerCss from "../../../css/mainLayout/header/header.css";
import Logo from "../../../images/notebook.png";
export default class Header extends React.Component{ 
   constructor(props){ 
      super(props); 
   }
   render(){ 
      return( 
        <div>
        <nav className="navbar fixed-top navbar-expand-lg navbar-light bg-success">
           <a className="navbar-brand" href="#">
              <img src={Logo} className = "logo-image"/>
           </a>
           <button
              className="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#navbarNavAltMarkup"
              aria-controls="navbarNavAltMarkup"
              aria-expanded="false"
              aria-label="Toggle navigation">
           <span className="navbar-toggler-icon"></span>
           </button>
           <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
               <div className = "navbar-nav mr-auto">
                 <a className="nav-item nav-link" href="/signUp">Sign Up</a>
               </div>
              <div className="navbar-nav">
                 <a className="nav-item nav-link" href="/Profile">
                    Profile
                 </a>
              </div>
              <div className="navbar-nav">
                 <a className="nav-item nav-link" href="/Map">
                    Map
                 </a>
              </div>
              
           </div>
        </nav>
     </div>
      ); 
   }
}