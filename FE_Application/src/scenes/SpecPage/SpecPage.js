import React from "react";
import {Carousel} from 'react-bootstrap';
import {Label} from 'react-bootstrap';
import HouseImm from './resources/casa.png';
import compon from './resources/compon.png';
import compon1 from './resources/compon1.png';
import {Grid, Row,Col, Thumbnail, ButtonToolbar, Button, Glyphicon} from 'react-bootstrap';
import HouseService from "../../services/houseService/HouseService";

export default class SpecPage extends React.Component{
    constructor(props) {
        super(props);


        this.state = {
            placeName: localStorage.getItem("specPlaceName")||"",
            price: localStorage.getItem("specPrice")||"",
            area: localStorage.getItem("specArea")||"",
            eClass: localStorage.getItem("specEclass")||"",
            type: localStorage.getItem("specType")||""
        };
        this.houseService = new HouseService();
    }


    render(){ 
 
        return( 
        <div style={{marginTop:"20px", marginBotton:"20px", minHeight:"80vh", padding:"20px"}}>    
          <div style={{float:'left', marginLeft:"50 px"}}>

           <div style={{padding:"20px"}}>
           <ButtonToolbar>
                 <Button onClick={this.handleClick} bsSize="large">
                  <Glyphicon glyph="star" /> Like It!!
                 </Button>
            </ButtonToolbar>
           </div>

            <div style={{margin:"20px"}}>
            <h4>
              Address: <Label> {this.state.placeName} </Label>
            </h4>
            <h4>
              Type: <Label> {this.state.type}  </Label>
            </h4>
            <h4>
              E_Class: <Label> {this.state.eClass}  </Label>
            </h4> 
            <h4>
              Price: <Label> {this.state.price}  </Label>
            </h4>
            </div>
            </div>

            <div style={{float:'left', padding:"20px", marginLeft:'50px'}}>
            <Carousel>
            <Carousel.Item>
            <img width={500} height={200} src={compon}/>
            </Carousel.Item>
             <Carousel.Item>
            <img width={500} height={200} src={compon1} />
            </Carousel.Item>
            </Carousel>
            </div>
            
             <div style={{float:'left', padding:"20px"}}>
              <p>DESCRIZIONE:</p>
              
             </div>
        </div>


        );
    }
}