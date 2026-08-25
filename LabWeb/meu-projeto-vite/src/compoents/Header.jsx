import React from 'react'
import Logo from '../assets/hero.png'
import './Header.css'
const Header = () => {
  return (
    <header> 
        <img src={Logo} className='logo' alt="rego" />   
        <h1>execico 2 react</h1> 
    </header>
  )
}

export default Header