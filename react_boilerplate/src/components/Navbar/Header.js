import React from "react";
import HeaderCss from "./Header.css";
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
<<<<<<< HEAD
              <div className="navbar-nav" style={{padding:'20px'}}>
                 <a className="nav-item nav-link" href="/profile">
                    My Profile
=======
               <div className = "navbar-nav mr-auto">
                 <a className="nav-item nav-link" href="/signUp">Sign Up</a>
               </div>
               <div className = "navbar-nav mr-auto">
                   <a className="nav-item nav-link" href="/">Log In</a>
               </div>
              <div className="navbar-nav">
                 <a className="nav-item nav-link" href="/Profile">
                    Profile
>>>>>>> fe7c22726a2f8d436ccc3b2ccb19d4781f0d20ae
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
                 <a className="nav-item nav-link" href="/caratteristicHouse">
                    Caratteristic House
                 </a>
              </div>
              
           </div>
        </nav>
     </div>
      ); 
   }
}