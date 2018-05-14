import React, { Component } from 'react';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import MainTemplate from "./components/mainLayout/template/mainTemplate";
import Home from "./components/home/home";
import Profile from "./components/profile/profile";
import Map from "./components/map/map";
import SignUp from "./components/signUp/signUp";
import TableSort from "./components/tableSort/tablesort";

class App extends Component {
  constructor(props){
    super(props);
 }
 
  render() {
   
    return (
        
      <BrowserRouter>
        <MainTemplate>
            <Switch>
                <Route exact path='/' component={Home}/>
                <Route exact path='/Profile' component={Profile}/>
                <Route exact path='/Map' component={Map}/>
                <Route exact path='/SignUp' component={SignUp}/>
                <Route exact path='/TableSort' component={TableSort}/>
            </Switch>
        </MainTemplate>
      </BrowserRouter>
    );
  }
}

export default App;
