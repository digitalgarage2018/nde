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
            range: localStorage.getItem("mapSearchRange")||5,
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
        localStorage.setItem("filterType",selectedOption.label);
    }
    handleClassChange = (selectedOption) => {
        this.setState({ E_class: selectedOption.label });
        console.log(`Selected: ${selectedOption.label}`);
        localStorage.setItem("filterEclass",selectedOption.label);
    }

   /* handleOnClickButton = () => {
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
    }*/

    handleDistanceSlider(target){
        this.setState({ range: target });
        console.log(target);
        localStorage.setItem("mapSearchRange",this.state.range.toString());
        localStorage.setItem("filterRange",this.state.range.toString());
    }

    handleAreaChange(target){
        this.setState({ area: target });
        localStorage.setItem("filterMaxArea",this.state.area.max.toString());
        localStorage.setItem("filterMinArea",this.state.area.min.toString());
    }

    handlePriceChange(target){
        this.setState({ price: target });
        localStorage.setItem("filterMaxPrice",this.state.price.max.toString());
        localStorage.setItem("filterMinPrice",this.state.price.min.toString());
    }


    render() {

        return (

            <div style={{margin:60}}>

                <div className='slider'>

                    <p>Km</p>
                    <InputRange
                        maxValue={20}
                        minValue={0}
                        value={this.state.range}
                        onChange={this.handleDistanceSlider.bind(this)}
                    />

                    <p>Mq</p>
                    <InputRange
                        maxValue={500}
                        minValue={0}
                        value={this.state.area}
                        onChange={this.handleAreaChange.bind(this)} />

                    <p>Prezzo</p>
                    <InputRange
                        maxValue={1000000}
                        minValue={0}
                        value={this.state.price}
                        onChange={this.handlePriceChange.bind(this)}
                    />
                </div>




                    <Select
                        name="E_Class"
                        value={this.state.E_class}
                        onChange={this.handleClassChange.bind(this)}
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



                    <Select
                        name="Type"
                        value={this.state.type}
                        onChange={this.handleTypeChange.bind(this)}
                        options={[
                            { value: 'Bilocale', label: 'Bilocale' },
                            { value: 'Trilocale', label: 'Trilocale' },
                            { value: 'Quadrilocale', label: 'Quadrilocale' },
                            { value: 'Plurilocale', label: 'Plurilocale' },
                            { value: 'Loft', label: 'Loft' },
                        ]}
                    />



                {/*<button className='button' onClick={this.handleOnClickButton}>CERCA</button>*/}


            </div>


        );
    }
}