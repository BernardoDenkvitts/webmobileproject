import React from 'react';

function CartPage({ cart }) {
  return (
    <div>
      <h2>Produtos no Carrinho</h2>
      <ul>
        {cart.map((item) => (
          <li key={item.id}>{item.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default CartPage;
