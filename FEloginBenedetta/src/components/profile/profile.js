import React from "react";


export default class Profile extends React.Component{
    constructor(props){
        super(props);
    }

render(){
    return(
        <div style={{marginTop:"100px"}}>
        <p>
           Sono la pagina profilo!
        </p>  
        </div>
    );
    }
}