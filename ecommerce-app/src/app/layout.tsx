import './globals.css'
import { Inter } from 'next/font/google';
import Navbar from './components/layouts/navbar';

const inter = Inter ({subsets: ['latin']});

export default function RootLayout ({
  children,}: {children: React.ReactNode }) {
    return (
      <html lang="en">
        <body className={inter.className}>
          <Navbar></Navbar>
          <h1></h1>
        {children}</body>
      </html>
)}