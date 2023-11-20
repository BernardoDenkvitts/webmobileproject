import Layout from "./components/eventos/layout"

export default function Eventos ()
{
  return (
    <div className={`flex justify-center items-center h-screen
                     bg-gradient-to-bl from-indigo-600 via-indigo-500 to-indigo-600
                     text-white`}>
        <Layout titulo="Ecommerce de Cavalos">
          Produtos
        </Layout>
    </div>
  )
}