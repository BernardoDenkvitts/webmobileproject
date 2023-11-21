"use client"
import React, { useState } from 'react';
import FormInput from '../components/LoginAndRegister/LoginRegisterFields';
import { useRouter } from 'next/navigation';

export default function LoginForm() {

    const [nome, setNome] = useState('');
    const [sobrenome, setSobrenome] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [password, setPassword] = useState('');
    const router = useRouter();

    const BASE_URL = "http://localhost:8080/auth/register"

    function validaCampos() {
        if (nome === "" || sobrenome === "" || email === "" || phone === "" || password === "") {
            alert("Preencha todos os campos")
            return false
        }
        return true
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault()

        if (validaCampos()) {
            try {
                const response = await fetch(BASE_URL, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        first_name: nome,
                        last_name: sobrenome,
                        email: email,
                        phone: phone,
                        password: password
                    }),
                });

                if (response.ok) {
                    // Direcionada o usuario para a pagina principal
                    router.push("/login")
                } else {
                    const data = await response.json();
                    alert(`Erro: ${data.message}`);
                }
            } catch (error) {
                console.error('Erro ao fazer Login:');
            }
        }
    };

    return (
        <div className="flex items-center justify-center h-screen">
            <form className="w-full max-w-md p-20 bg-gray-800 rounded-md shadow-md text-white" onSubmit={handleSubmit}>
                <h2 className="text-3xl font-semibold mb-4 text-center">Crie sua conta</h2>
                <FormInput label="Nome" type="text" value={nome} onChange={setNome} />
                <FormInput label="Sobrenome" type="text" value={sobrenome} onChange={setSobrenome} />
                <FormInput label="Email" type="email" value={email} onChange={setEmail} />
                <FormInput label="Telefone" type="number" value={phone} onChange={setPhone} />
                <FormInput label="Senha" type="password" value={password} onChange={setPassword} />
                <button
                    type='submit'
                    className="bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700 focus:outline-none focus:ring focus:border-indigo-500 w-full"
                >
                    Criar
                </button>
            </form>
        </div>
    );
};

