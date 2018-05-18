import React from "react";
import {Carousel} from 'react-bootstrap';
import Imm1 from './bg-1.png';
import Imm2 from './bg-2.png';

export default class AboutUs extends React.Component{ 


    render(){ 
 
       return( 
        <div style={{marginTop:"20px", minHeight:"75vh", padding:"40px"}}>
        <div style={{float:'left', marginLeft:"50 px"}}>
        <Carousel>
            <Carousel.Item>
            <img width={600} height={100} src={Imm1}/>
            </Carousel.Item>
             <Carousel.Item>
            <img width={600} height={100} src={Imm2} />
            </Carousel.Item>
        </Carousel>
        </div>
        <div style={{ float: 'left', marginLeft:'50px'}}>
        <p> Thanks to many years of experience, professionalism, seriousness, transparency and availability, the real estate agent WebImmobiliare will accompany you to the best choice, through all the bureaucratic process and resolving any doubts on the sale and lease. </p>
        <p> WebImmobiliare accompanies "buyer" the buyer or tenant to visit the properties that are close to his tastes and / or economic possibilities, agreeing days and times. </p>
        <p>WebImmobiliare works with professionalism and dynamism, in the field of real estate brokerage. It is proposed to offer the customer a wide-ranging consultancy and assistance in the real estate sector both in the lease and in the sale.</p>
        </div>
        </div>
       );
    }
}
