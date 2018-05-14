import React from 'react';
import PlacesAutocomplete, { geocodeByAddress, getLatLng } from 'react-places-autocomplete';

export class SearchBar extends React.Component {
    constructor(props) {
        super(props);
        this.state = {address: ''}
    }

    render() {
        return (
            <PlacesAutocomplete
                value={this.state.address}
                /*onChange={this.handleChange}*/
                onSelect={this.handleSelect}
            >
                {({ getInputProps, suggestions, getSuggestionItemProps }) => (
                    <div>
                        <input
                            {...getInputProps({
                                placeholder: 'Search Places ...',
                                className: 'location-search-input'
                            })}
                        />
                        {/*<div className="autocomplete-dropdown-container">
                            {suggestions.map(suggestion => {
                                const className = suggestion.active ? 'suggestion-item--active' : 'suggestion-item';
                                // inline style for demonstration purpose
                                const style = suggestion.active
                                    ? { backgroundColor: '#fafafa', cursor: 'pointer' }
                                    : { backgroundColor: '#ffffff', cursor: 'pointer' };
                                return (
                                    <div {...getSuggestionItemProps(suggestion, { className, style })}>
                                        <span>{suggestion.description}</span>
                                    </div>
                                )
                            })}
                        </div>*/}
                    </div>
                )}
            </PlacesAutocomplete>
        );
    }

}