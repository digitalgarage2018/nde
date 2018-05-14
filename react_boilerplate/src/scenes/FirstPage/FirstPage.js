import React from 'react';
import {FirstComponent} from "./components/FirstComponent";
import {contactBackend} from '../../services/firstPageService/firstPageService';
export default class FirstPage extends React.Component {
    constructor() {
        super();
        this.catchOutput = this.catchOutput.bind(this);
        
    }

    componentDidMount(){
        contactBackend();
    }
    state = {
        showOutput: ''
    }

    catchOutput(e) {
        console.log(e.target.value);
        this.setState({
            showOutput: e.target.value
        })
    }


    render() {
        return (
            <div>
                <FirstComponent output={this.catchOutput}/>
                {this.state.showOutput}
            </div>
        )
    }
}