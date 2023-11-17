import React from 'react';

function Cart() {
  // Suponha que você tenha uma lista de itens no carrinho aqui
  const cartItems = [];

  return (
    <div className="cart">
      <h2>Carrinho de Compras</h2>
      {cartItems.length === 0 ? (
        <p>Seu carrinho está vazio.</p>
      ) : (
        <ul>
          {cartItems.map((item) => (
            <li key={item.id}>
              {item.name} - R$ {item.price}
            </li>
          ))}
          <p>Total: R$ {cartItems.reduce((total, item) => total + item.price, 0)}</p>
          <button>Finalizar Compra</button>
        </ul>
      )}
    </div>
  );
}

export default Cart;
