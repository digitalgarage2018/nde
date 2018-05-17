import React, { Component } from 'react'
import ReactDOM from 'react-dom';
import InputRange from 'react-input-range';
import sliderCss from 'react-input-range/lib/css/index.css';
import sliderCss2 from './slider.css';
import Select from 'react-select';
import 'react-select/dist/react-select.css';

export default class Filter extends React.Component {

    constructor(props) {
      super(props);

      /*
      utilizzo i local Storage per mantenere i valori precedenti
       */
      this.state = {
        range: 5,
        price: {
            min: localStorage.getItem("filterMinPrice")||30000,
            max: localStorage.getItem("filterMaxPrice")||400000,
          },
        area: {
            min: localStorage.getItem("filterMinArea")||45,
            max: localStorage.getItem("filterMaxArea")||100,
          },
        type: localStorage.getItem("filterType")||'',
        E_class: localStorage.getItem("filterEclass")||'',
      };
    }

    prova(){
      console.log(this.state)
    }

    handleTypeChange = (selectedOption) => {
      this.setState({ type: selectedOption.label });
      console.log(`Selected: ${selectedOption.label}`);
    }
    handleClassChange = (selectedOption) => {
      this.setState({ E_class: selectedOption.label });
      console.log(`Selected: ${selectedOption.label}`);
    }

    handleOnClickButton = () => {
        localStorage.setItem("filterRange",this.state.range.toString());
        localStorage.setItem("filterMaxPrice",this.state.price.max.toString());
        localStorage.setItem("filterMinPrice",this.state.price.min.toString());
        localStorage.setItem("filterMaxArea",this.state.area.max.toString());
        localStorage.setItem("filterMinArea",this.state.area.min.toString());
        localStorage.setItem("filterType",this.state.type.toString());
        localStorage.setItem("filterEclass",this.state.E_class.toString());

        //tipologia di richiesta
        localStorage.setItem("requestType",'searchByFilter');

        //chiamata a handler di MapPage, eseguirà l'indirizzamento
        this.props.handler();
    }

    render() { 
      
    return (
   
      <div style={{marginTop:"100px", minHeight:"20vh", paddingLeft:"50px", paddingRight:"50px"}}>
        
        <div className='slider'>

          <p>Km</p>  
          <InputRange
          maxValue={20}
          minValue={0}
          value={this.state.range}
          onChange={range => this.setState({ range })}
          onChangeComplete={range => console.log(range)}
          />
          
          <p>Mq</p>
          <InputRange
            maxValue={500}
            minValue={0}
            value={this.state.area}
            onChange={area => this.setState({ area })} />
          
          <p>Prezzo</p>
          <InputRange
            maxValue={1000000}
            minValue={0}
            value={this.state.price}
            onChange={price => this.setState({ price })}
          />
        </div>



          <div style={{marginTop:"10px", paddingRight:"800px"}}>
        <Select
          name="E_Class"
          value={this.state.E_class}
          onChange={this.handleClassChange}
          options={[
            { value: 'A', label: 'A' },
            { value: 'B', label: 'B' },
            { value: 'C', label: 'C' },
            { value: 'D', label: 'D' },
            { value: 'E', label: 'E' },
            { value: 'F', label: 'F' },
            { value: 'G', label: 'G' },
          ]}
          />
      </div>
        
      <div style={{marginTop:"10px", paddingRight:"800px", minHeight:"20vh"}}>  
        <Select
          name="Type"
          value={this.state.type}
          onChange={this.handleTypeChange}
          options={[
            { value: 'Bilocale', label: 'Bilocale' },
            { value: 'Trilocale', label: 'Trilocale' },
            { value: 'Quadrilocale', label: 'Quadrilocale' },
            { value: 'Plurilocale', label: 'Plurilocale' },
            { value: 'Loft', label: 'Loft' },
          ]}
          />
      </div>


          <button className='button' onClick={this.handleOnClickButton}>CERCA</button>


    </div>
      
        
    );
    }
}

