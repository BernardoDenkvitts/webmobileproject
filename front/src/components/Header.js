import React from 'react';

function Header() {
  return (
    <header>
      <h1>Equestrian E-commerce</h1>
      <nav>
        <ul>
          <li><a href="/">Página Inicial</a></li>
          <li><a href="/produtos">Produtos</a></li>
          <li><a href="/carrinho">Carrinho</a></li>
        </ul>
      </nav>
    </header>
  );
}

export default Header;
