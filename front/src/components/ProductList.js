import React from 'react';

function ProductList() {
  // Suponha que você tenha uma lista de produtos aqui
  const products = [
    { id: 1, name: 'Cavalo 1', price: 1000 },
    { id: 2, name: 'Cavalo 2', price: 1500 },
    { id: 3, name: 'Cavalo 3', price: 1200 },
  ];

  return (
    <div className="product-list">
      <h2>Produtos Disponíveis</h2>
      <ul>
        {products.map((product) => (
          <li key={product.id}>
            <h3>{product.name}</h3>
            <p>Preço: R$ {product.price}</p>
            <button>Adicionar ao Carrinho</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ProductList;
