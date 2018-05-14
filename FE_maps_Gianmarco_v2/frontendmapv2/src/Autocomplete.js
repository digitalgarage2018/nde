import React from 'react';
import styles from './autocomplete.module.css';
import ReactDOMServer from 'react-dom/server';

export class Autocomplete extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            position: null

        }
    }
    componentDidMount() {
        this.renderAutoComplete();
    }

    componentDidUpdate(prevProps) {
        if (this.props !== prevProps.map) this.renderAutoComplete();
    }

    onSubmit(e) {
        e.preventDefault();
    }

    renderAutoComplete() {
        const { google, map } = this.props;

        if (!google || !map) return;

        const autocomplete = new google.maps.places.Autocomplete(this.autocomplete);
        autocomplete.bindTo('bounds', map);

        autocomplete.addListener('place_changed', () => {
            const place = autocomplete.getPlace();

            if (!place.geometry) return;

            if (place.geometry.viewport) map.fitBounds(place.geometry.viewport);
            else {
                map.setCenter(place.geometry.location);
                map.setZoom(17);
            }

            this.setState({ position: place.geometry.location });
        });
    }

    render() {
        const { position } = this.state;

        return (
            <div className={styles.flexWrapper}>
                <div className={styles.left}>
                    <form onSubmit={this.onSubmit}>
                        <input
                            placeholder="Enter a location"
                            ref={ref => (this.autocomplete = ref)}
                            type="text"
                        />

                        <input className={styles.button} type="submit" value="Go" /> {/*on Submit I must call BE fro AXIOS and retrieve all houses from that city*/}
                    </form>

                    <div>
                        <div>Lat: {position && position.lat()}</div>
                        <div>Lng: {position && position.lng()}</div>
                    </div>
                </div>
            </div>
        )
    }

}