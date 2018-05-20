import React from "react";
import {Carousel} from 'react-bootstrap';
import {Label} from 'react-bootstrap';
import HouseImm from './resources/casa.png';
import compon from './resources/compon.png';
import compon1 from './resources/compon1.png';
import {Grid, Row,Col, Thumbnail, ButtonToolbar, Button, Glyphicon} from 'react-bootstrap';

export default class SpecPage extends React.Component{
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
              Address: <Label> via Sismondi, 7 </Label>
            </h4>
            <h4>
              City: <Label> Milano </Label>
            </h4>
            <h4>
              Type: <Label> Loft </Label>
            </h4>
            <h4>
              E_Class: <Label> C </Label>
            </h4> 
            <h4>
              Price: <Label> 100.000 </Label>
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