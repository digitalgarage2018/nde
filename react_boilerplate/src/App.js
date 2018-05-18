import React, {Component} from 'react';
import {AppRouter} from "./AppRouter";
import Header from "./components/Navbar/Header";
import Footer from "./components/Footer/Footer";


class App extends Component {
    constructor(){
        super();
        localStorage.setItem("user", "");
    }

    render() {
        return (
            <div>
                {/*{localStorage.getItem("user") != "" && <Header/>} */}
                <Header/>
                <AppRouter/>
                <Footer/>
            </div>

        );
    }
}

export default App;
