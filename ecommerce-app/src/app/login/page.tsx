"use client"
import React, { useState } from 'react';
import { useRouter } from 'next/navigation';
import FormInput from '../components/LoginAndRegister/LoginRegisterFields';

const BASE_URL = "http://localhost:8080/auth/login"

export default function LoginForm() {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const router = useRouter();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await fetch(BASE_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    email,
                    password,
                }),
            });

            if (response.ok) {
                const data = await response.json()
                localStorage.setItem("jwtToken", data.login)

                if (localStorage.getItem("jwtToken")) {
                    alert('Login conclúido');
                }
                // Direcionada o usuario para a pagina principal
                router.push("/PaginaPrincipalNaoSeiAinda")
            } else {
                const data = await response.json();
                alert(`Erro: ${data.message}`);
            }
        } catch (error) {
            console.error('Erro ao fazer Login:');
        }
    };

    return (
        <div className="flex items-center justify-center h-screen">
            <form className="w-full max-w-md p-20 bg-gray-800 rounded-md shadow-md text-white" onSubmit={handleSubmit}>
                <h2 className="text-3xl font-semibold mb-4 text-center">Bem-vindo ao</h2>
                <h2 className="text-3xl font-semibold mb-4 text-center">E-Commerce de Cavalo</h2>
                <FormInput label="Email" type="email" value={email} onChange={setEmail} />
                <FormInput label="Senha" type="password" value={password} onChange={setPassword} />
                <button
                    type='submit'
                    className="bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700 focus:outline-none focus:ring focus:border-indigo-500 w-full"
                >
                    Entrar
                </button>
                <a href="http://localhost:3000/register" className="block text-center text-indigo-300 hover:text-indigo-400 mt-2">Não tenho uma conta</a>
            </form>
        </div>
    );
};