import React from "react";
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';

const products = [];

function addProducts(quantity) {
  const startId = products.length;
  for (let i = 0; i < quantity; i++) {
    const id = startId + i;
    products.push({
      id: id,
      name: 'Item name ' + id,
      price: 2100 + i
    });
  }
}
addProducts(10);



export default class ExternalSort extends React.Component {
    constructor(props) {
      super(props);
  
      this.state = {
        sortName: undefined,
        sortOrder: undefined
      };
      this.onSortChange = this.onSortChange.bind(this);
    }
  
    onSortChange(sortName, sortOrder) {
      console.info('onSortChange', arguments);
      this.setState({
        sortName,
        sortOrder
      });
    }
  
    render() {
      const options = {
        sortName: this.state.sortName,
        sortOrder: this.state.sortOrder,
        onSortChange: this.onSortChange
      };
      return (
          
        <div style={{marginTop:"100px", minHeight:"70vh"}}>
           <p>puoi ordinare Price e ID</p>
         
          <BootstrapTable data={ products } options={ options }>
            <TableHeaderColumn dataField='id' isKey dataSort>Product ID</TableHeaderColumn>
            <TableHeaderColumn dataField='name'>Address</TableHeaderColumn>
            <TableHeaderColumn dataField='price' dataSort>Price</TableHeaderColumn>
          </BootstrapTable>
        </div>
        
      );
    }
  }