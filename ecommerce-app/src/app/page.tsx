"use client"
import React, { useContext, useEffect, useState } from "react";
import Layout from "./components/eventos/layout";
import { useRouter } from 'next/navigation';
import cavaloImage from "../../public/images/cavalo2.jpg";

export default function Home() {
  const [products, setProducts] = useState([]);
  const [cart, setCart] = useState([]);
  const router = useRouter();

  useEffect(() => {
    const fetchApi = async () => {
      const URL = "http://localhost:8080/api/products";
      const response = await fetch(URL);
      const data = await response.json();
      setProducts(data.content);
    };
    fetchApi();
  }, []);

  const addToCart = (productId) => {
    const selectedProduct = products.find((product) => product.id === productId);
    setCart([...cart, selectedProduct]);
  };

  const goToCart = () => {
    router.push("/pages/cart", { cart: cart });
  };

  return (
    <div
      className={`flex justify-center items-center h-screen
                        bg-gradient-to-bl from-indigo-700 via-indigo-400 to-indigo-700
                        text-white`}
    >
      <Layout titulo="Ecommerce de Cavalo">
        <div>
          {products.map((e) => (
            <div key={e.id}>
              <h4>{e.name}</h4>
              <img src={cavaloImage}/>
              <h4>R$ {e.price}</h4>
              <button onClick={() => addToCart(e.id)}>
                Adicionar ao Carrinho
              </button>
            </div>
          ))}
          <button onClick={goToCart}>Ir para o Carrinho</button>
        </div>
      </Layout>
    </div>
  );
}
