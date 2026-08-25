import './App.css'
import { BrowserRouter,Route,Routes } from 'react-router-dom'
import Header from './compoents/Header'
import Navbar from './compoents/Navbar'
import Home from './pages/Home'
import Footer from './compoents/Footer'
import Form from './pages/Form'
function App() {

  return (
    <>
      <BrowserRouter>
        <Header/>
        <Navbar/>
        <Routes>
          <Route path='/' element = {<Home/>}/>
           <Route path='/contato' element = {<Form/>}/>
        </Routes>
        <Footer/>
      </BrowserRouter>
    </>
  )
}

export default App
