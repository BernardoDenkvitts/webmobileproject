"use client"
import React from "react";
import Layout from "../../components/layouts/navbar";

export default function Cart({ cartItems }) {
  const parsedCartItems = JSON.parse(cartItems);

  return (
    <div>
      <Layout titulo="Seu Carrinho">
        <h2>Itens no Carrinho:</h2>
        <ul>
          {parsedCartItems.map((item) => (
            <li key={item.id}>
              <h4>{item.name}</h4>
              <img src={item.image} alt={item.name} />
              <h4>R$ {item.price}</h4>
            </li>
          ))}
        </ul>
      </Layout>
    </div>
  );
}

// Parse query parameter to retrieve cart items
export async function getServerSideProps(context) {
  const { cart } = context.query;
  return {
    props: {
      cartItems: cart || "[]",
    },
  };
}
