import React, {Component} from 'react';
import {AppRouter} from "./AppRouter";
import Header from "./components/Navbar/Header";
import Footer from "./components/Footer/Footer";


class App extends Component {


    render() {
        return (
            <div>
                <Header/>
                <AppRouter/>
                <Footer />
            </div>

        );
    }
}

export default App;
