import React from "react";
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import {Label} from 'react-bootstrap';
import ProfileImm from './resources/avatar.png';
import HouseService from "../../services/houseService/HouseService";
import WishlistService from "../../services/wishlistService/WishlistService";


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


  
addProducts(10);

export default class Profile extends React.Component{
    constructor(props) {

        super(props);

        this.state = {
            wishlist: []
        }



        this.wishlistService = new WishlistService();

        let callback = (results) => {
            let wishlist = results.data.response.houses;
            this.setState({wishlist: wishlist});
            console.log("appena impostato lo stato");
            console.log(this.state.wishlist);
        };

        this.wishlistService.getWishlist(callback);
    }


   render(){ 

        //let table = this.state.wishlist.map( house =>  )

      return( 
     
         
        <div style={{marginTop:"20px", minHeight:"80vh", padding:"20px"}}>    
          <div>
          <img src={ProfileImm} style={{float:'left', marginLeft: '40px', marginRight:'40px'}}/>
          <h5>
             Username: <Label> {localStorage.getItem("loggedUsername")}</Label>
          </h5>
          <h5>
            Email: <Label> {localStorage.getItem("loggedEmail")} </Label>
          </h5>
          <h5>
             My wallet: <Label> 100000 </Label>
          </h5> 
        </div>

        <div style={{padding:'40px', marginTop:"40px"}}>
        <BootstrapTable data={ this.state.wishlist }>
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