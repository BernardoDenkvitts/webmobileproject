import React, { useState } from 'react';
import { Link } from 'react-router-dom';

function ProductsPage({ cart, setCart }) {
  const [quantity, setQuantity] = useState({});

  const products = [
    { id: 1, name: 'Cavalo 1', price: 1000, image: '/images/cavalo1.jpg' },
    { id: 2, name: 'Cavalo 2', price: 1500, image: '/images/cavalo2.jpg' },
    { id: 3, name: 'Cavalo 3', price: 1200, image: '/images/cavalo3.jpg' },
  ];

  const addToCart = (product) => {
    setCart([...cart, product]);
    setQuantity({
      ...quantity,
      [product.id]: (quantity[product.id] || 0) + 1,
    });
  };

  return (
    <div>
      <h2>Produtos Disponíveis</h2>
      <ul className="product-list">
        {products.map((product) => (
          <li key={product.id} className="product-item">
            <img src={product.image} alt={product.name} className="product-image" />
            <h3>{product.name}</h3>
            <p>Preço: R$ {product.price}</p>
            <button onClick={() => addToCart(product)}>Adicionar ao Carrinho</button>
            <span>Quantidade no Carrinho: {quantity[product.id] || 0}</span>
          </li>
        ))}
      </ul>

      <Link to="/carrinho">Produtos no Carrinho</Link>
    </div>
  );
}

export default ProductsPage;
