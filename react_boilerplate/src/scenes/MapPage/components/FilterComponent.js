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
  
      this.state = {
        value: 5,
        value2: {
            min: 45,
            max: 100,
          },
        value3: {
            min: 30000,
            max: 400000,
          },
        typeValue: '',
        classValue: '',
      };
    }

    prova(){
      console.log(this.state)
    }

    handleTypeChange = (selectedOption) => {
      this.setState({ typeValue: selectedOption });
      console.log(`Selected: ${selectedOption.label}`);
    }
    handleClassChange = (selectedOption) => {
      this.setState({ classValue: selectedOption });
      console.log(`Selected: ${selectedOption.label}`);
    }

    render() { 
      
    return (
   
      <div style={{marginTop:"100px", minHeight:"20vh", paddingLeft:"50px", paddingRight:"50px"}}>
        
        <div className='slider'>

          <p>Km</p>  
          <InputRange
          maxValue={20}
          minValue={0}
          value={this.state.value}
          onChange={value => this.setState({ value })}
          onChangeComplete={value => console.log(value)} />
          
          <p>Mq</p>
          <InputRange
            maxValue={500}
            minValue={0}
            value={this.state.value2}
            onChange={value2 => this.setState({ value2 })} />
          
          <p>Prezzo</p>
          <InputRange
            maxValue={1000000}
            minValue={0}
            value={this.state.value3}
            onChange={value3 => this.setState({ value3 })} />
        </div>

      <button className='button' onClick={this.prova.bind(this)}>PROVA</button> 

      <div style={{marginTop:"10px", paddingRight:"800px"}}>  
        <Select
          name="E_Class"
          value={this.state.classValue}
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
          value={this.state.typeValue}
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
    </div>
      
        
    );
    }
}

