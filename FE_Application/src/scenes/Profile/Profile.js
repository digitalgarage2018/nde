import React from "react";
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import {Label} from 'react-bootstrap';
import ProfileImm from './resources/avatar.png';


const products = [];
function addProducts(quantity) {
    const startId = products.length;
    for (let i = 1; i < quantity; i++) {
      const id = startId + i;
      products.push({
        id: id,
        address: 'Item name ' + id,
        type: 'Bilocale',
        price: 2100 + i
      });
    }
  }
  
addProducts(5);

export default class Profile extends React.Component{ 


   render(){ 

      return( 
     
         
        <div style={{marginTop:"20px", minHeight:"70vh", padding:"20px"}}>    
          <div>
          <img src={ProfileImm} style={{float:'left', marginLeft: '20px', marginRight:'20px'}}/>
          <h5>
             Username: <Label> my username </Label>
          </h5>
          <h5>
            Email: <Label> my email </Label>
          </h5>
          <h5>
             My wallet: <Label> 100000 </Label>
          </h5> 
        </div>

        <div style={{padding:'20px'}}>
        <BootstrapTable data={ products }>
            <TableHeaderColumn dataField='id' width='50' isKey={ true }>ID</TableHeaderColumn>
            <TableHeaderColumn dataField='address' width='150'>Address</TableHeaderColumn>
            <TableHeaderColumn dataField='type' width='150'>Type</TableHeaderColumn>
            <TableHeaderColumn dataField='price' width='150'>Price</TableHeaderColumn>
        </BootstrapTable>
        </div>
        </div>
        ); 
   } 
}