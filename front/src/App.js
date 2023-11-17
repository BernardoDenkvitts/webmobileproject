import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './components/Header';
import ProductsPage from './components/ProductsPage';
import CartPage from './components/CartPage';

function App() {
  const [cart, setCart] = useState([]);

  return (
    <Router>
      <div>
        <Header />
        <Routes>
          <Route path="/produtos" element={<ProductsPage cart={cart} setCart={setCart} />} />
          <Route path="/carrinho" element={<CartPage cart={cart} />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
