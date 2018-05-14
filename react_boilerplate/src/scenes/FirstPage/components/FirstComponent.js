import React from 'react';
import {Button} from "../../../components/Button/Button";
import {Input} from "../../../components/Input/Input";

export const FirstComponent = (props) => {

    const onClick = () => {
        console.log(this);
    };

    const outputToFather = (e) => {
        props.output(e);
    };

    return (
        <div>
            <Button title='example' customOnClick={() => onClick()}/>
            <Input type='text' customOnChange={(e) => outputToFather(e)}/>
        </div>
    )
};