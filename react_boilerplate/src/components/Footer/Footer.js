import React from "react";
import footerCss from  "../css/footer.css";

export default class Footer extends React.Component{ 
   constructor(props){ 
      super(props); 
   }

   render(){ 
      return( 
        <div className = "container-fluid footer">
        <div className = "row" >
           <div className = "col mr-auto" style={{textAlign:'center'}}>
              <p>WebImmobiliare.it</p>
              <p>Via Larga, 1</p>
              <p>Milano, Italia</p>
              <p>webimmobiliare@gmail.com</p>  
           </div>       
        </div>
     </div>
      ); 
   }
}