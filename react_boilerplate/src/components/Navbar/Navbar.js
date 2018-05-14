import React from 'react';
import {Navbar, Nav, NavItem, NavDropdown, MenuItem} from 'react-bootstrap';
import {Link} from 'react-router-dom';

export const NavBar = () => {
    return (
        <Navbar>
            <Navbar.Header>
                <Navbar.Brand>
                    <h4>My first App</h4>
                </Navbar.Brand>
            </Navbar.Header>
            <Nav>
                <NavItem eventKey={1} href="#">
                    <Link to={`/`}>FIRST</Link>
                </NavItem>
                <NavItem eventKey={2} href="#">
                    <Link to={`/second`}>SECOND</Link>
                </NavItem>
            </Nav>
        </Navbar>
    )
};